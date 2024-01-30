package leetcode;

import java.util.Stack;

public class N150 {
    public int evalRPN(String[] tokens) {
        Stack<Integer> operand = new Stack<>();

        for (String token : tokens) {
            if (isNumeric(token)) {
                operand.push(Integer.parseInt(token));
            } else {
                int a = operand.pop();
                int b = operand.pop();
                int result = calc(b, a, token);
                System.out.println(result);
                operand.push(result);
            }
        }

        return operand.pop();
    }

    private boolean isNumeric(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }

        return true;
    }

    private int calc(int a, int b, String op) {
        switch (op) {
            case "+" -> {
                return a + b;
            }
            case "-" -> {
                return a - b;
            }
            case "*" -> {
                return a * b;
            }
            case "/" -> {
                return a / b;
            }
        }
        throw new AssertionError();
    }
}
