package com.example.eindeopdrachtvanrahman.utils;

import java.util.Random;

public class RandomStringGenerator {
    public static String generateAlphaNumeric(int length) {
        int leftLimit = 48;// numeral '0'
        int rightLimit = 122;//letterz 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generateString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generateString;
    }
}
