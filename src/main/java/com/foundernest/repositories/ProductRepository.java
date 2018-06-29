package com.foundernest.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.foundernest.domain.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
