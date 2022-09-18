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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id", referencedColumnName = "driver_id")
    private Driver driver;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "in_operator_id", referencedColumnName = "operator_id")
    private Operator inOperator;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "out_operator_id", referencedColumnName = "operator_id")
    private Operator outOperator;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_id", referencedColumnName = "station_id")
    private Station departureStation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination_id", referencedColumnName = "station_id")
    private Station destinationStation;

    @Column(name = "status")
    private String status;

    public List<StationData> getStationDataList() {
        return plan.getStationDataList();
    }

    public StationData getStationDatByOrder(Integer order) {
        List<StationData> list = getStationDataList();
        return list.get((order < 1 ? list.size() - 1 : order));
    }

    public Timestamp getStartTime() {
        return getStationDatByOrder(1).getDepartureTime();
    }

    public Timestamp getEndTime() {
        return getStationDatByOrder(0).getArrivalTime();
    }

}
