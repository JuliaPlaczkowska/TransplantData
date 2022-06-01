package com.edu.transplantdataapi.dto.prediction;

import lombok.Data;

@Data
public class PredictionResultDto {
    private String classifiedAs;
    private String distribution;
}
