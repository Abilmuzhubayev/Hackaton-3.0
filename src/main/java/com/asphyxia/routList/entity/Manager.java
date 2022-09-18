package com.asphyxia.routList.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

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

    @OneToMany(mappedBy = "manager")
    private List<Route> routeList;

    public Route getRouteById(Long routeId) {
        Route route = new Route();
        for (Route oneRoute : routeList) {
            if (oneRoute.getId().equals(routeId)) {
                route = oneRoute;
                break;
            }
        }
        return route;
    }

}
