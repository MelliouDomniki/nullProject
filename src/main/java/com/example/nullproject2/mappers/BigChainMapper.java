package com.example.nullproject2.mappers;

import com.example.nullproject2.entity.BigChain;
import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.models.BigChainModel;
import com.example.nullproject2.models.PatientModel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BigChainMapper {

    public static BigChainModel mapToBigChainModel(BigChain bigchain){
        if (bigchain == null) return null;
        BigChainModel patientModel = new BigChainModel();
        patientModel.setHosp(bigchain.getHosp());
        patientModel.setAMKA(bigchain.getAMKA());
        patientModel.setBrand(bigchain.getBrand());
        patientModel.setStatus(bigchain.getStatus());
        patientModel.setDate(bigchain.getDate());
        return patientModel;
    }

    public static List<BigChainModel> mapToBigChainModelList(List<BigChain> bigchains){
        return bigchains.stream()
                .map(BigChainMapper::mapToBigChainModel)
                .collect(Collectors.toList());
    }

    public static Optional<BigChainModel> mapToBigChainModelOptional(BigChain bigchain){
        if (bigchain == null) return Optional.empty();
        return Optional.of(mapToBigChainModel(bigchain));
    }
}
