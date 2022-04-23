package com.edu.transplantdataapi.repository.analysis;

import com.edu.transplantdataapi.entity.analysis.Analysis;
import com.edu.transplantdataapi.entity.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepo extends CrudRepository<Analysis, Long> {
    Boolean existsByAuthor(User author);
    Analysis findByAuthor(User author);
}
