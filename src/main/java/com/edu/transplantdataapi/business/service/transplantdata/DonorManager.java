package com.edu.transplantdataapi.business.service.transplantdata;

import com.edu.transplantdataapi.integration.repository.DonorRepo;
import com.edu.transplantdataapi.entities.transplantdata.Donor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DonorManager {

    private DonorRepo donorRepo;

    @Autowired
    public DonorManager(DonorRepo donorRepo){
        this.donorRepo = donorRepo;
    }

    public Optional<Donor> findById(Long id){
        return donorRepo.findById(id);
    }

    public Iterable<Donor> findAll(){
        return donorRepo.findAll();
    }

    public Donor save(Donor donor){
        return donorRepo.save(donor);
    }

    public void deleteById(Long id){ donorRepo.deleteById(id); }


}
