package com.edu.transplantdataapi.entity.analysis;

import com.edu.transplantdataapi.entity.transplantdata.SurvivalResult;
import com.edu.transplantdataapi.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String factor;
    private String classFactor;

    @ManyToMany(mappedBy = "analysisResult", cascade = CascadeType.ALL)
    private List<SurvivalResult> dataset;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    public AnalysisResult(String factor,
                          String classFactor,
                          List<SurvivalResult> dataset) {
        this.factor = factor;
        this.classFactor = classFactor;
        this.dataset = dataset;
    }
}
