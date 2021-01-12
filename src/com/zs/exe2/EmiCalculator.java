package com.zs.exe2;

/*
Formula for calculating emi
(P.r.(1+r)n) / ((1+r)n – 1)
*/

import java.util.Scanner;

public class EmiCalculator {

    int principal;
    float rate;
    int numberOfYears;

    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter principal amount");
        principal = scanner.nextInt();
        System.out.println("Enter rate of interest");
        rate = scanner.nextFloat();
        System.out.println("Enter number of years");
        numberOfYears = scanner.nextInt();
    }

    public double calculateEmi() {
//       (P.r.(1+r)n) / ((1+r)n – 1)
        rate = rate / (12 * 100);
        numberOfYears = numberOfYears * 12;
        double numerator = principal * rate * Math.pow(1 + rate, numberOfYears);
        double denominator = Math.pow(1 + rate, numberOfYears) - 1;
        return numerator / denominator;
    }

    public static void main(String[] args) {

        EmiCalculator emiCalculator = new EmiCalculator();
        emiCalculator.input();
        double emi = emiCalculator.calculateEmi();
        System.out.println("EMI amount is " + emi);
    }

}
