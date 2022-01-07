package com.edu.transplantdataapi.ml;

import com.edu.transplantdataapi.datatransferobject.prediction.TransplantPredictionDto;
import com.edu.transplantdataapi.entity.Transplant;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils;
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
        log.info(data.toString());
    }

    public void removeAttributes(int[] indexes) throws Exception {

        StringBuilder indexesSB =  new StringBuilder();

        for (int index :
             indexes) {
            indexesSB.append(index).append(",");
        }

        Remove remove = new Remove();
        String[] opts = new String[] { "-R", indexesSB.toString()};
        remove.setOptions(opts);
        remove.setInputFormat(data);
        data = Filter.useFilter(data, remove);

        System.out.println(data.toString());
    }

    public void selectFeatures() throws Exception {

    }

    public void buildDecisionTree() throws Exception {
        tree = new J48();
        String[] options = new String[1];
        options[0] = "-U"; // un-pruned tree option
        tree.setOptions(options);
        tree.buildClassifier(data);
        log.info(tree.toString());
    }

    public void visualizeTree(J48 tree) throws Exception {
        TreeVisualizer tv = new TreeVisualizer(null, tree.graph(), new PlaceNode2());
        JFrame frame = new JFrame("Tree Visualizer");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(tv);
        frame.setVisible(true);
        tv.fitToScreen();
    }

    public String classifyData(TransplantPredictionDto transplant) throws Exception {
        Instance newInstance = transplantToInstance(transplant);
        newInstance.setDataset(data);
        double result = tree.classifyInstance(newInstance);
        log.info("Predicted survival result... " + data.classAttribute().value((int) result));
        return data.classAttribute().value((int) result);
    }

//model validation
    public void showErrorMetrics() throws Exception {
        Classifier c1 = new J48();
        Evaluation evalRoc = new Evaluation(data);
        evalRoc.crossValidateModel(c1, data, 10, new Random(1), new Object[] {});
        log.info(evalRoc.toSummaryString());
        log.info(evalRoc.toMatrixString());
    }

    private Instance transplantToInstance(TransplantPredictionDto transplant){
        double[] vals = new double[data.numAttributes()];
        vals[0] = transplant.getDonorAge();	//donor_age
        vals[1] = Double.parseDouble(transplant.getDonorBloodABO());	//donor_age
        vals[2] = Double.parseDouble(transplant.getDonorPresenceOfCMV());	//donor_CMV
        vals[3] = transplant.getRecipientAge();	//recipient_age
        vals[4] = Double.parseDouble(transplant.getRecipientBloodABO());	//recipient_ABO
        vals[5] = Double.parseDouble(transplant.getRecipientPresenceOfCMV());	//recipient_CMV
        vals[6] = Double.parseDouble(transplant.getDisease());	//disease
        vals[7] = Double.parseDouble(transplant.getDiseaseGroup());	//disease_group
        vals[8] = transplant.getMatchHLA();	//HLA_match
        vals[9] = Double.parseDouble(String.valueOf(transplant.isMismatchHLA()));	//HLA_mismatch
        vals[10] = transplant.getAntigen();	//antigen
        vals[11] = transplant.getAllele();	//allel
        vals[12] = Double.parseDouble(transplant.getGroup1HLA());	//HLA_group_1
        vals[13] = Double.parseDouble(transplant.getRiskGroup());	//risk_group
        vals[14] = Double.parseDouble(transplant.getStemCellSource());	//stem_cell_source
        vals[15] = Double.parseDouble(String.valueOf(transplant.isPostRelapse()));	//tx_post_relapse
        vals[16] = transplant.getCD34perKg();	//CD34_x1e6_per_kg
        vals[17] = transplant.getCD3perKg();	//CD3_x1e8_per_kg

        Instance newInstance = new DenseInstance(1.0, vals);
        return newInstance;
    }


}
