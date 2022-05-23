package com.edu.transplantdataapi.dto.analysis;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChiSquareTestDto {
    private double pValue;
    private boolean rejected;
}
