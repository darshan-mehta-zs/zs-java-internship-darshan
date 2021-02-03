package com.zs.dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.Locale;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Dictionary to search word from dictionary, to add word to dictionary,
 * to find words starting with a word or by a difference of one character from dictionary
 */

public class Dictionary {

    private static Logger logger;

    /**
     * @param word accepts word as a String
     * @param key  accepts key as a string
     * @return boolean value depending on whether arguments have common prefix
     */
    public static boolean startsWith(String word, String key) {
        if (key.length() > word.length())
            return false;
        for (int i = 0; i < key.length(); i++) {
            if (word.charAt(i) != key.charAt(i))
                return false;
        }
        return true;
    }

    /**
     * @param dictionary contains data
     * @param key        to find all words in dictionary starting with key
     */
    public static void startingWith(TreeMap<String, String> dictionary, String key) {
        Map<String, String> similarWordsMap = dictionary.tailMap(key);
        Set<String> words = similarWordsMap.keySet();
        boolean flag = false;
        for (String word : words) {
            if (startsWith(word.toUpperCase(), key.toUpperCase())) {
                flag = true;
                System.out.print(word + " ");
            }
        }
        if (!flag) {
            System.out.println("No words starting with " + key);
        }
        System.out.println();
    }

    /**
     * @param dictionary contains data
     * @param similar    to search similar strings from dictionary
     */
    public static void similarWords(TreeMap<String, String> dictionary, String similar) {
        Set<String> words = dictionary.keySet();
        for (String word : words) {
            int i = 0;
            int difference = 0;
            if (word.length() != similar.length())
                continue;
            while (i < word.length()) {
                if (Character.toUpperCase(word.charAt(i)) != Character.toUpperCase(similar.charAt(i)))
                    difference++;
                if (difference > 1)
                    break;
                i++;
            }
            if (difference == 1)
                System.out.print(word + " ");
        }
        System.out.println();
    }

    /**
     * writes dictionary to file
     *
     * @param dictionary
     */
    public static void writeToFile(TreeMap<String, String> dictionary) {

        logger.info("Writing to file");
        try (FileWriter fw = new FileWriter("src/main/resources/dictionary.txt")) {
            for (Map.Entry<String, String> row : dictionary.entrySet()) {
                String key = row.getKey();
                String value = row.getValue();
                fw.write(key + "=" + value + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Write complete");

    }

    /**
     * @return the dictionary after reading from file
     */
    public static TreeMap<String, String> readFromFile() {

        logger.info("Reading from file");
        TreeMap<String, String> dictionary = new TreeMap<>();
        try (BufferedReader fr = new BufferedReader(new FileReader("src/main/resources/dictionary.txt"))) {
            String line = fr.readLine();
            while (line != null) {
                String[] fullLine = line.split("=");
                String key = fullLine[0];
                String value = fullLine[1];
                dictionary.put(key, value);
                line = fr.readLine();
            }
        } catch (FileNotFoundException fe) {
            logger.severe("File not found");
            System.out.println("File not found");
        } catch (IOException e) {
            logger.severe(e.getMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        logger.info("Read complete");
        return dictionary;
    }

    /**
     * Dictionary functionalities
     */
    public static void useDictionary() {

        Scanner scanner = new Scanner(System.in);
        TreeMap<String, String> dictionary = readFromFile();

        int choice = 0;
        while (choice != 5) {
            System.out.println("1.Search Word");
            System.out.println("2.add Word");
            System.out.println("3.Similar words");
            System.out.println("4.Starting with");
            System.out.println("5.Exit");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("String to search");
                    scanner.nextLine();
                    String toSearch = scanner.nextLine();
                    if (dictionary.containsKey(toSearch.toLowerCase())) {
                        logger.info("Searching word " + toSearch);
                        System.out.println(toSearch + " = " + dictionary.get(toSearch.toLowerCase()));
                    } else {
                        logger.warning(toSearch + " Word not present in dictionary");
                        System.out.println("Does not exist");
                    }
                    break;
                case 2:
                    System.out.println("Add word");
                    scanner.nextLine();
                    String toAdd = scanner.nextLine();
                    if (dictionary.containsKey(toAdd.toLowerCase(Locale.ROOT))) {
                        logger.warning(toAdd + "Already exists");
                        System.out.println("Already exists");
                    } else {
                        System.out.println("Enter meaning");
                        String meaning = scanner.nextLine();
                        dictionary.put(toAdd.toLowerCase(), meaning);
                        logger.info(toAdd + "Word added to dictionary");
                    }
                    break;
                case 3:
                    System.out.println("Enter word to search similar");
                    scanner.nextLine();
                    String similar = scanner.nextLine();
                    logger.info("Searching similar words to " + similar);
                    similarWords(dictionary, similar);
                    break;
                case 4:
                    System.out.println("Enter word starting with");
                    scanner.nextLine();
                    String key = scanner.nextLine();
                    logger.info("Searching words staring with " + key);
                    startingWith(dictionary, key);
                    break;
                case 5:
                    writeToFile(dictionary);
                    return;
                default:
                    System.out.println("Enter valid choice");
            }
        }
    }

    /**
     * @param args
     */

    /*public static void main(String[] args) throws IOException {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resources/loggingHobby.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger = Logger.getLogger(Dictionary.class.getName());
        useDictionary();
    }*/
}
