package com.edu.transplantdataapi.repository;

import com.edu.transplantdataapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Component
public class DbMockData {

    private final PatientRepo patientRepository;
    private final DonorRepo donorRepository;
    private final RecipientRepo recipientRepository;
    private final TransplantRepo transplantRepository;
    private final SurvivalResultRepo survivalResultRepository;

    @Autowired
    public DbMockData(
            PatientRepo patientRepository,
            DonorRepo donorRepository,
            RecipientRepo recipientRepository,
            TransplantRepo transplantRepository,
            SurvivalResultRepo survivalResultRepository) {
        this.patientRepository = patientRepository;
        this.donorRepository = donorRepository;
        this.recipientRepository = recipientRepository;
        this.transplantRepository = transplantRepository;
        this.survivalResultRepository = survivalResultRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fill() {

        Path path = Paths.get(
                "src/main/resources/dataset/bone-marrow-uci-dataset.txt");

        try {

            BufferedReader reader = Files.newBufferedReader(path);
            String line;

            while (reader.ready()) {

                line = reader.readLine();

                String[] params = line.split(",");

                addSurvivalResult(params);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void addSurvivalResult(String[] params) {

        Patient patientDonor = new Patient(
                null,
                Double.parseDouble(params[0]),
                params[2],
                params[3]
        );

        patientRepository.save(patientDonor);

        Donor donor = new Donor(
                patientDonor,
                params[23]
        );

        donorRepository.save(donor);

        Patient patientRecipient = new Patient(
                null,
                Double.parseDouble(params[4]),
                params[9],
                params[10]
        );

        patientRepository.save(patientRecipient);


        Recipient recipient = new Recipient(
                patientRecipient,
                params[11],
                (params[8].equals("?")) ? 0 : Double.parseDouble(params[8]),
                params[12],
                params[13],
                params[22]);

        recipientRepository.save(recipient);

        Transplant transplant = new Transplant(
                donor,
                recipient,
                Integer.parseInt(params[17].replace("/10", "")),
                (params[18].equals("mismatched")),
                (params[19].equals("?")) ? 1000 : Integer.parseInt(params[19]),
                (params[20].equals("?")) ? 1000 : Integer.parseInt(params[20]),
                params[21],
                params[24].equals("yes"),
                (params[25].equals("?")) ? 0 : Double.parseDouble(params[25]),
                (params[26].equals("?")) ? 0 : Double.parseDouble(params[26])
        );

        transplantRepository.save(transplant);

        SurvivalResult survivalResult = new SurvivalResult(
                transplant,
                (params[28].equals("?")) ? 0 : Double.parseDouble(params[28]),
                (params[29].equals("?")) ? 0 : Double.parseDouble(params[29]),
                params[30].equals("yes"),
                params[31].equals("yes"),
                (params[32].equals("?")) ? 0 : Double.parseDouble(params[32]),
                params[33].equals("yes"),
                params[34].equals("yes"),
                (params[35].equals("?")) ? 0 : Double.parseDouble(params[35]),
                params[36].equals("1")

        );
        survivalResultRepository.save(survivalResult);


    }
}
