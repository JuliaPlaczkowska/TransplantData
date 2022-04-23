package com.edu.transplantdataapi.service.analysis;

import com.edu.transplantdataapi.entity.analysis.ChiSquare;
import com.edu.transplantdataapi.repository.analysis.ChiSquareRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChiSquareManager {

    private ChiSquareRepo chiSquareRepo;


}
