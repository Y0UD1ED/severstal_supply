package com.example.suppliers.repositories;

import com.example.suppliers.entities.ProductPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPurchaseRepository extends JpaRepository<ProductPurchase,Integer> {
}
