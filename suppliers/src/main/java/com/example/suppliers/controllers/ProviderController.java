package com.example.suppliers.controllers;

import com.example.suppliers.entities.Provider;
import com.example.suppliers.services.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/providers")
@AllArgsConstructor
public class ProviderController {
    private final ProviderService providerService;

    @GetMapping
    public ResponseEntity<List<Provider>> getAllProviders(){
        return new ResponseEntity<>(providerService.findAll(),HttpStatus.OK);
    }

}
