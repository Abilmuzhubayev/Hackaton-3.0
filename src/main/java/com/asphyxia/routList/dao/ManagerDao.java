package com.asphyxia.routList.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ManagerDao {

    @Autowired
    private EntityManager entityManager;



}
