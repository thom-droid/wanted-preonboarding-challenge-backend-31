package org.example.cqrs.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cqrs.product.entity.Product;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.example.cqrs.product.entity.Product}
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPostResponseDto implements Serializable {
    private Long id;
    private String name;
    private String slug;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ProductPostResponseDto fromEntity(Product entity) {
        return ProductPostResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .slug(entity.getSlug())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getCreatedAt())
                .build();
    }
}