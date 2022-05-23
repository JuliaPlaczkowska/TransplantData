package com.edu.transplantdataapi.dto.analysis;

import com.edu.transplantdataapi.dto.user.UserDto;
import com.edu.transplantdataapi.entity.transplantdata.SurvivalResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalysisResultDto {
    private String factor;
    private String classFactor;
    private List<SurvivalResult> dataset;
    private UserDto author;
}
