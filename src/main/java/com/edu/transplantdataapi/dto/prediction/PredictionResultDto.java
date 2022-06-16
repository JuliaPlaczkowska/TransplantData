package com.edu.transplantdataapi.dto.prediction;

import lombok.Data;

import java.util.List;

@Data
public class PredictionResultDto {
    private List<String> tree;
    private String classifiedAs;
    private String distribution;
}
