package com.zs.programs;

import java.util.Scanner;

/**
 * Program prints prime numbers from 1 to N
 */
public class PrimeNumbers1ToN {

    /**
     * @param number
     */
    public void printPrimes(int number) {

        for (int i = 2; i < number; i++) {
            int flag = 0;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0)
                System.out.print(i + " ");
        }

    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number");
        int number = scanner.nextInt();
        PrimeNumbers1ToN primeNumbers1ToN = new PrimeNumbers1ToN();
        primeNumbers1ToN.printPrimes(number);

    }
}
