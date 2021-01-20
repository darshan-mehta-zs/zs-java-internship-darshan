package main.java.com.zs.bodmas;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 *
 */
public class Bodmas {

    /**
     * @param expression
     * @return the value after evaluating the expression
     */
    public int evaluate(String expression) {

        Deque<Integer> numbers = new ArrayDeque<>();
        Deque<Character> operands = new ArrayDeque<>();

        for (int i = 0; i < expression.length(); i++) {

            if (expression.charAt(i) == ' ')
                continue;
            if (expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                StringBuilder stringBuilder = new StringBuilder();
                while (i < expression.length() && expression.charAt(i) >= '0' && expression.charAt(i) <= '9')
                    stringBuilder.append(expression.charAt(i++));
                i--;
                numbers.push(Integer.parseInt(stringBuilder.toString()));
            } else if (expression.charAt(i) == '(')
                operands.push(expression.charAt(i));
            else if (expression.charAt(i) == ')') {
                while (operands.peek() != '(')
                    numbers.push(applyOperation(operands.pop(), numbers.pop(), numbers.pop()));
                operands.pop();
            } else if (expression.charAt(i) == '+' || expression.charAt(i) == '-' ||
                    expression.charAt(i) == '*' || expression.charAt(i) == '/') {
                while (!operands.isEmpty() && hasPrecedence(expression.charAt(i), operands.peek()))
                    numbers.push(applyOperation(operands.pop(), numbers.pop(), numbers.pop()));
                operands.push(expression.charAt(i));
            }
        }
        while (!operands.isEmpty())
            numbers.push(applyOperation(operands.pop(), numbers.pop(), numbers.pop()));
        return numbers.pop();

    }

    /**
     * @param op1
     * @param op2
     * @return boolean based on precedence
     */
    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    /**
     * @param op
     * @param num2
     * @param num1
     * @return the value after performing arithmetic operation
     */
    public static int applyOperation(char op, int num2, int num1) {
        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                return 0;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter expression");
        String expression = scanner.nextLine();
        Bodmas bodmas = new Bodmas();
        int answer = bodmas.evaluate(expression);
        System.out.println("Answer is " + answer);
    }

}
