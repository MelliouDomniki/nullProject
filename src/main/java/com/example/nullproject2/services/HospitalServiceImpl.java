package com.example.nullproject2.services;

import com.example.nullproject2.forms.HospitalForm;
import com.example.nullproject2.models.HospitalModel;
import com.example.nullproject2.repositories.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public List<HospitalModel> getAllHospitals() {
        return null;
    }

    @Override
    public Optional<HospitalModel> addHospital(HospitalForm hospital) throws Exception {
        return Optional.empty();
    }

    @Override
    public Optional<HospitalModel> updateHospital(HospitalForm toBeUpdatedHospital) {
        return Optional.empty();
    }

    @Override
    public boolean deleteHospitalById(String hospital_id) {
        return false;
    }

}
