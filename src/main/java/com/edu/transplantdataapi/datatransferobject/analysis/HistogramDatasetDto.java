package com.edu.transplantdataapi.datatransferobject.analysis;

import com.edu.transplantdataapi.entity.transplantdata.SurvivalResult;
import com.edu.transplantdataapi.enums.ClassFactor;
import com.edu.transplantdataapi.enums.Factor;
import lombok.Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class HistogramDatasetDto {

    private String title;
    private List<String> labels;
    private ArrayList<HistogramDatasets> datasets;

    public HistogramDatasetDto(String factor, String classFactor,
                               List<SurvivalResult> survivalResultList) {

        this.title = classFactor;
        this.labels = generateFactorLabels(Factor.valueOf(factor.trim()));
        this.datasets = generateDatasets(
                survivalResultList,
                Factor.valueOf(factor.trim()),
                ClassFactor.valueOf(classFactor.trim()));

    }

    private ArrayList<String> generateFactorLabels(Factor factor) {
        return switch (factor) {
            case matchHla -> new ArrayList<>(Arrays.asList("10", "9", "8", "7"));
            case mismatchHla, postRelapse -> new ArrayList<>(Arrays.asList("true", "false"));
            case antigen -> new ArrayList<>(Arrays.asList("0", "1", "2", "3"));
            case allele -> new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4"));
            case groupHla -> new ArrayList<>(Arrays.asList(
                    "matched",
                    "mismatched",
                    "one_antigen",
                    "one_allel",
                    "DRB1_cell",
                    "two_diffs",
                    "three_diffs"
            ));
            case risk_group -> new ArrayList<>(Arrays.asList("low", "high"));
            case cd34perKg, cd3perKg -> new ArrayList<>(Arrays.asList(
                    "<5,0",
                    "5,0+",
                    "10+",
                    "15+",
                    "20+",
                    "25+"
            ));
        };
    }

    private ArrayList<HistogramDatasets> generateDatasets(
            List<SurvivalResult> survivalResultList,
            Factor factor,
            ClassFactor classFactor) {

        ArrayList<List<SurvivalResult>> byFactorList = new ArrayList<>();
        for (String label : this.labels) {
            byFactorList.add(survivalResultList.stream()
                    .filter(sr ->
                            String.valueOf(
                                    switch (factor) {
                                        case matchHla -> sr.getTransplant().getMatchHLA();
                                        case mismatchHla -> sr.getTransplant().getMismatchHLA();
                                        case antigen -> sr.getTransplant().getAntigen();
                                        case allele -> sr.getTransplant().getAllele();
                                        case groupHla -> sr.getTransplant().getGroup1HLA();
                                        case postRelapse -> sr.getTransplant().getPostRelapse();
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
            case ancRecovery: {

                HistogramDatasets hd1 = new HistogramDatasets();
                hd1.setLabel("<10");
                hd1.setData(new ArrayList<>());
                HistogramDatasets hd2 = new HistogramDatasets();
                hd2.setLabel("10+");
                hd2.setData(new ArrayList<>());
                HistogramDatasets hd3 = new HistogramDatasets();
                hd3.setLabel("15+");
                hd3.setData(new ArrayList<>());
                HistogramDatasets hd4 = new HistogramDatasets();
                hd4.setLabel("20+");
                hd4.setData(new ArrayList<>());

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

                break;
            }

            case pltRecovery: {
                HistogramDatasets hd1 = new HistogramDatasets();
                hd1.setLabel("<50");
                hd1.setData(new ArrayList<>());
                HistogramDatasets hd2 = new HistogramDatasets();
                hd2.setLabel("50+");
                hd2.setData(new ArrayList<>());
                HistogramDatasets hd3 = new HistogramDatasets();
                hd3.setLabel("100+");
                hd3.setData(new ArrayList<>());

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
                break;
            }

            case acuteGvHD_II_III_IV: {
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
                            .filter(sr ->
                                    sr.isAcuteGvHD_II_III_IV()
                            ).count());

                }

                hdList.add(hd1);
                hdList.add(hd2);
                break;
            }
            case acuteGvHD_III_IV: {
                HistogramDatasets hd1 = new HistogramDatasets();
                hd1.setLabel("no");
                hd1.setData(new ArrayList<>());
                HistogramDatasets hd2 = new HistogramDatasets();
                hd2.setLabel("yes");
                hd2.setData(new ArrayList<>());


                for (List<SurvivalResult> list : byFactorList) {

                    hd1.getData().add((double) list.stream()
                            .filter(sr ->
                                    !sr.isAcuteGvHD_III_IV()
                            ).count());
                    hd2.getData().add((double) list.stream()
                            .filter(sr ->
                                    sr.isAcuteGvHD_III_IV()
                            ).count());

                }

                hdList.add(hd1);
                hdList.add(hd2);
                break;
            }
            case time_to_acuteGvHD: {
                HistogramDatasets hd1 = new HistogramDatasets();
                hd1.setLabel("<20");
                hd1.setData(new ArrayList<>());
                HistogramDatasets hd2 = new HistogramDatasets();
                hd2.setLabel("20+");
                hd2.setData(new ArrayList<>());
                HistogramDatasets hd3 = new HistogramDatasets();
                hd3.setLabel("100+");
                hd3.setData(new ArrayList<>());

                ArrayList<List<SurvivalResult>> updatedByFactorList = new ArrayList<>();

                for (int i = 0; i < byFactorList.size(); i++) {

                    List<SurvivalResult> list = byFactorList.get(i).stream().filter(sr ->
                            sr.getTime_to_acuteGvHD_III_IV() < 10000
                    ).collect(Collectors.toList());

                    updatedByFactorList.add( list);

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
                break;
            }
            case extensiveChronicGvHD: {
                HistogramDatasets hd1 = new HistogramDatasets();
                hd1.setLabel("no");
                hd1.setData(new ArrayList<>());
                HistogramDatasets hd2 = new HistogramDatasets();
                hd2.setLabel("yes");
                hd2.setData(new ArrayList<>());

                for (List<SurvivalResult> list : byFactorList) {

                    hd1.getData().add((double) list.stream()
                            .filter(sr ->
                                    !sr.isExtensiveChronicGvHD()
                            ).count());
                    hd2.getData().add((double) list.stream()
                            .filter(sr ->
                                    sr.isExtensiveChronicGvHD()
                            ).count());

                }

                hdList.add(hd1);
                hdList.add(hd2);
                break;
            }
            case relapse: {
                HistogramDatasets hd1 = new HistogramDatasets();
                hd1.setLabel("no");
                hd1.setData(new ArrayList<>());
                HistogramDatasets hd2 = new HistogramDatasets();
                hd2.setLabel("yes");
                hd2.setData(new ArrayList<>());

                for (List<SurvivalResult> list : byFactorList) {

                    hd1.getData().add((double) list.stream()
                            .filter(sr ->
                                    !sr.isRelapse()
                            ).count());
                    hd2.getData().add((double) list.stream()
                            .filter(sr ->
                                    sr.isRelapse()
                            ).count());

                }

                hdList.add(hd1);
                hdList.add(hd2);
                break;
            }
            case survivalTime: {
                HistogramDatasets hd1 = new HistogramDatasets();
                hd1.setLabel("<50");
                hd1.setData(new ArrayList<>());
                HistogramDatasets hd2 = new HistogramDatasets();
                hd2.setLabel("50+");
                hd2.setData(new ArrayList<>());
                HistogramDatasets hd3 = new HistogramDatasets();
                hd3.setLabel("100+");
                hd3.setData(new ArrayList<>());
                HistogramDatasets hd4 = new HistogramDatasets();
                hd4.setLabel("1000+");
                hd4.setData(new ArrayList<>());

                ArrayList<List<SurvivalResult>> updatedByFactorList = new ArrayList<>();

                for (int i = 0; i < byFactorList.size(); i++) {

                    List<SurvivalResult> list = byFactorList.get(i).stream().filter(sr ->
                            sr.isSurvivalStatus()
                    ).collect(Collectors.toList());

                    updatedByFactorList.add( list);

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
                break;
            }
            case survivalStatus: {
                HistogramDatasets hd1 = new HistogramDatasets();
                hd1.setLabel("dead");
                hd1.setData(new ArrayList<>());
                HistogramDatasets hd2 = new HistogramDatasets();
                hd2.setLabel("alive");
                hd2.setData(new ArrayList<>());

                for (List<SurvivalResult> list : byFactorList) {

                    hd1.getData().add((double) list.stream()
                            .filter(sr ->
                                    !sr.isSurvivalStatus()
                            ).count());
                    hd2.getData().add((double) list.stream()
                            .filter(sr ->
                                    sr.isSurvivalStatus()
                            ).count());
                }
                hdList.add(hd1);
                hdList.add(hd2);
                break;
            }
            default:
        }

        for (int i = 0; i < byFactorList.size(); i++) {
            for (HistogramDatasets hd : hdList) {
                hd.getData().set(i, hd.getData().get(i) / byFactorList.get(i).size() * 100);
            }
        }

        return hdList;
    }
}

