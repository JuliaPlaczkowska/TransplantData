package com.edu.transplantdataapi.dto.patient;

import com.edu.transplantdataapi.entity.transplantdata.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {

    private Integer number;
    private double age;
    private String bloodABO;
    private String presenceOfCMV;

    public PatientDto(Patient patient) {
        this.number = patient.getNumber();
        this.age = patient.getAge();
        this.bloodABO = patient.getBloodABO();
        this.presenceOfCMV = patient.getPresenceOfCMV();
    }
}
