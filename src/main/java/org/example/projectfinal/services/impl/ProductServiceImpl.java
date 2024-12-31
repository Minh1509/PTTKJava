package org.example.projectfinal.services.impl;

import org.example.projectfinal.entity.Product;
import org.example.projectfinal.repository.ProductRepository;
import org.example.projectfinal.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
