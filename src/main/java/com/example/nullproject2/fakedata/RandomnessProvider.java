package com.example.nullproject2.fakedata;

import com.example.nullproject2.enumerations.Brand;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import static com.example.nullproject2.enumerations.Brand.*;


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

    public static Date getDateWithoutTimeUsingFormat() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        Date nextYear = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd/MM/yyyy");
        return formatter.parse(formatter.format(nextYear));
    }
}
