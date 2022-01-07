package com.edu.transplantdataapi.datatransferobject;

import com.edu.transplantdataapi.entity.Donor;
import com.edu.transplantdataapi.entity.Patient;

public class DonorDto {


    private Patient patient;

    private String stemCellSource;

    public DonorDto(Patient patient, String stemCellSource) {
        this.patient = patient;
        this.stemCellSource = stemCellSource;
    }

    public DonorDto(Donor donor) {
        this.patient = donor.getPatient();
        this.stemCellSource = stemCellSource;

    }

    public DonorDto() {

    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getStemCellSource() {
        return stemCellSource;
    }

    public void setStemCellSource(String stemCellSource) {
        this.stemCellSource = stemCellSource;
    }

}
