package com.asphyxia.routList.dto;

import com.asphyxia.routList.entity.LocoSubmission;
import lombok.Data;

@Data
public class PlanDto {
    private Long id;
    private LocoAcceptanceDto locoAcceptanceDto;
    private LocoSubmissionDto locoSubmissionDto;
}
