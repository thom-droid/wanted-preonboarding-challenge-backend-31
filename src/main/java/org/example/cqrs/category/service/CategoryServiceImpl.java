package org.example.cqrs.category.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.cqrs.category.entity.Category;
import org.example.cqrs.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

}
