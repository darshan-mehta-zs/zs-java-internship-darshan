package com.zs.exe2;

import java.math.BigInteger;
import java.util.Scanner;

public class PrimeNumbers1ToN {

    public void printPrimes(int number)
    {
        for(int i=2;i<number;i++)
        {
//            BigInteger bigInteger = new BigInteger(Integer.toString(i));
//            boolean isPrime = bigInteger.isProbablePrime(100);
//            if(isPrime)
//                System.out.print(i + " ");

            int flag = 0;
            for(int j=2;j<=Math.sqrt(i);j++)
            {
                if(i % j == 0)
                {
                    flag = 1;
                    break;
                }
            }
            if(flag == 0)
                System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number");
        int number = scanner.nextInt();
        PrimeNumbers1ToN primeNumbers1ToN = new PrimeNumbers1ToN();
        primeNumbers1ToN.printPrimes(number);
    }
}
