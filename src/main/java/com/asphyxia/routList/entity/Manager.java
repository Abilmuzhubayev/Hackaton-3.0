package com.asphyxia.routList.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

}
