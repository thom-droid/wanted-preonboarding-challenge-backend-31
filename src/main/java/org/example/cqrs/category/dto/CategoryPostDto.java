package org.example.cqrs.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryPostDto {

    private Long categoryId;
    private boolean isPrimary;

}
