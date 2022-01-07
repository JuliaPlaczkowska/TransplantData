package com.edu.transplantdataapi.entity.analysis;

import javax.persistence.*;
import java.util.List;

@Entity
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    @OneToMany(mappedBy = "analysis", cascade = CascadeType.ALL)
    private List<AnalysisResult> results;

    public Analysis() {
    }

    public Analysis(int number, List<AnalysisResult> results) {
        this.number = number;
        this.results = results;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public List<AnalysisResult> getResults() {
        return results;
    }

    public void setResults(List<AnalysisResult> results) {
        this.results = results;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
