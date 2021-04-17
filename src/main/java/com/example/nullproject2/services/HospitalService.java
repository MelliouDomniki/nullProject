package com.example.nullproject2.services;


import com.example.nullproject2.forms.HospitalForm;
import com.example.nullproject2.models.HospitalModel;

import java.util.List;
import java.util.Optional;

public interface HospitalService {

    List<HospitalModel> getAllHospitals();

    Optional<HospitalModel> addHospital(HospitalForm hospital) throws Exception;

    Optional<HospitalModel> updateHospital(HospitalForm toBeUpdatedHospital);

    boolean deleteHospitalById(String id);

    boolean deleteHospitalByHospitalId(int id);
}
