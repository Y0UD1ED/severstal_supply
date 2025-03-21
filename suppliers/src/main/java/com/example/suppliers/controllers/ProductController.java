package com.example.suppliers.controllers;

import com.example.suppliers.entities.Product;
import com.example.suppliers.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Map<String,List<Product>>> getAllProducts(){
        return new ResponseEntity<>(productService.findAll()
                .stream()
                .collect(Collectors.groupingBy(Product::getType)), HttpStatus.OK);
    }
}
