package com.example.suppliers.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String name; //название вида продукта
    @Column(nullable = false)
    private String type; //яблоко или груша

    public Product(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
