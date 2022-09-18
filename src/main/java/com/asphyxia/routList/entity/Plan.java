package com.asphyxia.routList.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", referencedColumnName = "route_id")
    private Route route;

    @OneToOne(mappedBy = "plan", cascade = CascadeType.ALL)
    private LocoAcceptance locoAcceptance;

    @OneToOne(mappedBy = "plan", cascade = CascadeType.ALL)
    private LocoSubmission locoSubmission;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<Subtask> subtaskList;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<StationData> stationDataList;
}
