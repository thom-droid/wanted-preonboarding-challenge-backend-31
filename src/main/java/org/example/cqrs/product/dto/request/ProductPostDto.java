package org.example.cqrs.product.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.example.cqrs.brand.entity.Brand;
import org.example.cqrs.category.dto.CategoryPostDto;
import org.example.cqrs.category.entity.ProductCategory;
import org.example.cqrs.product.entity.Product;
import org.example.cqrs.product.entity.ProductTag;
import org.example.cqrs.seller.entity.Seller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO for {@link org.example.cqrs.product.entity.Product}
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductPostDto implements Serializable {
    @NotBlank
    private String name;
    @NotBlank
    private String slug;
    private String shortDescription;
    private String fullDescription;

    @NotNull
    private Long sellerId;
    @NotNull
    private Long brandId;

    private Product.Status status;
    @Valid
    private ProductDetailPostDto detail;
    @Valid
    private ProductPricePostDto price;

    @Builder.Default
    private List<CategoryPostDto> categories = new ArrayList<>();

    @Builder.Default
    private List<@Valid ProductOptionGroupPostDto> optionGroups = new ArrayList<>();

    @Builder.Default
    private List<@Valid ProductImagePostDto> images = new ArrayList<>();

    @Builder.Default
    private List<Long> tags = new ArrayList<>();

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .slug(slug)
                .shortDescription(shortDescription)
                .fullDescription(fullDescription)
                .seller(Seller.builder().id(sellerId).build())
                .brand(Brand.builder().id(brandId).build())
                .status(status)
                .productDetails(detail.toEntity())
                .productPrice(price.toEntity())
                .productCategories(categories.stream().map(cat -> ProductCategory.builder().id(cat.getCategoryId()).isPrimary(cat.isPrimary()).build()).collect(Collectors.toList()))
                .productImages(images.stream().map(ProductImagePostDto::toEntity).collect(Collectors.toList()))
                .productTags(tags.stream().map(id -> ProductTag.builder().id(id).build()).collect(Collectors.toList()))
                .build();
    }
}