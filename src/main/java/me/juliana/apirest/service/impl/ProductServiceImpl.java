package me.juliana.apirest.service.impl;

import java.util.List;

import me.juliana.apirest.model.Product;
import me.juliana.apirest.service.exception.BusinessException;
import me.juliana.apirest.service.exception.NotFoundException;
import me.juliana.apirest.repository.ProductRepository;
import org.springframework.stereotype.Service;

import me.juliana.apirest.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(Long id) {
      return productRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Product create(Product productToCreate) {
        if(productToCreate.getId() != null && productRepository.existsById(productToCreate.getId())){
            throw new IllegalArgumentException("This product ID already exists.");
        }
        return productRepository.save(productToCreate);
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Product dbProduct = this.findById(id);
        this.productRepository.delete(dbProduct);
    }

    public Product update(Long id, Product productToUpdate) {
        Product dbProduct = this.findById(id);
        if (!dbProduct.getId().equals(productToUpdate.getId())) {
            throw new BusinessException("Update IDs must be the same.");
        }
        dbProduct.setName(productToUpdate.getName());
        dbProduct.setDescription(productToUpdate.getDescription());
        dbProduct.setPrice(productToUpdate.getPrice());
        dbProduct.setImage(productToUpdate.getImage());

        return this.productRepository.save(dbProduct);
    }
}