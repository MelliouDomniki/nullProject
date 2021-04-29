package com.example.nullproject2.fakedata;

import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.PatientStatus;
import com.example.nullproject2.enumerations.Sex;
import com.example.nullproject2.enumerations.VaccineStatus;
import lombok.NoArgsConstructor;

import java.util.Random;

import static com.example.nullproject2.enumerations.Brand.*;
import static com.example.nullproject2.enumerations.PatientStatus.*;
import static com.example.nullproject2.enumerations.Sex.FEMALE;
import static com.example.nullproject2.enumerations.Sex.MALE;
import static com.example.nullproject2.enumerations.VaccineStatus.UNAVAILABLE;

public abstract class RandomnessProvider {


//    Provides a random number
    public static int getRandomNumberBetween(int minNumber, int maxNumber){
        return minNumber + new Random().nextInt((maxNumber - minNumber)+1);
    }

//    Get a random PatientStatus
    public static PatientStatus getPatientStatus(){
        int value = getRandomNumberBetween(1,100);
        if (value <= 24){
            return AVAILABLE;
        }else if (value <= 49){
            return PENTING;
        }else if (value <= 74){
            return COMPLETED;
        }else{
            return CANCELED;
        }

        }


//        Get a random Sex
    public static Sex getSex(){
        int value = getRandomNumberBetween(1,2);
        if (value == 1){
            return FEMALE;
        }else{
            return MALE;
        }
    }

//    Get a random Brand
    public static Brand getBrand(){
        int value = getRandomNumberBetween(1,5);
        if (value == 1){
            return PFIZER;
        }else if (value == 2){
            return ASTRAZENECA;
        }else if (value == 3){
            return NOVAVAX;
        }else if (value == 4){
            return MODERNA;
        }else{
            return JOHNSON;
        }
    }


//    Get a random vaccineStatus
    public static VaccineStatus getVaccineStatus(){
        int value = getRandomNumberBetween(1,2);
        if (value == 1){
            return UNAVAILABLE;
        }else{
            return VaccineStatus.AVAILABLE;
        }
    }


//    Date provider

//    Day Provider
//    public

//
}
