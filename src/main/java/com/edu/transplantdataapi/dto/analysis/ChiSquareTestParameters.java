package com.edu.transplantdataapi.dto.analysis;

import lombok.Data;

@Data
public class ChiSquareTestParameters {
    String factor;
    String classFactor;
    double significance;
}
