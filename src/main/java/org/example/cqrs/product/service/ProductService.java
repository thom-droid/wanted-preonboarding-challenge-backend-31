package org.example.cqrs.product.service;

import org.example.cqrs.product.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    Product createProduct(Product source, List<MultipartFile> files);
}
