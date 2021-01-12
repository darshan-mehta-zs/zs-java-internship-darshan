package com.zs.exe1;

import java.util.Scanner;
import java.util.Stack;

public class Bodmas {

    public int evaluate(String expression) {

        Stack<Integer> numbers = new Stack<>();
        Stack<Character> operands = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {

            if (expression.charAt(i) == ' ')
                continue;
            if (expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                StringBuilder stringBuilder = new StringBuilder();
                while (i < expression.length() && expression.charAt(i) >= '0' && expression.charAt(i) <= '9')
                    stringBuilder.append(expression.charAt(i++));
                numbers.push(Integer.parseInt(stringBuilder.toString()));
            } else if (expression.charAt(i) == '(')
                operands.push(expression.charAt(i));
            else if (expression.charAt(i) == ')') {
                while (operands.peek() != '(')
                    numbers.push(applyOp(operands.pop(), numbers.pop(), numbers.pop()));
                operands.pop();
            } else if (expression.charAt(i) == '+' || expression.charAt(i) == '-' ||
                    expression.charAt(i) == '*' || expression.charAt(i) == '/') {
                while (!operands.empty() && hasPrecedence(expression.charAt(i), operands.peek()))
                    numbers.push(applyOp(operands.pop(), numbers.pop(), numbers.pop()));
                operands.push(expression.charAt(i));
            }
        }
        while (!operands.empty())
            numbers.push(applyOp(operands.pop(), numbers.pop(), numbers.pop()));
        return numbers.pop();

    }

    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    public static int applyOp(char op, int b, int a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return 0;
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter expression");
        String expression = scanner.nextLine();
        Bodmas bodmas = new Bodmas();
        int answer = bodmas.evaluate(expression);
        System.out.println("Answer is " + answer);
    }

}
