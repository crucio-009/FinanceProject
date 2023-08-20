package com.java.financeprojectapp.servicelayer.entities;

import java.security.SecureRandom;
import java.util.Random;

public class RandomPasswordGenerator {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_-+=<>?";

    public static String generateRandomPassword(int length) {
        String allCharacters = LOWERCASE + UPPERCASE + DIGITS + SPECIAL_CHARACTERS;
        StringBuilder password = new StringBuilder();

        Random random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allCharacters.length());
            password.append(allCharacters.charAt(index));
        }

        return password.toString();
    }
}