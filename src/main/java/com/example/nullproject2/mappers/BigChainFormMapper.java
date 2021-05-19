package com.example.nullproject2.mappers;

import com.example.nullproject2.entity.BigChain;
import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.PatientStatus;
import com.example.nullproject2.forms.BigChainForm;
import org.springframework.stereotype.Component;

@Component
public class BigChainFormMapper {

    public static BigChain mapToBigChain(BigChainForm bigChainForm){
        if(bigChainForm == null)
            return null;
        BigChain bigChain = new BigChain();

        bigChain.setId(bigChain.getId());

        if(bigChainForm.getHospital() != null){
            if (bigChainForm.getHospital().isBlank()){
                bigChain.setHospital(bigChainForm.getHospital());
            }
        }

        if(bigChainForm.getPatient() != null){
            if (bigChainForm.getPatient().isBlank()){
                bigChain.setPatient(bigChainForm.getPatient());
            }
        }

        if(bigChainForm.getAmka() != null){
            if (bigChainForm.getAmka().isBlank()){
                bigChain.setAmka(bigChainForm.getAmka());
            }
        }

        bigChain.setAge(bigChain.getAge());

        if(bigChainForm.getBrand() != null){
            if (bigChainForm.getBrand().isBlank()){
                bigChain.setBrand(Brand.valueOf(bigChainForm.getBrand()));
            }
        }

        bigChain.setDate(bigChain.getDate());

        if(bigChainForm.getPatientStatus() != null){
            if(bigChainForm.getPatientStatus().isBlank()){
                bigChain.setPatientStatus(PatientStatus.valueOf(bigChainForm.getPatientStatus()));
            }
        }

        if(bigChainForm.getCity() != null){
            if (bigChainForm.getCity().isBlank()){
                bigChain.setCity(bigChain.getCity());
            }
        }

        if(bigChainForm.getCountry() != null){
            if (bigChainForm.getCountry().isBlank()){
                bigChain.setCountry(bigChain.getCountry());
            }
        }

        return bigChain;
    }

    public static BigChainForm mapToBigChainForm(BigChain bigChain){
        if (bigChain == null)
            return null;

        BigChainForm bigChainForm = new BigChainForm();

        bigChainForm.setId(bigChain.getId());
        bigChainForm.setHospital(bigChain.getHospital());
        bigChainForm.setPatient(bigChain.getPatient());
        bigChainForm.setAmka(bigChain.getAmka());
        bigChainForm.setAge(bigChain.getAge());
        bigChainForm.setBrand(String.valueOf(bigChain.getBrand()));
        bigChainForm.setDate(bigChain.getDate());
        bigChainForm.setPatientStatus(String.valueOf(bigChain.getPatientStatus()));
        bigChainForm.setCity(bigChain.getCity());
        bigChainForm.setCountry(bigChain.getCountry());

        return bigChainForm;
    }
}
