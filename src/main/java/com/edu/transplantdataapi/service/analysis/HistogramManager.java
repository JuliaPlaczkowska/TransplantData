package com.edu.transplantdataapi.service.analysis;

import com.edu.transplantdataapi.dto.analysis.HistogramDatasetDto;
import com.edu.transplantdataapi.dto.analysis.HistogramDatasets;
import com.edu.transplantdataapi.entity.transplantdata.SurvivalResult;
import com.edu.transplantdataapi.enums.ClassFactor;
import com.edu.transplantdataapi.enums.Factor;
import com.edu.transplantdataapi.utils.HistogramUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

    private ArrayList<HistogramDatasets> generateDatasets(
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

        ArrayList<HistogramDatasets> hdList = new ArrayList<HistogramDatasets>();

        switch (classFactor) {
            case ancRecovery -> {

                HistogramDatasets hd1 = generateHistogramDatasets("<10");
                HistogramDatasets hd2 = generateHistogramDatasets("10+");
                HistogramDatasets hd3 = generateHistogramDatasets("15+");
                HistogramDatasets hd4 = generateHistogramDatasets("20+");

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
                HistogramDatasets hd1 = generateHistogramDatasets("<50");
                HistogramDatasets hd2 = generateHistogramDatasets("50+");
                HistogramDatasets hd3 = generateHistogramDatasets("100+");

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
                HistogramDatasets hd1 = new HistogramDatasets();
                hd1.setLabel("no");
                hd1.setData(new ArrayList<>());
                HistogramDatasets hd2 = new HistogramDatasets();
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
                HistogramDatasets hd1 = generateHistogramDatasets("no");
                HistogramDatasets hd2 = generateHistogramDatasets("yes");

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
                HistogramDatasets hd1 = generateHistogramDatasets("<20");
                HistogramDatasets hd2 = generateHistogramDatasets("20+");
                HistogramDatasets hd3 = generateHistogramDatasets("100+");

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
                HistogramDatasets hd1 = generateHistogramDatasets("no");
                HistogramDatasets hd2 = generateHistogramDatasets("yes");

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
                HistogramDatasets hd1 = generateHistogramDatasets("no");
                HistogramDatasets hd2 = generateHistogramDatasets("yes");

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
                HistogramDatasets hd1 = generateHistogramDatasets("<50");
                HistogramDatasets hd2 = generateHistogramDatasets("50+");
                HistogramDatasets hd3 = generateHistogramDatasets("100+");
                HistogramDatasets hd4 = generateHistogramDatasets("1000+");

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
                HistogramDatasets hd1 = generateHistogramDatasets("dead");
                HistogramDatasets hd2 = generateHistogramDatasets("alive");

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
            for (HistogramDatasets hd : hdList) {
                hd.getData().set(i, hd.getData().get(i) / byFactorList.get(i).size() * 100);
            }
        }
        return hdList;
    }

    private HistogramDatasets generateHistogramDatasets(String label) {
        HistogramDatasets datasets = new HistogramDatasets();
        datasets.setLabel(label);
        datasets.setData(new ArrayList<>());
        return datasets;
    }
}
