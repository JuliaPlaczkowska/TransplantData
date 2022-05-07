package com.edu.transplantdataapi.config;

import com.edu.transplantdataapi.dto.UserDto;
import com.edu.transplantdataapi.dto.prediction.TransplantToPredictDto;
import com.edu.transplantdataapi.entity.transplantdata.Transplant;
import com.edu.transplantdataapi.entity.user.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper
                .getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

//        map_User_to_UserDto(mapper);
//        map_Transplant_to_TransplantToPredictDto(mapper);

        mapper.validate();
        return mapper;
    }

    private void map_User_to_UserDto(ModelMapper mapper) {
        TypeMap<User, UserDto> config =
                mapper.createTypeMap(
                        User.class,
                        UserDto.class
                );
        config.addMapping(
                User::getRoles,
                UserDto::setRoles);
    }

    private void map_Transplant_to_TransplantToPredictDto(ModelMapper mapper) {
        TypeMap<Transplant, TransplantToPredictDto> config =
                mapper.createTypeMap(
                        Transplant.class,
                        TransplantToPredictDto.class
                );
        config.addMapping(
                        transplant -> transplant.getDonor().getPatient().getAge(),
                        TransplantToPredictDto::setDonorAge)
                .addMapping(
                        transplant -> transplant.getDonor().getPatient().getBloodABO(),
                        TransplantToPredictDto::setDonorBloodABO)
                .addMapping(
                        transplant -> transplant.getDonor().getPatient().getPresenceOfCMV(),
                        TransplantToPredictDto::setDonorPresenceOfCMV)
                .addMapping(
                        transplant -> transplant.getRecipient().getPatient().getAge(),
                        TransplantToPredictDto::setRecipientAge)
                .addMapping(
                        transplant -> transplant.getRecipient().getPatient().getBloodABO(),
                        TransplantToPredictDto::setRecipientBloodABO)
                .addMapping(
                        transplant -> transplant.getRecipient().getPatient().getPresenceOfCMV(),
                        TransplantToPredictDto::setRecipientPresenceOfCMV)
                .addMapping(
                        transplant -> transplant.getRecipient().getDisease(),
                        TransplantToPredictDto::setDisease)
                .addMapping(
                        transplant -> transplant.getRecipient().getDiseaseGroup(),
                        TransplantToPredictDto::setDiseaseGroup)
                .addMapping(
                        transplant -> transplant.getDonor().getStemCellSource(),
                        TransplantToPredictDto::setStemCellSource
                );
    }
}
