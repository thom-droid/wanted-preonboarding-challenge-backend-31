package org.example.cqrs.category.service;

import org.example.cqrs.category.entity.Category;

public interface CategoryService {
    Category getCategoryById(Long id);
}
