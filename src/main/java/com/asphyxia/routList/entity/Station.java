package com.asphyxia.routList.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private Long id;

    @Column(name = "station_name")
    private String name;

}
