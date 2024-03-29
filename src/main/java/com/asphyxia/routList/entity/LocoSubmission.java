package com.asphyxia.routList.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "loco_submission")
@Data
public class LocoSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loco_submission_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", referencedColumnName = "plan_id")
    private Plan plan;

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
    private List<SafetyPrecaution> safetyPrecautions;

    @ManyToMany
    @JoinTable(
            name = "submission_consumption",
            joinColumns = @JoinColumn(name = "submission_id"),
            inverseJoinColumns = @JoinColumn(name = "consumption_id"))
    private List<FuelConsumption> fuelConsumptions;

    @ManyToMany
    @JoinTable(
            name = "submission_speeds",
            joinColumns = @JoinColumn(name = "submission_id"),
            inverseJoinColumns = @JoinColumn(name = "speed_id"))
    private List<TechSpeed> techSpeeds;

    @Column(name = "status")
    private String status;

    public List<Long> getSafetyIds() {
        List<Long> safetyIds = new ArrayList<>();
        for (SafetyPrecaution safetyPrecaution : safetyPrecautions) {
            safetyIds.add(safetyPrecaution.getId());
        }
        return safetyIds;
    }

    public List<Long> getConsumptionIds() {
        List<Long> consumptionIds = new ArrayList<>();
        for (TechSpeed techSpeed : techSpeeds) {
            consumptionIds.add(techSpeed.getId());
        }
        return consumptionIds;
    }

    public List<Long> getSpeedIds() {
        List<Long> techSpeedIds = new ArrayList<>();
        for (TechSpeed techSpeed : techSpeeds) {
            techSpeedIds.add(techSpeed.getId());
        }
        return techSpeedIds;
    }
}
