package org.example.cqrs.product.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.cqrs.brand.service.BrandService;
import org.example.cqrs.category.service.CategoryService;
import org.example.cqrs.core.file.FileStorageService;
import org.example.cqrs.product.entity.Product;
import org.example.cqrs.product.repository.ProductRepository;
import org.example.cqrs.seller.service.SellerService;
import org.example.cqrs.tag.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SellerService sellerService;
    private final BrandService brandService;
    private final CategoryService categoryService;
    private final TagService tagService;
    private final FileStorageService fileStorageService;

    @Override
    public Product createProduct(Product source, List<MultipartFile> files) {

        // file storage
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            String url = fileStorageService.uploadFile(file);
            source.getProductImages().get(i).setUrl(url);
        }

        source.addSeller(sellerService.getSellerById(source.getSeller().getId()));
        source.addBrand(brandService.getBrandById(source.getBrand().getId()));

        source.getProductCategories().stream()
            .map(category -> categoryService.getCategoryById(category.getId()))
            .forEach(source::addCategory);

        source.getProductTags().stream()
            .map(tag -> tagService.getTagById(tag.getId()))
            .forEach(source::addTag);

        // save product
        return productRepository.save(source);
    }

}
