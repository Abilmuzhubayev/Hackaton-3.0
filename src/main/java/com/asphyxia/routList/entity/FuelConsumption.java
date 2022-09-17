package com.asphyxia.routList.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "fuel_consumption")
@Data
public class FuelConsumption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fuel_consumption_id")
    private Long id;

    @Column(name = "description")
    private String description;
}
