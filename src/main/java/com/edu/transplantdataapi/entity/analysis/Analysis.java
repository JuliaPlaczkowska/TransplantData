package com.edu.transplantdataapi.entity.analysis;
import com.edu.transplantdataapi.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    @OneToMany(mappedBy = "analysis", cascade = CascadeType.ALL)
    private List<AnalysisResult> results;

    @ManyToOne
    @JoinTable(name = "analysis_user",
    joinColumns = @JoinColumn(name = "analysis_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User author;
}
