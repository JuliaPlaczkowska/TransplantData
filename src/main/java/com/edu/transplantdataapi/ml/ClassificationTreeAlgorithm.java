package com.edu.transplantdataapi.ml;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
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

@Data
@Slf4j
public class ClassificationTreeAlgorithm {

    private Instances dataTrain;
    private Instances dataTest;
    private J48 tree = new J48();

    public ClassificationTreeAlgorithm(String arrfTrain, String arrfTest) throws Exception {
        dataTrain = arrfToInstances(arrfTrain);
        dataTest = arrfToInstances(arrfTest);
    }

    public Instances arrfToInstances(String arrf) throws Exception {
        ConverterUtils.DataSource source1 = new ConverterUtils.DataSource(arrf);
        return source1.getDataSet();
    }

    //wg 18.7.2 z WekaManual.pdf - Train/test set
    public List<String> getTree() throws Exception {
        selectFeatures(dataTrain);
        tree.buildClassifier(dataTrain);
        Evaluation eval = new Evaluation(dataTrain);
        selectFeatures(dataTest);
        eval.evaluateModel(tree, dataTest);

        List<String> results = evaluationSummary();
        results.add(tree.toString());
        return results;
    }

    //wg 18.7.2 z WekaManual.pdf - Collecting predictions
    public List<String> evaluationSummary() throws Exception {

        dataTrain.setClassIndex(dataTrain.numAttributes() - 1);

        Evaluation eval = new Evaluation(dataTrain);
        StringBuffer buffer = new StringBuffer();
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

    //wg 18.7.3 z WekaManual.pdf - Classifying instances
    public String predict() throws Exception {

        dataTrain.setClassIndex(dataTrain.numAttributes() - 1);
        dataTest.setClassIndex(dataTest.numAttributes() - 1);

        //	train classifier
        tree.buildClassifier(dataTrain);

        //	output predictions
        ArrayList<String> dane = new ArrayList();//do wykorzystania jako dane modelu
        String l1 = "# - actual - predicted - distribution (0/1)" + "\n";
        dane.add(l1);

        for (int i = 0; i < dataTest.numInstances(); i++) {
            double pred = tree.classifyInstance(dataTest.instance(i));
            double[] dist = tree.distributionForInstance(dataTest.instance(i));

            l1 = (i + 1) + " -	";

            l1 = l1 + dataTest.instance(i).toString(dataTest.classIndex()) + " -      ";
            l1 = l1 + dataTest.classAttribute().value((int) pred) + " -        ";

            l1 = l1 + Utils.arrayToString(dist);

            l1 = l1 + '\n';
            dane.add(l1);
        }


        return dane.toString();
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

        //System.out.println(data.toString());
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
        frame.setDefaultCloseOperation(3);
        frame.getContentPane().add(tv);
        frame.setVisible(true);
        tv.fitToScreen();
    }
}
