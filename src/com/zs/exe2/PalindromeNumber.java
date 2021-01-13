package com.zs.exe2;

import java.util.Scanner;

public class PalindromeNumber {

    int reverse(int number) {
        int reverseNumber = 0;
        while (number > 0) {
            int rem = number % 10;
            reverseNumber = reverseNumber * 10 + rem;
            number = number / 10;
        }
        return reverseNumber;
    }

    boolean checkPalindrome(int number) {

        if (number < 0)
            return false;
        if (number == reverse(number))
            return true;
        return false;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number");
        int number = scanner.nextInt();
        PalindromeNumber palindromeNumber = new PalindromeNumber();
        boolean isPalindrome = palindromeNumber.checkPalindrome(number);
        if (isPalindrome)
            System.out.println(number + " is palindrome");
        else
            System.out.println(number + " is not palindrome");

    }

}
