package com.example.suppliers.services;

import com.example.suppliers.converters.SupplyConverter;
import com.example.suppliers.dtos.ProductReportDto;
import com.example.suppliers.dtos.ReportDto;
import com.example.suppliers.dtos.SupplyDto;
import com.example.suppliers.dtos.SupplyReportDto;
import com.example.suppliers.entities.Supply;
import com.example.suppliers.exceptions.SupplyNotFoundException;
import com.example.suppliers.repositories.SupplyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SupplyService {
    private final SupplyRepository supplyRepository;
    private final ProductPurchaseService purchaseService;
    private final ProviderService providerService;
    private final SupplyConverter supplyConverter;
    private final DateTimeFormatter dateFormat=DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public List<Supply> findAll(){
        return supplyRepository.findAll();
    }

    public Supply findById(int id){
        return supplyRepository.findById(id).orElseThrow(()->new SupplyNotFoundException("Не найдена поставка с таким id!"));
    }
    @Transactional
    public Supply acceptSupply(SupplyDto supplyDto){
        Supply supply=new Supply();
        supply.setProvider(providerService.findById(supplyDto.getProviderId()));
        supply.setSupplyDate(LocalDate.parse(supplyDto.getSupplyDate(), dateFormat));
        supplyRepository.save(supply);
        supply.setProducts(new HashSet<>(purchaseService.saveAll(supplyDto.getProducts(),supply)));
        return supply;
    }

    public List<Supply> findAllByPeriod(String startDate, String endDate) {
        LocalDate date1=LocalDate.parse(startDate,dateFormat);
        LocalDate date2=LocalDate.parse(endDate,dateFormat);
        return supplyRepository.findBySupplyDateBetween(date1,date2);
    }

    public List<ReportDto> makeReportByProvider(List<Supply> supplyList){
        Map<String,List<SupplyReportDto>> groupedSupplies=supplyList .stream()
                .collect(Collectors.groupingBy(s->s.getProvider().getName(),
                        Collectors.mapping(supplyConverter::convertToReportDto, Collectors.toList())));

        List<ReportDto> reports=new ArrayList<>();
        for(String provider:groupedSupplies.keySet()){
            List<SupplyReportDto> supplyReportDtos=groupedSupplies.get(provider);
            reports.add(new ReportDto(provider,supplyReportDtos,calculateTotalCost(supplyReportDtos),calculateTotalWeight(supplyReportDtos)));
        }
        return reports;
    }

    private double calculateTotalCost(List<SupplyReportDto> supplyReportDtos){
        return supplyReportDtos.stream()
                .flatMapToDouble(s->s.getProducts().stream().mapToDouble(ProductReportDto::getCost))
                .sum();
    }

    private int calculateTotalWeight(List<SupplyReportDto> supplyReportDtos){
        return supplyReportDtos.stream()
                .flatMapToInt(s->s.getProducts().stream().mapToInt(ProductReportDto::getWeight))
                .sum();
    }
}
