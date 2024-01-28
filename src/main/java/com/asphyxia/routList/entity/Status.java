package com.asphyxia.routList.entity;

import lombok.Data;

import javax.persistence.*;

public class Status {
    public static String finished = "success";
    public static String pending = "pending";
    public static String inFuture = "future";
}