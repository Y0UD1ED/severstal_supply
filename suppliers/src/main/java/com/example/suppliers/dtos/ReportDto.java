package com.example.suppliers.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {
    String providerName;
    List<SupplyReportDto> supplies;
    double totalCost;
    int totalWeight;
}
