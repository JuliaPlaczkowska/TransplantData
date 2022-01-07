package com.edu.transplantdataapi.controller;

import com.edu.transplantdataapi.datatransferobject.DonorDto;
import com.edu.transplantdataapi.entity.Donor;
import com.edu.transplantdataapi.service.DonorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping
@CrossOrigin
public class DonorApi {

    private DonorManager donors;

    @Autowired
    public DonorApi(DonorManager donorManager) {

        this.donors = donorManager;
    }

    @GetMapping("api/donor/all")
    public Iterable<DonorDto> getAllDonors() {

        Iterable<Donor> iterableDonor = donors.findAll();

        return StreamSupport
                .stream(iterableDonor.spliterator(), true)
                .map(this::donorToDto)::iterator;
    }

    @GetMapping("api/donor")
    public Optional<DonorDto> getDonorById(@RequestParam Long index) {

        Optional<Donor> donor1 = donors.findById(index);

        DonorDto donorDto = null;
        if(donor1.isPresent())
            donorDto = donorToDto(donor1.get());

        return Optional.ofNullable(donorDto);
    }

    @GetMapping("api/donor/isAvailable")
    public boolean isIdAvailable(@RequestParam Long index){
        Optional<Donor> donor1 = donors.findById(index);

        return donor1.isEmpty();
    }

    @PostMapping("api/donor")
    public DonorDto addDonor(@RequestBody DonorDto donorDto){

        return  donorToDto(donors.save(convertToEntity(donorDto)));
    }

    private DonorDto donorToDto(Donor donor) {
        return new DonorDto(
                donor.getPatient(),
                donor.getStemCellSource());
    }

    private Donor convertToEntity(DonorDto DonorDto){
        return new Donor(DonorDto.getPatient(), DonorDto.getStemCellSource());
    }
}
