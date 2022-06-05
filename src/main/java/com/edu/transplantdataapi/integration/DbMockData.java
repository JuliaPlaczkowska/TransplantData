package com.edu.transplantdataapi.integration;

import com.edu.transplantdataapi.entities.enums.ERole;
import com.edu.transplantdataapi.entities.transplantdata.SurvivalResult;
import com.edu.transplantdataapi.entities.transplantdata.Transplant;
import com.edu.transplantdataapi.entities.transplantdata.patient.Donor;
import com.edu.transplantdataapi.entities.transplantdata.patient.Patient;
import com.edu.transplantdataapi.entities.transplantdata.patient.Recipient;
import com.edu.transplantdataapi.entities.user.Role;
import com.edu.transplantdataapi.entities.user.User;
import com.edu.transplantdataapi.integration.repositories.transplantdata.SurvivalResultRepo;
import com.edu.transplantdataapi.integration.repositories.user.RoleRepo;
import com.edu.transplantdataapi.integration.repositories.user.UserRepo;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class DbMockData {

    private static final String ADMIN_EMAIL = "admin@admin.com";
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    private static final String DATASET_CSV_PATH = "src/main/resources/dataset/bone-marrow-uci-dataset.csv";
    public static final String MISSING_VALUE = "?";

    static long patientNumber = 22000000000L;
    static long transplantNumber = 1L;
    static long survivalResultNumber = 1L;

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final SurvivalResultRepo survivalResultRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void fill() {
        Path path = Paths.get(DATASET_CSV_PATH);
        User admin = addAdminUser();
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

    private User addAdminUser() {
        Role roleAdmin = Role.builder().name(ERole.ROLE_ADMIN).build();
        Role roleUser = Role.builder().name(ERole.ROLE_USER).build();
        Role roleDoctor = Role.builder().name(ERole.ROLE_DOCTOR).build();
        Set<Role> adminRoles = new HashSet<>(Arrays.asList(roleAdmin, roleUser, roleDoctor));
        roleRepo.save(roleAdmin);
        roleRepo.save(roleUser);
        roleRepo.save(roleDoctor);

        User user = User.builder()
                .username(ADMIN_USERNAME)
                .email(ADMIN_EMAIL)
                .password(new BCryptPasswordEncoder().encode(ADMIN_PASSWORD))
                .roles(adminRoles)
                .build();
        return userRepo.save(user);
    }

    private void addSurvivalResult(String[] params, User user) {
        Patient patientDonor = Patient.builder()
                .number(patientNumber++)
                .age(Double.parseDouble(params[0]))
                .bloodABO(params[2])
                .presenceOfCMV(params[3])
                .build();

        Donor donor = Donor.builder()
                .patient(patientDonor)
                .stemCellSource(params[23])
                .build();

        Patient patientRecipient = Patient.builder()
                .number(patientNumber++)
                .age(Double.parseDouble(params[4]))
                .bloodABO(params[9])
                .presenceOfCMV(params[11])
                .build();


        Recipient recipient = Recipient.builder()
                .patient(patientRecipient)
                .bloodRh(params[10])
                .bodyMass((params[8].equals(MISSING_VALUE)) ? 0 : Double.parseDouble(params[8]))
                .disease(params[12])
                .diseaseGroup(params[13])
                .riskGroup(params[22])
                .build();

        Transplant transplant = Transplant.builder()
                .number(transplantNumber++)
                .donor(donor)
                .recipient(recipient)
                .matchHLA(Integer.parseInt(params[17].replace("/10", "")))
                .mismatchHLA((params[18].equals("mismatched")))
                .antigen((params[19].equals(MISSING_VALUE)) ? 1000 : Integer.parseInt(params[19]))
                .allele((params[20].equals(MISSING_VALUE)) ? 1000 : Integer.parseInt(params[20]))
                .group1HLA(params[21])
                .postRelapse(params[24].equals("yes"))
                .CD34perKg((params[25].equals(MISSING_VALUE)) ? 0 : Double.parseDouble(params[25]))
                .CD3perKg((params[26].equals(MISSING_VALUE)) ? 0 : Double.parseDouble(params[26]))
                .author(user)
                .build();

        SurvivalResult survivalResult = SurvivalResult.builder()
                .number(survivalResultNumber++)
                .transplant(transplant)
                .ancRecovery((params[28].equals(MISSING_VALUE)) ? 0 : (int) Double.parseDouble(params[28]))
                .pltRecovery((params[29].equals(MISSING_VALUE)) ? 0 : (int) Double.parseDouble(params[29]))
                .acuteGvHD_II_III_IV(params[30].equals("yes"))
                .acuteGvHD_III_IV(params[31].equals("yes"))
                .time_to_acuteGvHD_III_IV((params[32].equals(MISSING_VALUE)) ? 0 : Double.parseDouble(params[32]))
                .extensiveChronicGvHD(params[33].equals("yes"))
                .relapse(params[34].equals("yes"))
                .survivalTime((params[35].equals(MISSING_VALUE)) ? 0 : Double.parseDouble(params[35]))
                .survivalStatus(params[36].equals("1"))
                .confirmed(true)
                .build();
        survivalResultRepository.save(survivalResult);
    }
}
