package com.edu.transplantdataapi.business.ml;

import com.edu.transplantdataapi.dto.prediction.PredictionResultDto;
import lombok.extern.slf4j.Slf4j;
import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Slf4j
public class ClassificationTreeAlgorithm {

    private Instances dataTrain;
    private final Instances dataTest;
    private final J48 tree = new J48();

    public ClassificationTreeAlgorithm(String arffTrain, String arffTest) throws Exception {
        dataTrain = arffToInstances(arffTrain);
        dataTest = arffToInstances(arffTest);
    }

    public Instances arffToInstances(String arff) throws Exception {
        ConverterUtils.DataSource source1 = new ConverterUtils.DataSource(arff);
        return source1.getDataSet();
    }

    public List<String> getTree() throws Exception {
        selectFeatures(dataTrain);
        tree.buildClassifier(dataTrain);
        Evaluation eval = new Evaluation(dataTrain);
        selectFeatures(dataTest);
        eval.evaluateModel(tree, dataTest);

        List<String> results = evaluationSummary();
        String stringTree = tree.toString();
        String[] treeLines = stringTree.split("\n");
        results.addAll(Arrays.asList(treeLines));
        return results;
    }

    public List<String> evaluationSummary() throws Exception {

        dataTrain.setClassIndex(dataTrain.numAttributes() - 1);

        Evaluation eval = new Evaluation(dataTrain);
        eval.crossValidateModel(
                tree,
                dataTrain,
                10,
                new Random(1));

        //	output collected predictions
        String evaluationSummary = eval.toSummaryString();

        return Arrays
                .stream(evaluationSummary.split("\n"))
                .collect(Collectors.toList());
    }

//    public String predict() throws Exception {
//
//        dataTrain.setClassIndex(dataTrain.numAttributes() - 1);
//        dataTest.setClassIndex(dataTest.numAttributes() - 1);
//        tree.buildClassifier(dataTrain);
//
//        ArrayList<String> dane = new ArrayList();
//        String l1 = "# - actual - predicted - distribution (0/1)" + "\n";
//        dane.add(l1);
//
//        for (int i = 0; i < dataTest.numInstances(); i++) {
//            double pred = tree.classifyInstance(dataTest.instance(i));
//            double[] dist = tree.distributionForInstance(dataTest.instance(i));
//
//            l1 = (i + 1) + " -	";
//
//            l1 = l1 + dataTest.instance(i).toString(dataTest.classIndex()) + " -      ";
//            l1 = l1 + dataTest.classAttribute().value((int) pred) + " -        ";
//
//            l1 = l1 + Utils.arrayToString(dist);
//
//            l1 = l1 + '\n';
//            dane.add(l1);
//        }
//        return dane.toString();
//    }

    public PredictionResultDto predict() throws Exception {

        dataTrain.setClassIndex(dataTrain.numAttributes() - 1);
        dataTest.setClassIndex(dataTest.numAttributes() - 1);
        tree.buildClassifier(dataTrain);

        PredictionResultDto result = new PredictionResultDto();
        Instance instance = dataTest.instance(0);

        double pred = tree.classifyInstance(instance);
        double[] dist = tree.distributionForInstance(instance);
        result.setClassifiedAs(dataTest.classAttribute().value((int) pred));
        result.setDistribution(Utils.arrayToString(dist));
        return result;
    }

    public void removeAttributes(int[] indexes) throws Exception {

        StringBuilder indexesSB = new StringBuilder();

        for (int index :
                indexes) {
            indexesSB.append(index).append(",");
        }

        Remove remove = new Remove();
        String[] opts = new String[]{"-R", indexesSB.toString()};
        remove.setOptions(opts);
        remove.setInputFormat(dataTrain);
        dataTrain = Filter.useFilter(dataTrain, remove);
    }

    public void selectFeatures(Instances data) throws Exception {
        InfoGainAttributeEval evaluator = new InfoGainAttributeEval();
        Ranker ranker = new Ranker();
        AttributeSelection attSelect = new AttributeSelection();
        attSelect.setEvaluator(evaluator);
        attSelect.setSearch(ranker);
        attSelect.SelectAttributes(data);
    }


    public void visualizeTree() throws Exception {
        TreeVisualizer tv = new TreeVisualizer(null, tree.graph(), new PlaceNode2());
        System.setProperty("java.awt.headless", "true");
        JFrame frame = new JFrame("Tree Visualizer");
        frame.setSize(2000, 900);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(tv);
        frame.setVisible(true);
        tv.fitToScreen();
    }
}
