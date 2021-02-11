package com.zs.programs;

import java.util.Scanner;

/**
 * Program checks whether the given number is palindrome or not
 */
public class PalindromeNumber {

    /**
     * @param number
     * @return reverse of number
     */
    int reverse(int number) {
        int reverseNumber = 0;
        while (number > 0) {
            int rem = number % 10;
            reverseNumber = reverseNumber * 10 + rem;
            number = number / 10;
        }
        return reverseNumber;
    }

    /**
     * @param number
     * @return whether the number is palindrome or not
     */
    boolean checkPalindrome(int number) {

        if (number < 0)
            return false;
        if (number == reverse(number))
            return true;
        return false;

    }

    /**
     * @param args
     */
    /*public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number");
        int number = scanner.nextInt();
        PalindromeNumber palindromeNumber = new PalindromeNumber();
        boolean isPalindrome = palindromeNumber.checkPalindrome(number);
        if (isPalindrome)
            System.out.println(number + " is palindrome");
        else
            System.out.println(number + " is not palindrome");

    }*/

}
