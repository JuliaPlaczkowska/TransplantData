package com.edu.transplantdataapi.datatransferobject;

import com.edu.transplantdataapi.entity.transplantdata.Donor;
import com.edu.transplantdataapi.entity.transplantdata.Patient;

public class DonorDto {


    private PatientDto patient;

    private String stemCellSource;

    public DonorDto(Patient patient, String stemCellSource) {
        this.patient = new PatientDto(patient);
        this.stemCellSource = stemCellSource;
    }

    public DonorDto(Donor donor) {
        this.patient = new PatientDto(donor.getPatient());
        this.stemCellSource = donor.getStemCellSource();

    }

    public DonorDto() {

    }

    public Donor convertToDonor() {
        return new Donor(
                this.patient.convertToPatient(),
                this.stemCellSource);
    }

    public PatientDto getPatient() {
        return patient;
    }


    public void setPatient(Patient patient) {
        this.patient = new PatientDto(patient);
    }

    public void setPatient(PatientDto patient) {
        this.patient = patient;
    }

    public String getStemCellSource() {
        return stemCellSource;
    }

    public void setStemCellSource(String stemCellSource) {
        this.stemCellSource = stemCellSource;
    }

}
