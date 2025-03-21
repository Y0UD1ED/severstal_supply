package com.example.suppliers.converters;

import com.example.suppliers.dtos.ProductReportDto;
import com.example.suppliers.dtos.ProductSupplyDto;
import com.example.suppliers.dtos.SupplyDto;
import com.example.suppliers.dtos.SupplyReportDto;
import com.example.suppliers.entities.Supply;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SupplyConverter {
    public SupplyDto convertToDto(Supply supply){
        SupplyDto supplyDto=new SupplyDto();
        supplyDto.setProviderId(supply.getProvider().getId());
        supplyDto.setSupplyDate(supply.getSupplyDate().toString());
        List<ProductSupplyDto> products=supply.getProducts()
                .stream()
                .map(p->new ProductSupplyDto(p.getProduct().getId(),p.getPrice(),p.getWeight()))
                .toList();
        supplyDto.setProducts(products);
        return supplyDto;
    }

    public SupplyReportDto convertToReportDto(Supply supply){
        SupplyReportDto supplyDto=new SupplyReportDto();
        supplyDto.setSupplyId(supply.getId());
        supplyDto.setSupplyDate(supply.getSupplyDate().toString());
        List<ProductReportDto> products=supply.getProducts()
                .stream()
                .map(p->new ProductReportDto(p.getProduct().getId(),p.getProduct().getName(),p.getProduct().getType(),p.getPrice(),p.getWeight(),((double) (p.getPrice() * p.getWeight()) /1000)))
                .toList();
        supplyDto.setProducts(products);
        return supplyDto;
    }

}
