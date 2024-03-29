package com.asphyxia.routList.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "loco_acceptance")
@Data
public class LocoAcceptance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loco_acceptance_id")
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

    @Column(name = "status")
    private String status;
}
