package com.example.nullproject2.mappers;


import com.example.nullproject2.entity.Hospital;
import com.example.nullproject2.models.HospitalModel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HospitalMapper {


    public static HospitalModel mapToHospitalModel(Hospital hospital){
        if (hospital == null) return null;
        HospitalModel hospitalModel = new HospitalModel();
        hospitalModel.setHospital_id(hospitalModel.getHospital_id());
        hospitalModel.setName(hospital.getName());
        hospitalModel.setAddress(hospital.getAddress());
        hospitalModel.setPhone_number(hospital.getPhone_number());
        hospitalModel.setCity(hospital.getCity());
        hospitalModel.setCountry(hospital.getCountry());
        hospitalModel.setAvailable_doses(hospital.getAvailable_doses());
        hospitalModel.setEmail(hospital.getEmail());
        hospitalModel.setPassword(hospital.getPassword());
        return hospitalModel;
    }

    public static List<HospitalModel> mapToHospitalModelList( List<Hospital> hospitals){
        return hospitals.stream()
                .map(HospitalMapper::mapToHospitalModel)
                .collect(Collectors.toList());
    }

    public static Optional<HospitalModel> mapToHospitalModelOptional(Hospital hospital){
        if (hospital == null) return Optional.empty();
        return Optional.of(mapToHospitalModel(hospital));
    }
}
