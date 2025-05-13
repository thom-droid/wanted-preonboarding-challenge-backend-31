package org.example.cqrs.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cqrs.core.response.ApiResponse;
import org.example.cqrs.product.dto.request.ProductPostDto;
import org.example.cqrs.product.dto.response.ProductPostResponseDto;
import org.example.cqrs.product.entity.Product;
import org.example.cqrs.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<?>> createProduct(@RequestPart("product") @Valid ProductPostDto productPostDto,
                                                        @RequestPart("images") List<MultipartFile> files) {

        Product source = productPostDto.toEntity();
        Product product = productService.createProduct(source, files);
        ProductPostResponseDto responseDto = ProductPostResponseDto.fromEntity(product);
        ApiResponse<ProductPostResponseDto> response = ApiResponse.of(responseDto);
        return ResponseEntity.ofNullable(response);
    }
}
