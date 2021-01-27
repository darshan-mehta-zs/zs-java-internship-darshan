package com.zs.programs;

import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Program prints the unique characters in String
 */
public class DifferentCharactersInString {

    String input;

    /**
     * takes the input string
     */
    public void inputString() {
        System.out.println("Enter String");
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
    }

    /**
     * @return the string containing unique characters
     */
    public String differentCharacters() {
        LinkedHashSet<Character> uniqueCharacters = new LinkedHashSet<>();
        for (char c : input.toCharArray()) {
            uniqueCharacters.add(c);
        }
        return uniqueCharacters.toString();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        DifferentCharactersInString differentCharactersInString = new DifferentCharactersInString();
        differentCharactersInString.inputString();
        String uniqueCharacters = differentCharactersInString.differentCharacters();
        System.out.println(uniqueCharacters);
    }

}
