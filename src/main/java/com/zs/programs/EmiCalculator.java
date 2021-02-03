package com.zs.programs;

import java.util.Scanner;

/**
 * Program to calculate EMI based on principal amount, rate of interest and number of years
 * Formula for calculating emi is (P.r.(1+r)n) / ((1+r)n – 1)
 */

public class EmiCalculator {

    float principal;
    float rate;
    int numberOfYears;

    /**
     * Takes the input principal amount rate of interest and number of years
     */
    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter principal amount");
        principal = scanner.nextFloat();
        System.out.println("Enter rate of interest");
        rate = scanner.nextFloat();
        System.out.println("Enter number of years");
        numberOfYears = scanner.nextInt();
    }


    /**
     * Calculates the EMI by formula (P.r.(1+r)^n) / ((1+r)^n – 1)
     *
     * @return the EMI
     */
    public double calculateEmi() {

        rate = rate / (12 * 100);
        numberOfYears = numberOfYears * 12;
        double numerator = principal * rate * Math.pow(1 + rate, numberOfYears);
        double denominator = Math.pow(1 + rate, numberOfYears) - 1;
        return numerator / denominator;

    }

    /**
     * @param args
     */
    /*public static void main(String[] args) {

        EmiCalculator emiCalculator = new EmiCalculator();
        emiCalculator.input();
        double emi = emiCalculator.calculateEmi();
        System.out.println("EMI amount is " + emi);

    }*/

}
