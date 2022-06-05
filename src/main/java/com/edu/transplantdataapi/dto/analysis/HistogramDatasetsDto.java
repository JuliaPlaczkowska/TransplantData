package com.edu.transplantdataapi.dto.analysis;

import lombok.Data;

import java.util.ArrayList;

@Data
public class HistogramDatasetsDto {
    private String label;
    private ArrayList<Double> data;
}
