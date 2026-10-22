package tests.utils;

import java.util.Random;

import static java.lang.String.format;

public class RandomUtils {

    public static String getRandomString(int length) {
        String forbiddenСharacters = "€‰ŠŒŽƒ†‡•™œžŸ¢£¤¥¦§¨©ª«¬ ";
        StringBuffer result = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < length; i++) {
            result.append(forbiddenСharacters.charAt(rnd.nextInt(forbiddenСharacters.length())));
        }
        return result.toString();
    }

    public static String getRandomForbiddenCharacters() {
        return format("%s", getRandomString(5));
    }

    public static String getRandomEmailWithoutDomain(){
        return format("%s@%s", getRandomString(5), getRandomString(5));
    }
}

