package com.edu.transplantdataapi.dto.analysis;

import com.edu.transplantdataapi.entity.transplantdata.SurvivalResult;
import com.edu.transplantdataapi.enums.ClassFactor;
import com.edu.transplantdataapi.enums.Factor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class HistogramDatasetDto {
    private String title;
    private List<String> labels;
    private ArrayList<HistogramDatasets> datasets;
}

