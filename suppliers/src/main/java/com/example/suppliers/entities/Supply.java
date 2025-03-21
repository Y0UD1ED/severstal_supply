package com.example.suppliers.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "provider_id",nullable = false)
    private Provider provider;
    @OneToMany(mappedBy = "supply")
    @Fetch(FetchMode.SUBSELECT)
    private Set<ProductPurchase> products;
    @Column(nullable = false)
    private LocalDate supplyDate;

    public Supply(Provider provider, LocalDate supplyDate) {
        this.provider = provider;
        this.supplyDate = supplyDate;
    }
}
