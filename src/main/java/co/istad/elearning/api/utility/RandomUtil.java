package co.istad.elearning.api.utility;

import java.util.Random;

public class RandomUtil {
    public static String random6Digits(){
        int randomNumber = new Random().nextInt(900000) + 100000;
        // Convert the number to a 6-character string (padded with leading zeros if needed)
        String formattedRandomNumber = String.format("%06d", randomNumber);
        return  formattedRandomNumber;
    }
}
