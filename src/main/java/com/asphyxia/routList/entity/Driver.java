package com.asphyxia.routList.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

}
