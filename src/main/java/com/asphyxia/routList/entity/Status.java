package com.asphyxia.routList.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Long id;

    @Column(name = "description")
    private String statusDescription;

    public static String finished = "success";
    public static String pending = "pending";
    public static String inFuture = "future";

    public Status() {
        statusDescription = inFuture;
    }
}