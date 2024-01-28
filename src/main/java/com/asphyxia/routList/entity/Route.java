package com.asphyxia.routList.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Long id;

    @OneToOne(mappedBy = "route", cascade = CascadeType.ALL)
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

    @Column(name = "departure_time")
    private Timestamp departureTime;

    @Column(name = "destination_time")
    private Timestamp destinationTime;

    @Column(name = "status")
    private String status;

    public List<StationData> getStationDataList() {
        return plan.getStationDataList();
    }

    public StationData getStationDateByOrder(Integer order) {
        List<StationData> list = getStationDataList();
        return list.get((order < 1 ? list.size() - 1 : order - 1));
    }

}
