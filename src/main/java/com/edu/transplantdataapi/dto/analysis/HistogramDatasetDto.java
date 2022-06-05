package com.edu.transplantdataapi.dto.analysis;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class HistogramDatasetDto {
    private String title;
    private List<String> labels;
    private ArrayList<HistogramDatasetsDto> datasets;
}

