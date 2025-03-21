package com.example.suppliers.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(name = "supply_id",nullable = false)
    private Supply supply;
    private int price; //цена за кг
    private int weight; //вес в гр

    public ProductPurchase(Product product, Supply supply, int price, int weight) {
        this.product = product;
        this.supply = supply;
        this.price = price;
        this.weight = weight;
    }
}
