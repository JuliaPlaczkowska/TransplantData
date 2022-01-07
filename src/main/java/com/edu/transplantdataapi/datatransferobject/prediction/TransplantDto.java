package com.edu.transplantdataapi.datatransferobject.prediction;

import com.edu.transplantdataapi.entity.Donor;
import com.edu.transplantdataapi.entity.Recipient;
import com.edu.transplantdataapi.entity.Transplant;
import lombok.Data;

@Data
public class TransplantDto {


    private int matchHLA;
    private boolean mismatchHLA;
    private int antigen;
    private int allele;
    private String group1HLA;
    private boolean postRelapse;
    private double CD34perKg;
    private double CD3perKg;

    public TransplantDto(Transplant transplant) {
        this.matchHLA = transplant.getMatchHLA();
        this.mismatchHLA = transplant.getMismatchHLA();
        this.antigen = transplant.getAntigen();
        this.allele = transplant.getAllele();
        this.group1HLA = transplant.getGroup1HLA();
        this.postRelapse = transplant.getPostRelapse();
        this.CD3perKg = transplant.getCD3perKg();
        this.CD34perKg = transplant.getCD34perKg();
    }
}
