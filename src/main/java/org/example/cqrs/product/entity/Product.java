package org.example.cqrs.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.cqrs.brand.entity.Brand;
import org.example.cqrs.category.entity.Category;
import org.example.cqrs.category.entity.ProductCategory;
import org.example.cqrs.review.entity.Review;
import org.example.cqrs.seller.entity.Seller;
import org.example.cqrs.tag.entity.Tag;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "short_description", length = 500)
    private String shortDescription;

    @Column(name = "full_description", length = Integer.MAX_VALUE)
    private String fullDescription;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductPrice productPrice;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductDetail productDetails;

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> productImages = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private List<ProductOptionGroup> productOptionGroups = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade =  CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ProductCategory> productCategories = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductTag> productTags = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public void addSeller(Seller seller) {
        if (this.seller != null) {
            this.seller.getProducts().remove(this);
        }
        this.seller = seller;
        if (seller != null && !seller.getProducts().contains(this)) {
            seller.getProducts().add(this);
        }
    }

    public void addBrand(Brand brand) {
        if (this.brand != null) {
            this.brand.getProducts().remove(this);
        }
        this.brand = brand;
        if (brand != null && !brand.getProducts().contains(this)) {
            brand.getProducts().add(this);
        }
    }

    public void addCategory(Category category) {
        if (this.productCategories != null) {
            this.productCategories.removeIf(pc -> pc.getCategory().getId().equals(category.getId()));
        }
        ProductCategory productCategory = ProductCategory.builder()
                .product(this)
                .category(category)
                .build();
        this.productCategories.add(productCategory);
        if (category != null && !category.getProductCategories().contains(productCategory)) {
            category.getProductCategories().add(productCategory);
        }
    }

    public void addProductPrice(ProductPrice productPrice) {
        if (this.productPrice != null) {
            this.productPrice.setProduct(null);
        }
        this.productPrice = productPrice;
        if (productPrice != null && productPrice.getProduct() != this) {
            productPrice.setProduct(this);
        }
    }

    public void addProductDetails(ProductDetail productDetails) {
        if (this.productDetails != null) {
            this.productDetails.setProduct(null);
        }
        this.productDetails = productDetails;
        if (productDetails != null && productDetails.getProduct() != this) {
            productDetails.setProduct(this);
        }
    }

    public void addProductImage(ProductImage productImage) {
        if (!productImages.contains(productImage)) {
            productImages.add(productImage);
            productImage.setProduct(this);
        }
    }

    public void addProductOptionGroup(ProductOptionGroup productOptionGroup) {
        if (!productOptionGroups.contains(productOptionGroup)) {
            productOptionGroups.add(productOptionGroup);
            productOptionGroup.setProduct(this);
        }
    }

    public void addReview(Review review) {
        if (!reviews.contains(review)) {
            reviews.add(review);
            review.setProduct(this);
        }
    }

    public void addTag(Tag tag) {
        if (this.productTags != null) {
            this.productTags.removeIf(pt -> pt.getTag().getId().equals(tag.getId()));
        }
        ProductTag productTag = ProductTag.builder()
                .product(this)
                .tag(tag)
                .build();
        this.productTags.add(productTag);
        if (tag != null && !tag.getProductTags().contains(productTag)) {
            tag.getProductTags().add(productTag);
        }
    }

    public enum Status {
        ACTIVE,
        OUT_OF_STOCK,
        DELETED
    }
}