package com.example.nullproject2.mappers;

import com.example.nullproject2.entity.BigChain;
import com.example.nullproject2.models.BigChainModel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BigChainMapper {

    public static BigChainModel mapToBigChainModel(BigChain bigChain){
        if (bigChain == null)
            return null;

        BigChainModel bigChainModel = new BigChainModel();
        bigChainModel.setId(bigChain.getId());
        bigChainModel.setHospital(bigChain.getHospital());
        bigChainModel.setPatient(bigChain.getPatient());
        bigChainModel.setAmka(bigChain.getAmka());
        bigChainModel.setAge(bigChain.getAge());
        bigChainModel.setBrand(bigChain.getBrand());
        bigChainModel.setDate(bigChain.getDate());
        bigChainModel.setPatientStatus(bigChain.getPatientStatus());
        bigChainModel.setCity(bigChain.getCity());
        bigChainModel.setCountry(bigChain.getCountry());

        return bigChainModel;
    }

    public static List<BigChainModel> mapToBigChainModelList(List<BigChain> bigChains){
        return bigChains.stream()
                .map(BigChainMapper::mapToBigChainModel)
                .collect(Collectors.toList());
    }

    public static Optional<BigChainModel> mapToBigChainModelOptional(BigChain bigChain){
        if (bigChain == null)
            return Optional.empty();
        return Optional.of(mapToBigChainModel(bigChain));
    }
}
