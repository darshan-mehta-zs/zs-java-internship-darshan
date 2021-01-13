package com.zs.exe2;

import java.util.LinkedHashSet;
import java.util.Scanner;

public class DifferentCharactersInString {

    String input;

    public void inputString() {
        System.out.println("Enter String");
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
    }

    public String differentCharacters() {
        LinkedHashSet<Character> uniqueCharacters = new LinkedHashSet<>();
        for (char c : input.toCharArray()) {
            uniqueCharacters.add(c);
        }
        return uniqueCharacters.toString();
    }

    public static void main(String[] args) {

        DifferentCharactersInString differentCharactersInString = new DifferentCharactersInString();
        differentCharactersInString.inputString();
        String uniqueCharacters = differentCharactersInString.differentCharacters();
        System.out.println(uniqueCharacters);
    }

}
