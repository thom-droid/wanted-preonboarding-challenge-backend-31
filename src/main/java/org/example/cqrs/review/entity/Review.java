package org.example.cqrs.review.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.cqrs.product.entity.Product;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "user_id")
    private Long user;

    @NotNull
    @Column(name = "rating", nullable = false)
    private double rating;

    @Size(max = 255)
    @Column(name = "title")
    private String title;

    @Column(name = "content", length = Integer.MAX_VALUE)
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "verified_purchase")
    private boolean verifiedPurchase;

    @Column(name = "helpful_votes")
    private Integer helpfulVotes;

}