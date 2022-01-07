package com.edu.transplantdataapi.datatransferobject.analysis;

import lombok.Data;

import java.util.ArrayList;

@Data
public class HistogramDatasets {
    private String label;
    private ArrayList<Double> data;
}
