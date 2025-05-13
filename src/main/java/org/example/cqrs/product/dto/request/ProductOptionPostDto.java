package org.example.cqrs.product.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cqrs.product.entity.ProductOption;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO for {@link ProductOption}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOptionPostDto implements Serializable {
    @NotNull
    @Size(max = 100)
    private String name;
    private BigDecimal additionalPrice;
    @Size(max = 100)
    private String sku;
    private Integer stock;
    private Integer displayOrder;
    private List<ProductImagePostDto> productImages = new ArrayList<>();

    public ProductOption toEntity() {
        return ProductOption.builder()
                .name(name)
                .additionalPrice(additionalPrice)
                .sku(sku)
                .stock(stock)
                .displayOrder(displayOrder)
                .productImages(productImages.stream().map(ProductImagePostDto::toEntity).collect(Collectors.toList()))
                .build();
    }
}