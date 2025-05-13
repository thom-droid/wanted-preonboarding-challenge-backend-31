package org.example.cqrs.seller.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.cqrs.seller.entity.Seller;
import org.example.cqrs.seller.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    @Override
    public Seller getSellerById(Long id) {
        return sellerRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

}
