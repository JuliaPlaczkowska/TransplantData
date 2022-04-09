package com.edu.transplantdataapi.entity.analysis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    @OneToMany(mappedBy = "analysis", cascade = CascadeType.ALL)
    private List<AnalysisResult> results;

    public Analysis(int number, List<AnalysisResult> results) {
        this.number = number;
        this.results = results;
    }
}
