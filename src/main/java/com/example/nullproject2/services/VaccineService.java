package com.example.nullproject2.services;

import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.VaccineStatus;
import com.example.nullproject2.forms.VaccineForm;
import com.example.nullproject2.models.VaccineModel;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface VaccineService {

    List<VaccineModel> getAllVaccines();

    Optional<VaccineModel> addVaccine(VaccineForm vaccine) throws Exception;

    Optional<VaccineModel> updateVaccine(VaccineForm toBeUpdatedVaccine);

    Optional<VaccineModel> getVaccineById(String id);

    Optional<VaccineModel> getVaccineByCode(int code);

    List<VaccineModel> getVaccineByStatus(VaccineStatus vaccineStatus);

    List<VaccineModel> getVaccineByBrand(Brand brand);

    List<VaccineModel> getVaccineByDate(Date date);

}
