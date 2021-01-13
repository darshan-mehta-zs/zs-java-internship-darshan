package com.zs.exe3;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Dictionary {

    public static boolean startsWith(String word, String key) {
        if (key.length() > word.length())
            return false;
        for (int i = 0; i < key.length(); i++) {
            if (word.charAt(i) != key.charAt(i))
                return false;
        }
        return true;
    }

    public static void startingWith(TreeMap<String, String> dictionary, String key) {
        Map<String, String> similarWordsMap = dictionary.tailMap(key);
        Set<String> words = similarWordsMap.keySet();
        for (String word : words) {
            if (startsWith(word, key))
                System.out.print(word + " ");
        }
        System.out.println();
    }

    public static void similarWords(TreeMap<String, String> dictionary, String similar) {
        Set<String> words = dictionary.keySet();
        for (String word : words) {
            int i = 0;
            int difference = 0;
            if (word.length() != similar.length())
                continue;
            while (i < word.length()) {
                if (word.charAt(i) != similar.charAt(i))
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

    public static void writeToFile(TreeMap<String, String> dictionary) {
        try {
            FileWriter fw = new FileWriter("src/com/zs/exe3/dictionary.txt");
            for (Map.Entry<String, String> row : dictionary.entrySet()) {
                String key = row.getKey();
                String value = row.getValue();
                fw.write(key + "=" + value + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TreeMap<String, String> readFromFile() {
        BufferedReader fr = null;
        TreeMap<String, String> dictionary = new TreeMap<>();
        try {
            fr = new BufferedReader(new FileReader("src/com/zs/exe3/dictionary.txt"));
            String line = fr.readLine();
            while (line != null) {
                String[] fullLine = line.split("=");
                String key = fullLine[0];
                String value = fullLine[1];
                dictionary.put(key, value);
                line = fr.readLine();
            }
        } catch (FileNotFoundException fe) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return dictionary;
    }

    public static void useDictionary() {
        Scanner scanner = new Scanner(System.in);
        TreeMap<String, String> dictionary = readFromFile();
        //LinkedHashMap<String, String> dictionary = new LinkedHashMap<>();

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
                    if (dictionary.containsKey(toSearch))
                        System.out.println(toSearch + " = " + dictionary.get(toSearch));
                    else
                        System.out.println("Does not exist");
                    break;
                case 2:
                    System.out.println("Add word");
                    scanner.nextLine();
                    String toAdd = scanner.nextLine();
                    if (dictionary.containsKey(toAdd))
                        System.out.println("Already exists");
                    else {
                        System.out.println("Enter meaning");
                        String meaning = scanner.nextLine();
                        dictionary.put(toAdd, meaning);
                    }
                    break;
                case 3:
                    System.out.println("Enter word to search similar");
                    scanner.nextLine();
                    String similar = scanner.nextLine();
                    similarWords(dictionary, similar);
                    break;
                case 4:
                    System.out.println("Enter word starting with");
                    scanner.nextLine();
                    String key = scanner.nextLine();
                    startingWith(dictionary, key);
                    break;
                case 5:
                    writeToFile(dictionary);
                    System.exit(0);
                default:
                    System.out.println("Enter valid choice");
            }
        }
    }

    public static void main(String[] args) {

        useDictionary();

    }

}
