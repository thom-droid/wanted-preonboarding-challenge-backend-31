package org.example.cqrs.brand.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.cqrs.brand.entity.Brand;
import org.example.cqrs.brand.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class BrandServiceImpl implements BrandService {

    private  final BrandRepository brandRepository;

    @Override
    public Brand getBrandById(Long id) {
        return brandRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

}
