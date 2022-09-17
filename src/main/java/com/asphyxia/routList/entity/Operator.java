package com.asphyxia.routList.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "operator")
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operator_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "station_id", referencedColumnName = "station_id")
    private Station station;

}
