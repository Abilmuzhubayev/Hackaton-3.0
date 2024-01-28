package com.asphyxia.routList.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class LocoSubmissionDto {
    private Long id;
    private Timestamp time;
    private Double recuperationCounter;
    private Double electricCounter;
    private List<Long> precautionsId;
    private List<Long> consumptionsId;
    private List<Long> speedsId;
    private String status;
    private Long planId;
}
