package com.asphyxia.routList.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Long id;

    @OneToOne(mappedBy = "route")
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "manager_id")
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "in_operator_id", referencedColumnName = "operator_id")
    private Operator inOperator;

    @ManyToOne
    @JoinColumn(name = "out_operator_id", referencedColumnName = "operator_id")
    private Operator outOperator;

    @ManyToOne
    @JoinColumn(name = "departure_id", referencedColumnName = "station_id")
    private Station departureStation;

    @ManyToOne
    @JoinColumn(name = "destination_id", referencedColumnName = "station_id")
    private Station destinationStation;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private Status status;

}
