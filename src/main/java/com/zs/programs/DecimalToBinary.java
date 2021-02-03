package com.zs.programs;

import java.util.Scanner;

/**
 *
 */
public class DecimalToBinary {

    /**
     * @param decimal
     * @return binary of the decimal
     */
    public static String convertToBinary(int decimal) {
        int binary[] = new int[32];
        int index = 0;
        StringBuilder binaryString = new StringBuilder();
        while (decimal > 0) {
            binary[index++] = decimal % 2;
            decimal = decimal / 2;
        }
        for (int i = index - 1; i >= 0; i--) {
            binaryString.append(binary[i]);
        }
        return binaryString.toString();
    }

    /*public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter decimal");
        int decimal = scanner.nextInt();
        DecimalToBinary decimalToBinary = new DecimalToBinary();
        String binaryConverted = decimalToBinary.convertToBinary(decimal);
        System.out.println(binaryConverted);

    }*/

}
