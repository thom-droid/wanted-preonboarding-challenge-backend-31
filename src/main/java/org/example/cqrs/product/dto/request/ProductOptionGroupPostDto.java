package org.example.cqrs.product.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cqrs.product.entity.ProductOptionGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO for {@link ProductOptionGroup}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOptionGroupPostDto implements Serializable {
    @NotNull
    @Size(max = 100)
    private String name;
    private Integer displayOrder;
    private List<ProductOptionPostDto> productOptions = new ArrayList<>();

    public ProductOptionGroup toEntity() {
        return ProductOptionGroup.builder()
                .name(name)
                .displayOrder(displayOrder)
                .productOptions(productOptions.stream().map(ProductOptionPostDto::toEntity).collect(Collectors.toList()))
                .build();
    }
}