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
}
