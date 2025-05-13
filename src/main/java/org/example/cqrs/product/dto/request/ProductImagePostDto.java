package org.example.cqrs.product.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.example.cqrs.product.entity.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * DTO for {@link org.example.cqrs.product.entity.ProductImage}
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductImagePostDto implements Serializable {
    @Size(max = 255)
    private String altText;
    private boolean isPrimary;
    private Integer displayOrder;
    private Long optionId;

    public ProductImage toEntity() {
        return ProductImage.builder()
                .altText(altText)
                .isPrimary(isPrimary)
                .displayOrder(displayOrder)
                .build();
    }
}