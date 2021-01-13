package com.zs.exe2;

import java.util.Arrays;
import java.util.Scanner;

public class ExtractWords {

    public String[] getWordsFromSentence(String sentence) {

        //return sentence.split(" ");
        String word = "";
        int index = 0;
        String words[] = new String[sentence.length()];
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) == ' ') {
                words[index++] = new String(word);
                word = "";
            } else
                word += sentence.charAt(i);
        }
        words[index++] = new String(word);
        return words;
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter String");
        String sentence = scanner.nextLine();
        ExtractWords extractWords = new ExtractWords();
        String words[] = extractWords.getWordsFromSentence(sentence);
        System.out.println(Arrays.deepToString(words));

    }
}
