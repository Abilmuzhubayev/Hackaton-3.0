package com.asphyxia.routList.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "subtask")
public class Subtask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subtask_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plan_id", referencedColumnName = "plan_id")
    private Plan plan;

    @Column(name = "category")
    private String category;

    @Column(name = "time")
    private Timestamp time;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private Status status;

}
