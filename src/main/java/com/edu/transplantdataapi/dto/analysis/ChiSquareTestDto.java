package com.edu.transplantdataapi.dto.analysis;

import lombok.Data;

@Data
public class ChiSquareTestDto {
    private double pValue;
    private boolean rejected;

    public ChiSquareTestDto(double pValue, boolean rejected) {
        this.pValue = pValue;
        this.rejected = rejected;
    }
}
