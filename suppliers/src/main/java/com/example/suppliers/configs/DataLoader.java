package com.example.suppliers.configs;

import com.example.suppliers.entities.Product;
import com.example.suppliers.entities.ProductPurchase;
import com.example.suppliers.entities.Provider;
import com.example.suppliers.entities.Supply;
import com.example.suppliers.repositories.ProductPurchaseRepository;
import com.example.suppliers.repositories.ProductRepository;
import com.example.suppliers.repositories.ProviderRepository;
import com.example.suppliers.repositories.SupplyRepository;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final ProviderRepository providerRepository;
    private final ProductPurchaseRepository purchaseRepository;
    private final SupplyRepository supplyRepository;

    @Override
    public void run(String ...args) throws Exception{
        List<Product> productList=List.of(new Product("Флорина","Яблоко"),
                new Product("Мельба","Яблоко"),
                new Product("Фуджи","Яблоко"),
                new Product("Медуница","Яблоко"),
                new Product("Конференс","Груша"),
                new Product("Аббат","Груша"),
                new Product("Вильямс","Груша"),
                new Product("Комис","Груша"));
        productRepository.saveAll(productList);
        List<Provider> providerList =List.of(new Provider("Поставщик 1","7-912-232-2333"),
                new Provider("Поставщик 2","7-944-243-2463"),
                new Provider("Поставщик 3","7-962-252-2343"));
        providerRepository.saveAll(providerList);

        List<Supply> supplyList=List.of(new Supply(providerList.get(0),LocalDate.now()),
                        new Supply(providerList.get(1),LocalDate.now()),
                        new Supply(providerList.get(2),LocalDate.now()));
        supplyRepository.saveAll(supplyList);
        List<ProductPurchase> purchaseList=List.of(new ProductPurchase(productList.get(0),supplyList.get(0),150,10000),
                new ProductPurchase(productList.get(4),supplyList.get(0),200,25000),
                new ProductPurchase(productList.get(0),supplyList.get(1),250,30000),
                new ProductPurchase(productList.get(1),supplyList.get(2),340,24000),
                new ProductPurchase(productList.get(5),supplyList.get(2),400,10000));
        purchaseRepository.saveAll(purchaseList);

    }
}
