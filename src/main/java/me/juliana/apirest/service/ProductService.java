package me.juliana.apirest.service;

import me.juliana.apirest.model.Product;

import java.util.List;

public interface ProductService {
    Product findById(Long id);

    Product create(Product product);

    List<Product> findAll();

    void delete(Long id);

    Product update(Long id, Product productToUpdate);
}
