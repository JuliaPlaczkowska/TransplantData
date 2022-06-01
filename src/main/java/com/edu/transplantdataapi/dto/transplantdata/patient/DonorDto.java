package com.edu.transplantdataapi.dto.transplantdata.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonorDto {
    private PatientDto patient;
    private String stemCellSource;
}
