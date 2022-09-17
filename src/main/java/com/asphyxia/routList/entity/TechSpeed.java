package com.asphyxia.routList.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tech_speed")
@Data
public class TechSpeed {
    @Id
    @Column(name = "tech_speed_id")
    private Long id;

    @Column(name = "description")
    private String description;
}
