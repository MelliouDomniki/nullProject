package com.example.nullproject2.services;

import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.VaccineStatus;
import com.example.nullproject2.forms.VaccineForm;
import com.example.nullproject2.models.VaccineModel;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class VaccineServiceImpl implements VaccineService {
    @Override
    public List<VaccineModel> getAllVaccines() {
        return null;
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
        return Optional.empty();
    }

    @Override
    public List<VaccineModel> getVaccineByStatus(VaccineStatus vaccineStatus) {
        return null;
    }

    @Override
    public List<VaccineModel> getVaccineByBrand(Brand brand) {
        return null;
    }

    @Override
    public List<VaccineModel> getVaccineByDate(Date date) {
        return null;
    }
}
