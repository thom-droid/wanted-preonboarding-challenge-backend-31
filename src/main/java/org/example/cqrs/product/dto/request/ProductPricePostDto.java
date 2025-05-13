package org.example.cqrs.product.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.example.cqrs.product.entity.ProductPrice;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductPricePostDto {
    private BigDecimal basePrice;
    private BigDecimal salePrice;
    private BigDecimal costPrice;
    private String currency;
    private BigDecimal taxRate;

    public ProductPrice toEntity() {
        return ProductPrice.builder()
                .basePrice(basePrice)
                .salePrice(salePrice)
                .costPrice(costPrice)
                .currency(currency)
                .taxRate(taxRate)
                .build();
    }
}
