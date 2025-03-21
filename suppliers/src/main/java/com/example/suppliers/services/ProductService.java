package com.example.suppliers.services;

import com.example.suppliers.entities.Product;
import com.example.suppliers.exceptions.ProviderNotFoundException;
import com.example.suppliers.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(int id){
        return productRepository.findById(id).orElseThrow(()->new ProviderNotFoundException("Не найден продукт с таким id!"));
    }

    public List<Product> findAllByIds(List<Integer> ids){
        return productRepository.findAllById(ids);
    }

}
