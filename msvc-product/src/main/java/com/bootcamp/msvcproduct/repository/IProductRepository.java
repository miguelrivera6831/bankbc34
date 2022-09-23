package com.bootcamp.msvcproduct.repository;

import com.bootcamp.msvcproduct.model.document.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Interface de Metodos del Repositorio.
 */
public interface IProductRepository extends ReactiveMongoRepository<Product, String> {
}