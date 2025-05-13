package org.example.cqrs.product.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.example.cqrs.product.entity.ProductDetail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * DTO for {@link org.example.cqrs.product.entity.ProductDetail}
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductDetailPostDto implements Serializable {
    private BigDecimal weight;
    private Map<String, Object> dimensions;
    private String materials;
    @Size(max = 100)
    private String countryOfOrigin;
    private String warrantyInfo;
    private String careInstructions;
    private Map<String, Object> additionalInfo;

    public ProductDetail toEntity() {
        return ProductDetail.builder()
                .weight(weight)
                .dimensions(dimensions)
                .materials(materials)
                .countryOfOrigin(countryOfOrigin)
                .warrantyInfo(warrantyInfo)
                .careInstructions(careInstructions)
                .additionalInfo(additionalInfo)
                .build();
    }


}