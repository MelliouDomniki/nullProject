package com.example.nullproject2.fakedata;

import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.PatientStatus;
import com.example.nullproject2.enumerations.Sex;
import com.example.nullproject2.enumerations.VaccineStatus;
import lombok.NoArgsConstructor;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import net.i2p.crypto.eddsa.KeyPairGenerator;

import java.security.KeyPair;
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
}
