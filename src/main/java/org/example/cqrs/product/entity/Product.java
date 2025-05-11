package org.example.cqrs.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.cqrs.brand.entity.Brand;
import org.example.cqrs.category.entity.ProductCategory;
import org.example.cqrs.review.entity.Review;
import org.example.cqrs.seller.entity.Seller;

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

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToOne(mappedBy = "product")
    private ProductPrice productPrice;

    @OneToOne(mappedBy = "product")
    private ProductDetail productDetails;

    @Builder.Default
    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImages = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "product")
    private List<ProductOptionGroup> productOptionGroups = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "product")
    private List<Review> reviews = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "product")
    private List<ProductCategory> productCategories = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "product")
    private List<ProductTag> productTags = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}