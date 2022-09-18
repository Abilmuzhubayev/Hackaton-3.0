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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id", referencedColumnName = "route_id")
    private Route route;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "local_acceptance_id", referencedColumnName = "loco_acceptance_id")
    private LocoAcceptance locoAcceptance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loco_submission_id", referencedColumnName = "loco_submission_id")
    private LocoSubmission locoSubmission;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<Subtask> subtaskList;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<StationData> stationDataList;
}
