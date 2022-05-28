package com.edu.transplantdataapi.dto.patient;

import com.edu.transplantdataapi.entities.transplantdata.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipientDto {


    private PatientDto patient;

    private String bloodRh;
    private double bodyMass;
    private String disease;
    private String diseaseGroup;
    private String riskGroup;


    public RecipientDto(Patient patient, String bloodRh,
                        double bodyMass,
                        String disease, String diseaseGroup,
                        String riskGroup) {
        this.patient = new PatientDto(patient);
        this.bloodRh = bloodRh;
        this.bodyMass = bodyMass;
        this.disease = disease;
        this.diseaseGroup = diseaseGroup;
        this.riskGroup = riskGroup;
    }
}
