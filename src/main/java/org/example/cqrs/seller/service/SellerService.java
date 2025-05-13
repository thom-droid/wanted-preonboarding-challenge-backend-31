package org.example.cqrs.seller.service;

import org.example.cqrs.seller.entity.Seller;

public interface SellerService {

    Seller getSellerById(Long id);

}
