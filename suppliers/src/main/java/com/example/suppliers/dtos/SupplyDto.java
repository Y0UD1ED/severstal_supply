package com.example.suppliers.dtos;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplyDto {
    private int providerId;
    @NotEmpty(message = "Необходимо указать минимум один продукт в поставке!")
    @Valid
    List<ProductSupplyDto> products;
    @NotNull(message = "Требуется указать дату поставки!")
    private String supplyDate;
}
