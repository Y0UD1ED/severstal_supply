package com.example.suppliers.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true,nullable = false)
    private String name;
    @Column(nullable = false)
    private String phone;
//    @OneToMany(mappedBy = "supplier")
//    private List<Supply> supplies;

    public Provider(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
