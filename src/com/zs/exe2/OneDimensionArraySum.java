package com.zs.exe2;

import java.util.Scanner;

public class OneDimensionArraySum {

    int oneD[];

    OneDimensionArraySum() {
    }

    public void inputOneD() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter length of array");
        int length = scanner.nextInt();
        oneD = new int[length];
        System.out.println("Enter " + length + " elements");
        for (int i = 0; i < length; i++) {
            oneD[i] = scanner.nextInt();
        }
    }

    public int sumOneD() {
        int sum = 0;
        for (int value : oneD)
            sum += value;
        return sum;
    }

    public static void main(String[] args) {

        OneDimensionArraySum oneDimensionArraySum = new OneDimensionArraySum();
        oneDimensionArraySum.inputOneD();
        int sum = oneDimensionArraySum.sumOneD();
        System.out.println("Sum is " + sum);

    }

}
