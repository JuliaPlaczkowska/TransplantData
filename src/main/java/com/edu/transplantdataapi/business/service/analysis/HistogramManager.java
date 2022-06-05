package com.edu.transplantdataapi.business.service.analysis;

import com.edu.transplantdataapi.dto.analysis.HistogramDatasetDto;
import com.edu.transplantdataapi.dto.analysis.HistogramDatasetsDto;
import com.edu.transplantdataapi.entities.transplantdata.SurvivalResult;
import com.edu.transplantdataapi.entities.enums.ClassFactor;
import com.edu.transplantdataapi.entities.enums.Factor;
import com.edu.transplantdataapi.business.utils.HistogramUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HistogramManager {

    public HistogramDatasetDto getHistogramData(Factor factor,
                                                ClassFactor classFactor,
                                                List<SurvivalResult> survivalResultList){
        List<String> labels = generateFactorLabels(factor);
        return HistogramDatasetDto.builder()
                .title(factor.name())
                .labels(labels)
                .datasets(generateDatasets(
                        survivalResultList,
                        factor,
                        classFactor,
                        labels
                ))
                .build();
    }


    private List<String> generateFactorLabels(Factor factor) {
        return switch (factor) {
            case matchHla -> HistogramUtils.getMatchHlaLabels();
            case mismatchHla, postRelapse -> HistogramUtils.getBooleanLabels();
            case antigen -> HistogramUtils.getAntigenLabels();
            case allele -> HistogramUtils.getAlleleLabels();
            case groupHla -> HistogramUtils.getGroupHlaLabels();
            case risk_group -> HistogramUtils.getRiskGroupLabels();
            case cd34perKg, cd3perKg -> HistogramUtils.getCdLabels();
        };
    }

    private ArrayList<HistogramDatasetsDto> generateDatasets(
            List<SurvivalResult> survivalResultList,
            Factor factor,
            ClassFactor classFactor,
            List<String> labels) {

        ArrayList<List<SurvivalResult>> byFactorList = new ArrayList<>();
        for (String label : labels) {
            byFactorList.add(survivalResultList.stream()
                    .filter(sr ->
                            String.valueOf(
                                            switch (factor) {
                                                case matchHla -> sr.getTransplant().getMatchHLA();
                                                case mismatchHla -> sr.getTransplant().isMismatchHLA();
                                                case antigen -> sr.getTransplant().getAntigen();
                                                case allele -> sr.getTransplant().getAllele();
                                                case groupHla -> sr.getTransplant().getGroup1HLA();
                                                case postRelapse -> sr.getTransplant().isPostRelapse();
                                                case risk_group -> sr.getTransplant().getRecipient().getRiskGroup();
                                                case cd34perKg -> sr.getTransplant().getCD34perKg();
                                                case cd3perKg -> sr.getTransplant().getCD3perKg();
                                            }
                                    )
                                    .equals(label)
                    ).collect(Collectors.toList())
            );
        }

        ArrayList<HistogramDatasetsDto> hdList = new ArrayList<>();

        switch (classFactor) {
            case ancRecovery -> {

                HistogramDatasetsDto hd1 = generateHistogramDatasets("<10");
                HistogramDatasetsDto hd2 = generateHistogramDatasets("10+");
                HistogramDatasetsDto hd3 = generateHistogramDatasets("15+");
                HistogramDatasetsDto hd4 = generateHistogramDatasets("20+");

                for (List<SurvivalResult> list : byFactorList) {

                    hd1.getData().add((double) list.stream()
                            .filter(sr ->
                                    sr.getAncRecovery() < 10
                            ).count());
                    hd2.getData().add((double) list.stream()
                            .filter(sr ->
                                    (sr.getAncRecovery() >= 10) && (sr.getAncRecovery() < 15)
                            ).count());
                    hd3.getData().add((double) list.stream()
                            .filter(sr ->
                                    (sr.getAncRecovery() >= 15) && (sr.getAncRecovery() < 20)
                            ).count());
                    hd4.getData().add((double) list.stream()
                            .filter(sr ->
                                    sr.getAncRecovery() >= 20
                            ).count());
                }

                hdList.add(hd1);
                hdList.add(hd2);
                hdList.add(hd3);
                hdList.add(hd4);

            }
            case pltRecovery -> {
                HistogramDatasetsDto hd1 = generateHistogramDatasets("<50");
                HistogramDatasetsDto hd2 = generateHistogramDatasets("50+");
                HistogramDatasetsDto hd3 = generateHistogramDatasets("100+");

                for (List<SurvivalResult> list : byFactorList) {

                    hd1.getData().add((double) list.stream()
                            .filter(sr ->
                                    sr.getPltRecovery() < 50
                            ).count());
                    hd2.getData().add((double) list.stream()
                            .filter(sr ->
                                    (sr.getPltRecovery() >= 50) && (sr.getPltRecovery() < 100)
                            ).count());
                    hd3.getData().add((double) list.stream()
                            .filter(sr ->
                                    (sr.getPltRecovery() >= 100)
                            ).count());
                }

                hdList.add(hd1);
                hdList.add(hd2);
                hdList.add(hd3);
            }
            case acuteGvHD_II_III_IV -> {
                HistogramDatasetsDto hd1 = new HistogramDatasetsDto();
                hd1.setLabel("no");
                hd1.setData(new ArrayList<>());
                HistogramDatasetsDto hd2 = new HistogramDatasetsDto();
                hd2.setLabel("yes");
                hd2.setData(new ArrayList<>());


                for (List<SurvivalResult> list : byFactorList) {

                    hd1.getData().add((double) list.stream()
                            .filter(sr ->
                                    !sr.isAcuteGvHD_II_III_IV()
                            ).count());
                    hd2.getData().add((double) list.stream()
                            .filter(SurvivalResult::isAcuteGvHD_II_III_IV
                            ).count());
                }

                hdList.add(hd1);
                hdList.add(hd2);
            }
            case acuteGvHD_III_IV -> {
                HistogramDatasetsDto hd1 = generateHistogramDatasets("no");
                HistogramDatasetsDto hd2 = generateHistogramDatasets("yes");

                for (List<SurvivalResult> list : byFactorList) {

                    hd1.getData().add((double) list.stream()
                            .filter(sr ->
                                    !sr.isAcuteGvHD_III_IV()
                            ).count());
                    hd2.getData().add((double) list.stream()
                            .filter(SurvivalResult::isAcuteGvHD_III_IV
                            ).count());

                }

                hdList.add(hd1);
                hdList.add(hd2);
            }
            case time_to_acuteGvHD -> {
                HistogramDatasetsDto hd1 = generateHistogramDatasets("<20");
                HistogramDatasetsDto hd2 = generateHistogramDatasets("20+");
                HistogramDatasetsDto hd3 = generateHistogramDatasets("100+");

                ArrayList<List<SurvivalResult>> updatedByFactorList = new ArrayList<>();

                for (List<SurvivalResult> survivalResults
                        : byFactorList) {

                    List<SurvivalResult> list = survivalResults.stream().filter(sr ->
                            sr.getTime_to_acuteGvHD_III_IV() < 10000
                    ).collect(Collectors.toList());

                    updatedByFactorList.add(list);

                    hd1.getData().add((double) list.stream()
                            .filter(sr ->
                                    sr.getTime_to_acuteGvHD_III_IV() < 50
                            ).count());
                    hd2.getData().add((double) list.stream()
                            .filter(sr ->
                                    (sr.getTime_to_acuteGvHD_III_IV() >= 50) && (sr.getTime_to_acuteGvHD_III_IV() < 100)
                            ).count());
                    hd3.getData().add((double) list.stream()
                            .filter(sr ->
                                    (sr.getTime_to_acuteGvHD_III_IV() >= 100) && (sr.getTime_to_acuteGvHD_III_IV() < 10000)
                            ).count());
                }

                byFactorList = updatedByFactorList;

                hdList.add(hd1);
                hdList.add(hd2);
                hdList.add(hd3);
            }
            case extensiveChronicGvHD -> {
                HistogramDatasetsDto hd1 = generateHistogramDatasets("no");
                HistogramDatasetsDto hd2 = generateHistogramDatasets("yes");

                for (List<SurvivalResult> list : byFactorList) {

                    hd1.getData().add((double) list.stream()
                            .filter(sr ->
                                    !sr.isExtensiveChronicGvHD()
                            ).count());
                    hd2.getData().add((double) list.stream()
                            .filter(SurvivalResult::isExtensiveChronicGvHD
                            ).count());

                }

                hdList.add(hd1);
                hdList.add(hd2);
            }
            case relapse -> {
                HistogramDatasetsDto hd1 = generateHistogramDatasets("no");
                HistogramDatasetsDto hd2 = generateHistogramDatasets("yes");

                for (List<SurvivalResult> list : byFactorList) {

                    hd1.getData().add((double) list.stream()
                            .filter(sr ->
                                    !sr.isRelapse()
                            ).count());
                    hd2.getData().add((double) list.stream()
                            .filter(SurvivalResult::isRelapse
                            ).count());

                }

                hdList.add(hd1);
                hdList.add(hd2);
            }
            case survivalTime -> {
                HistogramDatasetsDto hd1 = generateHistogramDatasets("<50");
                HistogramDatasetsDto hd2 = generateHistogramDatasets("50+");
                HistogramDatasetsDto hd3 = generateHistogramDatasets("100+");
                HistogramDatasetsDto hd4 = generateHistogramDatasets("1000+");

                ArrayList<List<SurvivalResult>> updatedByFactorList = new ArrayList<>();

                for (List<SurvivalResult> survivalResults : byFactorList) {

                    List<SurvivalResult> list = survivalResults.stream().filter(SurvivalResult::isSurvivalStatus
                    ).collect(Collectors.toList());

                    updatedByFactorList.add(list);

                    hd1.getData().add((double) list.stream()
                            .filter(sr ->
                                    sr.getSurvivalTime() < 50
                            ).count());
                    hd2.getData().add((double) list.stream()
                            .filter(sr ->
                                    (sr.getSurvivalTime() >= 50) && (sr.getSurvivalTime() < 100)
                            ).count());
                    hd3.getData().add((double) list.stream()
                            .filter(sr ->
                                    (sr.getSurvivalTime() >= 100) && (sr.getSurvivalTime() < 1000)
                            ).count());
                    hd4.getData().add((double) list.stream()
                            .filter(sr ->
                                    (sr.getSurvivalTime() >= 1000)
                            ).count());
                }

                byFactorList = updatedByFactorList;

                hdList.add(hd1);
                hdList.add(hd2);
                hdList.add(hd3);
                hdList.add(hd4);
            }
            case survivalStatus -> {
                HistogramDatasetsDto hd1 = generateHistogramDatasets("dead");
                HistogramDatasetsDto hd2 = generateHistogramDatasets("alive");

                for (List<SurvivalResult> list : byFactorList) {
                    hd1.getData().add((double) list.stream()
                            .filter(sr ->
                                    !sr.isSurvivalStatus()
                            ).count());
                    hd2.getData().add((double) list.stream()
                            .filter(SurvivalResult::isSurvivalStatus
                            ).count());
                }
                hdList.add(hd1);
                hdList.add(hd2);
            }
            default -> {
            }
        }
        for (int i = 0; i < byFactorList.size(); i++) {
            for (HistogramDatasetsDto hd : hdList) {
                hd.getData().set(i, hd.getData().get(i) / byFactorList.get(i).size() * 100);
            }
        }
        return hdList;
    }

    private HistogramDatasetsDto generateHistogramDatasets(String label) {
        HistogramDatasetsDto datasets = new HistogramDatasetsDto();
        datasets.setLabel(label);
        datasets.setData(new ArrayList<>());
        return datasets;
    }
}
