package com.edu.transplantdataapi.dto.transplantdata.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private long number;
    private double age;
    private String bloodABO;
    private String presenceOfCMV;
}
