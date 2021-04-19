package com.example.nullproject2.mappers;


import com.example.nullproject2.entity.Hospital;
import com.example.nullproject2.forms.HospitalForm;
import org.springframework.stereotype.Component;

@Component
public class HospitalFormMapper {

    public static Hospital mapToHospital(HospitalForm hospitalForm){
        if (hospitalForm == null) return null;
        Hospital hospital = new Hospital();
        hospital.setHospital_id(hospitalForm.getHospitalId());
        if (hospitalForm.getName() != null){
            if (!hospitalForm.getName().isBlank()){
                hospital.setName(hospitalForm.getName());
            }
        }
        if (hospitalForm.getAddress() != null){
            if (!hospitalForm.getAddress().isBlank()){
                hospital.setAddress(hospitalForm.getAddress());
            }
        }
        hospital.setPhone_number(hospitalForm.getPhoneNumber());
        if (hospitalForm.getCity() != null){
            if (!hospitalForm.getCity().isBlank()){
                hospital.setCity(hospitalForm.getCity());
            }
        }
        if (hospitalForm.getCountry() != null){
            if (!hospitalForm.getCountry().isBlank()){
                hospital.setCountry(hospitalForm.getCountry());
            }
        }
        hospital.setAvailable_doses(hospitalForm.getAvailable_doses());
        if (hospitalForm.getEmail() != null){
            if (!hospitalForm.getEmail().isBlank()){
                hospital.setEmail(hospitalForm.getEmail());
            }
        }
        if (hospitalForm.getPassword() != null){
            if (!hospitalForm.getPassword().isBlank()){
                hospital.setPassword(hospitalForm.getPassword());
            }
        }

        return hospital;
    }

    public static HospitalForm mapToHospitalForm(Hospital hospital){
        if (hospital == null) return null;

        HospitalForm hospitalForm = new HospitalForm();
        hospitalForm.setHospitalId(hospital.getHospital_id());
        hospitalForm.setName(hospital.getName());
        hospitalForm.setAddress(hospital.getAddress());
        hospitalForm.setPhoneNumber(hospital.getPhone_number());
        hospitalForm.setCity(hospital.getCity());
        hospitalForm.setCountry(hospital.getCountry());
        hospitalForm.setAvailable_doses(hospital.getAvailable_doses());
        hospitalForm.setEmail(hospital.getEmail());
        hospitalForm.setPassword(hospital.getPassword());

        return hospitalForm;
    }
}
