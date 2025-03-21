package com.example.suppliers.services;

import com.example.suppliers.dtos.ProductSupplyDto;
import com.example.suppliers.entities.Product;
import com.example.suppliers.entities.ProductPurchase;
import com.example.suppliers.entities.Supply;
import com.example.suppliers.exceptions.ProductNotFoundException;
import com.example.suppliers.repositories.ProductPurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductPurchaseService {
    private final ProductPurchaseRepository purchaseRepository;
    private final ProductService productService;

    @Transactional
    public List<ProductPurchase> saveAll(List<ProductSupplyDto> productSupplyDtos, Supply supply){
        Map<Integer,Product> productMap=findAllProductInPurchase(productSupplyDtos);
        List<ProductPurchase> purchaseList=productSupplyDtos.stream()
                .map(dto->{
                    ProductPurchase productPurchase=new ProductPurchase();
                    if(!productMap.containsKey(dto.getId())){
                        throw new ProductNotFoundException("Продукт с идентификатором "+dto.getId()+" не найден!");
                    }
                    productPurchase.setProduct(productMap.get(dto.getId()));
                    productPurchase.setWeight(dto.getWeight());
                    productPurchase.setPrice(dto.getPrice());
                    productPurchase.setSupply(supply);
                    return productPurchase;
                })
                .filter(distinctByKey(p->p.getProduct().getId()))
                .toList();
        return purchaseRepository.saveAll(purchaseList);
    }

    private  Map<Integer, Product> findAllProductInPurchase(List<ProductSupplyDto> dtos){
        List<Integer> productIds = dtos.stream()
                .map(ProductSupplyDto::getId)
                .collect(Collectors.toList());
        return productService.findAllByIds(productIds)
                .stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
