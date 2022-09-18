package com.asphyxia.routList.dto;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class LocoAcceptanceDto {
    private int id;
    private Timestamp time;
    private Double recuperationCounter;
    private Double electricCounter;
}
