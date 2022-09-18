package com.asphyxia.routList.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "station_data")
public class StationData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_data_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", referencedColumnName = "plan_id")
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "station_id", referencedColumnName = "station_id")
    private Station station;

    @Column(name = "arrival_time")
    private Timestamp arrivalTime;

    @Column(name = "departure_time")
    private Timestamp departureTime;

    @Column(name = "weight_netto")
    private Double weightNetto;

    @Column(name = "weight_brutto")
    private Double weightBrutto;

    @Column(name = "cisterns")
    private Integer cisterns;

    @Column(name = "axes_composition")
    private Integer axesComposition;

    @Column(name = "status")
    private String status;

    @Column(name = "order_number")
    private Integer orderNumber;

}
