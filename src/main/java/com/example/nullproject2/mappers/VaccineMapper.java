package com.example.nullproject2.mappers;

import com.example.nullproject2.entity.Vaccine;
import com.example.nullproject2.models.VaccineModel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VaccineMapper {

    public static VaccineModel mapToVaccineModel(Vaccine vaccine){
        if (vaccine == null) return null;
        VaccineModel vaccineModel = new VaccineModel();
        vaccineModel.setId(vaccine.getId());
        vaccineModel.setBrand(vaccine.getBrand());
        vaccineModel.setStatus(vaccine.getStatus());
        vaccineModel.setDate(vaccine.getDate());
        vaccineModel.setHospitalName(vaccine.getHospitalName());
        return vaccineModel;
    }

    public static List<VaccineModel> mapToVaccineModelList (List<Vaccine> vaccines){
        return vaccines.stream()
                .map(VaccineMapper::mapToVaccineModel)
                .collect(Collectors.toList());
    }

    public static Optional<VaccineModel> mapToVaccineModelOptional(Vaccine vaccine){
        if (vaccine == null) return Optional.empty();
        return Optional.of(mapToVaccineModel(vaccine));
    }
}
