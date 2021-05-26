package com.example.nullproject2.services;

import com.example.nullproject2.entity.Vaccine;
import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.VaccineStatus;
import com.example.nullproject2.forms.VaccineForm;
import com.example.nullproject2.mappers.VaccineFormMapper;
import com.example.nullproject2.mappers.VaccineMapper;
import com.example.nullproject2.models.VaccineModel;
import com.example.nullproject2.repositories.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

public class VaccineServiceImpl implements VaccineService {

    @Autowired
    private VaccineRepository vacrepo;


    @Override
    public List<VaccineModel> getAllVaccines() {

        return VaccineMapper.mapToVaccineModelList(vacrepo.findAll());
    }

//    @Override
//    public Optional<VaccineModel> addVaccine(VaccineForm vaccine) throws Exception {
//        return Optional.empty();
//    }

    @Override
    public Optional<VaccineModel> updateVaccine(VaccineForm toBeUpdatedVaccine) {
        Vaccine vaccine = VaccineFormMapper.mapToVaccine(toBeUpdatedVaccine);
        Vaccine originalVaccine = vacrepo.findFirstById(vaccine.getId());
        if (vaccine.equals(originalVaccine)){
//            if no changes made dont update
            return VaccineMapper.mapToVaccineModelOptional(vaccine);
        }

        return VaccineMapper.mapToVaccineModelOptional(vacrepo.save(vaccine));

    }

    @Override
    public Optional<VaccineModel> getVaccineById(String id) {

       return VaccineMapper.mapToVaccineModelOptional(vacrepo.findFirstById(id));
    }

    @Override
    public List<VaccineModel> getVaccineByStatus(String username, VaccineStatus vaccineStatus) {

        return VaccineMapper.mapToVaccineModelList(vacrepo.findByHospitalNameAndStatus(username, vaccineStatus));
    }

    @Override
    public List<VaccineModel> getVaccineByBrand(String username, Brand brand) {

        return VaccineMapper.mapToVaccineModelList(vacrepo.findByHospitalNameAndBrand(username, brand));
    }

    @Override
    public boolean deleteVaccineById(String id) {
        if (id == null || getVaccineById(id).isEmpty()){
            return false;
        }

        vacrepo.deleteById(id);
        return true;
    }

}
