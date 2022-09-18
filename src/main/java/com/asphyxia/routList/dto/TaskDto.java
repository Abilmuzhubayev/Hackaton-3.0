package com.asphyxia.routList.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TaskDto {
    private String taskCategory;

    private Long taskId;
    private String name;
    private Timestamp time;
    private String status;
}
