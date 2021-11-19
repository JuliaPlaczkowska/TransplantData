package com.edu.transplantdataapi.service;

import javax.persistence.*;

@Entity
public class SurvivalResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Transplant transplant;

    private double ancRecovery;
    private double pltRecovery;
    private boolean acuteGvHD_II_III_IV;
    private boolean acuteGvHD_III_IV;
    private double time_to_acuteGvHD_III_IV;
    private boolean extensiveChronicGvHD;
    private boolean relapse;
    private double survivalTime;
    private boolean survivalStatus;

    public SurvivalResult(Transplant transplant,
                          double ancRecovery,
                          double pltRecovery,
                          boolean acuteGvHD_II_III_IV,
                          boolean acuteGvHD_III_IV,
                          double time_to_acuteGvHD_III_IV,
                          boolean extensiveChronicGvHD,
                          boolean relapse,
                          double survivalTime,
                          boolean survivalStatus) {
        this.transplant = transplant;
        this.ancRecovery = ancRecovery;
        this.pltRecovery = pltRecovery;
        this.acuteGvHD_II_III_IV = acuteGvHD_II_III_IV;
        this.acuteGvHD_III_IV = acuteGvHD_III_IV;
        this.time_to_acuteGvHD_III_IV = time_to_acuteGvHD_III_IV;
        this.extensiveChronicGvHD = extensiveChronicGvHD;
        this.relapse = relapse;
        this.survivalTime = survivalTime;
        this.survivalStatus = survivalStatus;
    }

    public SurvivalResult() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transplant getTransplant() {
        return transplant;
    }

    public void setTransplant(Transplant transplant) {
        this.transplant = transplant;
    }

    public double getAncRecovery() {
        return ancRecovery;
    }

    public void setAncRecovery(double ancRecovery) {
        this.ancRecovery = ancRecovery;
    }

    public double getPltRecovery() {
        return pltRecovery;
    }

    public void setPltRecovery(double pltRecovery) {
        this.pltRecovery = pltRecovery;
    }

    public boolean isAcuteGvHD_II_III_IV() {
        return acuteGvHD_II_III_IV;
    }

    public void setAcuteGvHD_II_III_IV(boolean acuteGvHD_II_III_IV) {
        this.acuteGvHD_II_III_IV = acuteGvHD_II_III_IV;
    }

    public boolean isAcuteGvHD_III_IV() {
        return acuteGvHD_III_IV;
    }

    public void setAcuteGvHD_III_IV(boolean acuteGvHD_III_IV) {
        this.acuteGvHD_III_IV = acuteGvHD_III_IV;
    }

    public double getTime_to_acuteGvHD_III_IV() {
        return time_to_acuteGvHD_III_IV;
    }

    public void setTime_to_acuteGvHD_III_IV(double time_to_acuteGvHD_III_IV) {
        this.time_to_acuteGvHD_III_IV = time_to_acuteGvHD_III_IV;
    }

    public boolean isExtensiveChronicGvHD() {
        return extensiveChronicGvHD;
    }

    public void setExtensiveChronicGvHD(boolean extensiveChronicGvHD) {
        this.extensiveChronicGvHD = extensiveChronicGvHD;
    }

    public boolean isRelapse() {
        return relapse;
    }

    public void setRelapse(boolean relapse) {
        this.relapse = relapse;
    }

    public double getSurvivalTime() {
        return survivalTime;
    }

    public void setSurvivalTime(double survivalTime) {
        this.survivalTime = survivalTime;
    }

    public boolean isSurvivalStatus() {
        return survivalStatus;
    }

    public void setSurvivalStatus(boolean survivalStatus) {
        this.survivalStatus = survivalStatus;
    }



}
