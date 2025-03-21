package com.example.suppliers.controllers;

import com.example.suppliers.converters.SupplyConverter;
import com.example.suppliers.dtos.ReportDto;
import com.example.suppliers.dtos.SupplyDto;
import com.example.suppliers.entities.Supply;
import com.example.suppliers.services.SupplyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplies")
@AllArgsConstructor
@Validated
public class SupplyController {
    private final SupplyService supplyService;
    private final SupplyConverter supplyConverter;

    @PostMapping
    public ResponseEntity<SupplyDto> acceptSupply(@Valid @RequestBody SupplyDto supply){
        Supply acceptedSupply=supplyService.acceptSupply(supply);
        return new ResponseEntity<>(supplyConverter.convertToDto(acceptedSupply), HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity<List<SupplyDto>> getAll(){
//        List<SupplyDto> supplyDtos=supplyService.findAll()
//                .stream()
//                .map(s->supplyConverter.convertToDto(s))
//                .toList();
//        return new ResponseEntity<>(supplyDtos,HttpStatus.OK);
//
//    }

    @GetMapping("/report")
    public ResponseEntity<List<ReportDto>> getReportByPeriod(@RequestParam String startDate, @RequestParam String endDate){
        List<Supply> supplyList=supplyService.findAllByPeriod(startDate,endDate);
        return new ResponseEntity<>(supplyService.makeReportByProvider(supplyList),HttpStatus.OK);

    }



}
