package com.example.nullproject2.services;

import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.VaccineStatus;
import com.example.nullproject2.forms.VaccineForm;
import com.example.nullproject2.mappers.VaccineMapper;
import com.example.nullproject2.models.VaccineModel;
import com.example.nullproject2.repositories.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class VaccineServiceImpl implements VaccineService {

    @Autowired
    private VaccineRepository vacrepo;


    @Override
    public List<VaccineModel> getAllVaccines() {

        return VaccineMapper.mapToVaccineModelList(vacrepo.findAll());
    }

    @Override
    public Optional<VaccineModel> addVaccine(VaccineForm vaccine) throws Exception {
        return Optional.empty();
    }

    @Override
    public Optional<VaccineModel> updateVaccine(VaccineForm toBeUpdatedVaccine) {
        return Optional.empty();
    }

    @Override
    public Optional<VaccineModel> getVaccineById(String id) {

       return VaccineMapper.mapToVaccineModelOptional(vacrepo.findFirstById(id));
    }

    @Override
    public List<VaccineModel> getVaccineByStatus(VaccineStatus vaccineStatus) {

        return VaccineMapper.mapToVaccineModelOptional(vacrepo.);
    }

    @Override
    public List<VaccineModel> getVaccineByBrand(Brand brand) {

        return VaccineMapper.mapToVaccineModelOptional(vacrepo.);
    }

    @Override
    public List<VaccineModel> getVaccineByDate(Date date) {
        return null;
    }
}
