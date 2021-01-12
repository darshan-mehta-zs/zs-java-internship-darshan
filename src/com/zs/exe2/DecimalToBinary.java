package com.zs.exe2;

import java.util.Scanner;

public class DecimalToBinary {

    String convertToBinary(int decimal) {
        return Integer.toBinaryString(decimal);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter decimal");
        int decimal = scanner.nextInt();
        DecimalToBinary decimalToBinary = new DecimalToBinary();
        String binaryConverted = decimalToBinary.convertToBinary(decimal);
        System.out.println(binaryConverted);

    }
}
