package com.example.nullproject2.mappers;

import com.example.nullproject2.entity.BigChain;
import com.example.nullproject2.forms.BigChainForm;
import org.springframework.stereotype.Component;

@Component
public class BigChainFormMapper {

    public static BigChain mapToBigChain(BigChainForm bigChainForm){
        if(bigChainForm == null)
            return null;

        BigChain bigchain = new BigChain();

        bigchain.setId(bigChainForm.getId());

        if(bigChainForm.getHosp() != null){
            if(bigChainForm.getHosp().isBlank()){
                bigchain.setHosp(bigChainForm.getHosp());
            }
        }

        if(bigChainForm.getAMKA() != null){
            if(bigChainForm.getAMKA().isBlank()){
                bigchain.setAMKA(bigChainForm.getAMKA());
            }
        }


        if(bigChainForm.getBrand() != null){
            if(bigChainForm.getBrand().isBlank()){
                bigchain.setBrand(bigChainForm.getBrand());
            }
        }

        if(bigChainForm.getStatus() != null){
            if(bigChainForm.getStatus().isBlank()){
                bigchain.setStatus(bigChainForm.getStatus());
            }
        }

        bigchain.setDate(bigChainForm.getDate());



        return bigchain;
    }

    public static BigChainForm mapToBigChainForm(BigChain bigchain){
        if(bigchain == null)
            return null;

        BigChainForm bigChainForm = new BigChainForm();

        bigChainForm.setId(bigchain.getId());
        bigChainForm.setHosp(bigchain.getHosp());
        bigChainForm.setAMKA(bigchain.getAMKA());
        bigChainForm.setBrand(bigchain.getBrand());
        bigChainForm.setStatus(bigchain.getStatus());
        bigChainForm.setDate(bigchain.getDate());

        return bigChainForm;
    }
}
