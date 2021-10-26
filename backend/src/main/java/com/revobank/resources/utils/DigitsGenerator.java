package com.revobank.resources.utils;

import java.util.Random;

public class DigitsGenerator {

	public static String generateDigits(Integer number) {
        StringBuilder text = new StringBuilder();
        Random generator = new Random();
        for (int i = 0; i < number; i++) {
            text.append(generator.nextInt(9));            
        }
        return text.toString();
    }	
	
}
