package com.edu.transplantdataapi.repository.analysis;

import com.edu.transplantdataapi.entity.analysis.Analysis;
import com.edu.transplantdataapi.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepo extends JpaRepository<Analysis, Long> {
    Boolean existsByAuthor(User author);

    Analysis findByAuthor(User author);
}
