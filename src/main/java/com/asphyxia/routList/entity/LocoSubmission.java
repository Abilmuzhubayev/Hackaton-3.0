package com.asphyxia.routList.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name = "loco_submission")
@Data
public class LocoSubmission {
    @Id
    @Column(name = "loco_submission_id")
    private Long id;

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "recuperation_counter")
    private Double recuperationCounter;

    @Column(name = "electric_counter")
    private Double electricCounter;

    @ManyToMany
    @JoinTable(
            name = "submission_precaution",
            joinColumns = @JoinColumn(name = "submission_id"),
            inverseJoinColumns = @JoinColumn(name = "precaution_id"))
    List<SafetyPrecaution> safetyPrecautions;

    @ManyToMany
    @JoinTable(
            name = "submission_consumption",
            joinColumns = @JoinColumn(name = "submission_id"),
            inverseJoinColumns = @JoinColumn(name = "consumption_id"))
    List<FuelConsumption> fuelConsumptions;

    @ManyToMany
    @JoinTable(
            name = "submission_speeds",
            joinColumns = @JoinColumn(name = "submission_id"),
            inverseJoinColumns = @JoinColumn(name = "speed_id"))
    List<TechSpeed> techSpeeds;
}
