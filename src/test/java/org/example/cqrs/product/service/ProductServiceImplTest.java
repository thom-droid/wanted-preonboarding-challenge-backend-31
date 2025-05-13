package org.example.cqrs.product.service;

import org.example.cqrs.brand.entity.Brand;
import org.example.cqrs.brand.service.BrandServiceImpl;
import org.example.cqrs.category.entity.Category;
import org.example.cqrs.category.service.CategoryServiceImpl;
import org.example.cqrs.core.file.LocalFileStorage;
import org.example.cqrs.product.entity.Product;
import org.example.cqrs.product.entity.ProductImage;
import org.example.cqrs.product.repository.ProductRepository;
import org.example.cqrs.seller.entity.Seller;
import org.example.cqrs.seller.service.SellerServiceImpl;
import org.example.cqrs.tag.entity.Tag;
import org.example.cqrs.tag.service.TagServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryServiceImpl categoryService;

    @Mock
    private BrandServiceImpl brandService;

    @Mock
    private SellerServiceImpl sellerService;

    @Mock
    private TagServiceImpl tagService;

    @Mock
    private LocalFileStorage fileStorageService;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testCreateProduct() {

        //given
        Product product = Product.builder()
                .id(123L)
                .name("편안한소파")
                .slug("super-comfortable-sofa")
                .createdAt(LocalDateTime.now())
                .build();

        List<MultipartFile> mockMultipartFiles = List.of(new MockMultipartFile("file1", "images".getBytes()));

        //when
        Product mockedProduct = mock(Product.class);
        when(mockedProduct.getSeller()).thenReturn(Seller.builder().id(1L).build());
        when(mockedProduct.getBrand()).thenReturn(Brand.builder().id(1L).build());
        when(mockedProduct.getProductCategories()).thenReturn(new ArrayList<>());
        when(mockedProduct.getProductTags()).thenReturn(new ArrayList<>());
        when(productRepository.save(mockedProduct)).thenReturn(product);
        when(fileStorageService.uploadFile(mock(MultipartFile.class))).thenReturn("fileUrl");
        when(sellerService.getSellerById(Mockito.anyLong())).thenReturn(Mockito.mock(Seller.class));
        when(brandService.getBrandById(Mockito.anyLong())).thenReturn(Mockito.mock(Brand.class));
        when(categoryService.getCategoryById(Mockito.anyLong())).thenReturn(Mockito.mock(Category.class));
        when(tagService.getTagById(Mockito.anyLong())).thenReturn(Mockito.mock(Tag.class));
        when(mockedProduct.getProductImages()).thenReturn(List.of(mock(ProductImage.class)));

        //then
        Product createdProduct = productService.createProduct(mockedProduct, mockMultipartFiles);
        verify(productRepository).save(Mockito.any(Product.class));
        verify(fileStorageService, times(mockMultipartFiles.size())).uploadFile(Mockito.any(MultipartFile.class));

        assertEquals(product.getId(), createdProduct.getId());
        assertEquals(product.getName(), createdProduct.getName());
        assertEquals(product.getSlug(), createdProduct.getSlug());

    }

}