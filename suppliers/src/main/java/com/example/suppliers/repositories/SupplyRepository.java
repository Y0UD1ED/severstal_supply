package com.example.suppliers.repositories;

import com.example.suppliers.entities.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SupplyRepository extends JpaRepository<Supply,Integer> {
    @Query("from Supply s join fetch s.provider where s.supplyDate between :startDate and :endDate")
    List<Supply> findBySupplyDateBetween(LocalDate startDate,LocalDate endDate);
}
