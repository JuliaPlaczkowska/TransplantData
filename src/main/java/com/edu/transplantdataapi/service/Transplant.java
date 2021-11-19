package com.edu.transplantdataapi.service;


import javax.persistence.*;

@Entity
public class Transplant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Donor donor;

    @OneToOne
    private Recipient recipient;

    private int matchHLA;
    private boolean mismatchHLA;
    private int antigen;
    private int allele;
    private String group1HLA;
    private boolean postRelapse;
    private double CD34perKg;
    private double CD3perKg;

    //private User user;


    public Transplant(Donor donor,
                      Recipient recipient,
                      int matchHLA,
                      boolean mismatchHLA,
                      int antigen,
                      int allele,
                      String group1HLA,
                      boolean postRelapse,
                      double CD34perKg,
                      double CD3perKg) {
        this.donor = donor;
        this.recipient = recipient;
        this.matchHLA = matchHLA;
        this.mismatchHLA = mismatchHLA;
        this.antigen = antigen;
        this.allele = allele;
        this.group1HLA = group1HLA;
        this.postRelapse = postRelapse;
        this.CD34perKg = CD34perKg;
        this.CD3perKg = CD3perKg;
    }

    public Transplant() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

//    public Donor getDonor() {
//        return donor;
//    }
//
//    public void setDonor(Donor donor) {
//        this.donor = donor;
//    }
//
//    public Recipient getRecipient() {
//        return recipient;
//    }
//
//    public void setRecipient(Recipient recipient) {
//        this.recipient = recipient;
//    }

    public int getMatchHLA() {
        return matchHLA;
    }

    public void setMatchHLA(int matchHLA) {
        this.matchHLA = matchHLA;
    }

    public boolean isMismatchHLA() {
        return mismatchHLA;
    }

    public void setMismatchHLA(boolean mismatchHLA) {
        this.mismatchHLA = mismatchHLA;
    }

    public int getAntigen() {
        return antigen;
    }

    public void setAntigen(int antigen) {
        this.antigen = antigen;
    }

    public int getAllele() {
        return allele;
    }

    public void setAllele(int allele) {
        this.allele = allele;
    }

    public String getGroup1HLA() {
        return group1HLA;
    }

    public void setGroup1HLA(String group1HLA) {
        this.group1HLA = group1HLA;
    }

    public boolean isPostRelapse() {
        return postRelapse;
    }

    public void setPostRelapse(boolean postRelapse) {
        this.postRelapse = postRelapse;
    }

    public double getCD34perKg() {
        return CD34perKg;
    }

    public void setCD34perKg(double CD34perKg) {
        this.CD34perKg = CD34perKg;
    }

    public double getCD3perKg() {
        return CD3perKg;
    }

    public void setCD3perKg(double CD3perKg) {
        this.CD3perKg = CD3perKg;
    }
}
