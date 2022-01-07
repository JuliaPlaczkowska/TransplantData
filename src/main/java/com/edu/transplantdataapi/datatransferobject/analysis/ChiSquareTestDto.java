package com.edu.transplantdataapi.datatransferobject.analysis;

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
