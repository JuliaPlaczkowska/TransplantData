package com.edu.transplantdataapi.dto.patient;

import com.edu.transplantdataapi.entities.transplantdata.Donor;
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

    public DonorDto(Donor donor) {
        this.patient = new PatientDto(donor.getPatient());
        this.stemCellSource = donor.getStemCellSource();
    }
}
