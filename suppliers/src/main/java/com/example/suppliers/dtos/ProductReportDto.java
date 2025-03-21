package com.example.suppliers.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductReportDto {
    private int id;
    private String name;
    private String type;
    private int price;
    private int weight;
    private double cost;
}
