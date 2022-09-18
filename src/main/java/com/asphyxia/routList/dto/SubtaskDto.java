package com.asphyxia.routList.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SubtaskDto {
    private Long id;
    private String category;
    private Timestamp time;
    private Long statusId;
    private Long planId;
    private int confirmed;
}
