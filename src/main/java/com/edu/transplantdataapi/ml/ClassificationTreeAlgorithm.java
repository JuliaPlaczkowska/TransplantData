package com.edu.transplantdataapi.ml;

import com.edu.transplantdataapi.datatransferobject.prediction.TransplantDto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.*;
import weka.core.converters.ConverterUtils;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import javax.swing.*;
import java.util.Random;

@Data
@Slf4j
public class ClassificationTreeAlgorithm {

    private Instances data;
    private J48 tree;


    public ClassificationTreeAlgorithm(String arrf) throws Exception {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource(arrf);
        data = source.getDataSet();
        log.info(data.numInstances() + " instances loaded!");
        //log.info(data.toString());
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
        remove.setInputFormat(data);
        data = Filter.useFilter(data, remove);

        //System.out.println(data.toString());
    }

    public void selectFeatures() throws Exception {
        InfoGainAttributeEval evaluator = new InfoGainAttributeEval();
        Ranker ranker = new Ranker();
        AttributeSelection attSelect = new AttributeSelection();
        attSelect.setEvaluator(evaluator);
        attSelect.setSearch(ranker);
        attSelect.SelectAttributes(data);
    }

    public String buildDecisionTree() throws Exception {
        selectFeatures();
        tree = new J48();
        String[] options = new String[1];
        options[0] = "-U"; // un-pruned tree option
        tree.setOptions(options);
        tree.buildClassifier(data);
        return tree.toString();
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
