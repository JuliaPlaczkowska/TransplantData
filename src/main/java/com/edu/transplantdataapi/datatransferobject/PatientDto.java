package com.edu.transplantdataapi.datatransferobject;

import com.edu.transplantdataapi.entity.transplantdata.Patient;
import lombok.Data;

@Data
public class PatientDto {

    private Integer number;
    private double age;
    private String bloodABO;
    private String presenceOfCMV;

    public PatientDto(Integer number, double age, String bloodABO, String presenceOfCMV) {
        this.number = number;
        this.age = age;
        this.bloodABO = bloodABO;
        this.presenceOfCMV = presenceOfCMV;
    }

    public PatientDto(Patient patient) {
        this.number = patient.getNumber();
        this.age = patient.getAge();
        this.bloodABO = patient.getBloodABO();
        this.presenceOfCMV = patient.getPresenceOfCMV();
    }

    public Patient convertToPatient() {
        return new Patient(number, age, bloodABO, presenceOfCMV);
    }
}
