package com.example.suppliers.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSupplyDto {
    @NotNull(message = "Продукт должен быть указан!")
    private int id;
    @Min(value = 1,message = "Цена должны быть больше нуля!")
    private int price;
    @Min(value = 1000,message = "Вес продукта должен быть больше 1000 гр!")
    private int weight;
}
