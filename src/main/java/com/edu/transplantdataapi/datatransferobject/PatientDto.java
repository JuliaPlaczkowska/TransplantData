package com.edu.transplantdataapi.datatransferobject;

import com.edu.transplantdataapi.entity.Patient;
import lombok.Data;

@Data
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
