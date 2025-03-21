package com.example.suppliers.services;

import com.example.suppliers.entities.Provider;
import com.example.suppliers.exceptions.ProviderNotFoundException;
import com.example.suppliers.repositories.ProviderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProviderService {
    private final ProviderRepository providerRepository;

    public List<Provider> findAll() {
        return providerRepository.findAll();
    }

    public Provider findById(int id){
        return providerRepository.findById(id).orElseThrow(()->new ProviderNotFoundException("Не найден поставщик с таким id!"));
    }
}
