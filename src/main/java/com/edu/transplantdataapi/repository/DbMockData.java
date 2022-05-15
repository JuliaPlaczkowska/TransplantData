package com.edu.transplantdataapi.repository;

import com.edu.transplantdataapi.entity.transplantdata.*;
import com.edu.transplantdataapi.entity.user.Role;
import com.edu.transplantdataapi.entity.user.User;
import com.edu.transplantdataapi.enums.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Component
public class DbMockData {

    private final String adminEmail = "admin@admin.com";
    private final String adminUsername = "admin";
    private final String adminPassword = "admin123";


    private final AccountRepo accountRepo;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PatientRepo patientRepository;
    private final DonorRepo donorRepository;
    private final RecipientRepo recipientRepository;
    private final TransplantRepo transplantRepository;
    private final SurvivalResultRepo survivalResultRepository;

    @Autowired
    public DbMockData(
            AccountRepo accountRepo,
            UserRepo userRepo, RoleRepo roleRepo,
            PatientRepo patientRepository,
            DonorRepo donorRepository,
            RecipientRepo recipientRepository,
            TransplantRepo transplantRepository,
            SurvivalResultRepo survivalResultRepository) {
        this.accountRepo = accountRepo;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.patientRepository = patientRepository;
        this.donorRepository = donorRepository;
        this.recipientRepository = recipientRepository;
        this.transplantRepository = transplantRepository;
        this.survivalResultRepository = survivalResultRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fill() {

        Path path = Paths.get(
                "src/main/resources/dataset/bone-marrow-uci-dataset.csv");

        User admin = addAdminAccount();

        try {

            BufferedReader reader = Files.newBufferedReader(path);
            String line;

            while (reader.ready()) {

                line = reader.readLine();

                String[] params = line.split(",");

                addSurvivalResult(params, admin);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private User addAdminAccount() {

        Role roleAdmin = Role.builder().name(ERole.ROLE_ADMIN).build();
        Role roleUser = Role.builder().name(ERole.ROLE_USER).build();
        Role roleDoctor = Role.builder().name(ERole.ROLE_DOCTOR).build();
        Set<Role> adminRoles = new HashSet<>(Arrays.asList(roleAdmin, roleUser, roleDoctor));
        roleRepo.save(roleAdmin);
        roleRepo.save(roleUser);
        roleRepo.save(roleDoctor);

        User user = User.builder()
                .username(adminUsername)
                .email(adminEmail)
                .password(new BCryptPasswordEncoder().encode(adminPassword))
                .roles(adminRoles)
                .build();
        userRepo.save(user);
        return user;
    }

    private void addSurvivalResult(String[] params, User user) {

        Patient patientDonor = Patient.builder()
                .number(0)
                .age(Double.parseDouble(params[0]))
                .bloodABO(params[2])
                .presenceOfCMV(params[3])
                .build();

        patientRepository.save(patientDonor);

        Donor donor = Donor.builder()
                .patient(patientDonor)
                .stemCellSource(params[23])
                .build();

        donorRepository.save(donor);

        Patient patientRecipient = Patient.builder()
                .number(0)
                .age(Double.parseDouble(params[4]))
                .bloodABO(params[9])
                .presenceOfCMV(params[10])
                .build();

        patientRepository.save(patientRecipient);


        Recipient recipient = Recipient.builder()
                .patient(patientRecipient)
                .bloodRh(params[11])
                .bodyMass((params[8].equals("?")) ? 0 : Double.parseDouble(params[8]))
                .disease(params[12])
                .diseaseGroup(params[13])
                .riskGroup(params[22])
                .build();

        recipientRepository.save(recipient);

        Transplant transplant = Transplant.builder()
                .donor(donor)
                .recipient(recipient)
                .matchHLA(Integer.parseInt(params[17].replace("/10", "")))
                .mismatchHLA((params[18].equals("mismatched")))
                .antigen((params[19].equals("?")) ? 1000 : Integer.parseInt(params[19]))
                .allele((params[20].equals("?")) ? 1000 : Integer.parseInt(params[20]))
                .group1HLA(params[21])
                .postRelapse(params[24].equals("yes"))
                .CD34perKg((params[25].equals("?")) ? 0 : Double.parseDouble(params[25]))
                .CD3perKg((params[26].equals("?")) ? 0 : Double.parseDouble(params[26]))
                .user(user)
                .build();
        transplantRepository.save(transplant);

        SurvivalResult survivalResult = SurvivalResult.builder()
                .number(1L) //TODO
                .transplant(transplant)
                .ancRecovery((params[28].equals("?")) ? 0 : (int) Double.parseDouble(params[28]))
                .pltRecovery((params[29].equals("?")) ? 0 : (int) Double.parseDouble(params[29]))
                .acuteGvHD_II_III_IV(params[30].equals("yes"))
                .acuteGvHD_III_IV(params[31].equals("yes"))
                .time_to_acuteGvHD_III_IV((params[32].equals("?")) ? 0 : Double.parseDouble(params[32]))
                .extensiveChronicGvHD(params[33].equals("yes"))
                .relapse(params[34].equals("yes"))
                .survivalTime((params[35].equals("?")) ? 0 : Double.parseDouble(params[35]))
                .survivalStatus(params[36].equals("1"))
                .build();
        survivalResultRepository.save(survivalResult);


    }
}
