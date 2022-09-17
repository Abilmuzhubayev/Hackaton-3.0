package com.asphyxia.routList.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "safety_precaution")
@Data
public class SafetyPrecaution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "safety_precaution_id")
    private Long id;

    @Column(name = "description")
    private String description;
}
