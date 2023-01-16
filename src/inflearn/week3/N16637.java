package inflearn.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 괄호 추가하기
// https://www.acmicpc.net/problem/16637
public class N16637 {

    static int N, ANSWER;
    static List<Integer> OPERANDS;
    static List<Character> OPERATORS;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ANSWER = Integer.MIN_VALUE;
        String expression = br.readLine();
        char[] input = expression.toCharArray();
        OPERANDS = new ArrayList<>();
        OPERATORS = new ArrayList<>();

        for (int i = 0; i < input.length; i++) {
            if (i % 2 == 0) OPERANDS.add(Character.getNumericValue(input[i]));
            else OPERATORS.add(input[i]);
        }

        getMax(0, OPERANDS.get(0));

        System.out.println(ANSWER);
    }

    private static void getMax(int index, int result) {

        if (index >= OPERATORS.size()) {
            ANSWER = Math.max(ANSWER, result);
            return;
        }

        // 다음 연산을 먼저하지 않고 진행
        getMax(index + 1, operate(result, OPERATORS.get(index), OPERANDS.get(index + 1)));

        // 다음 연산을 먼저 하고 진행
        if (index + 1 < OPERATORS.size()) {
            int temp = operate(OPERANDS.get(index + 1), OPERATORS.get(index + 1), OPERANDS.get(index + 2));
            getMax(index + 2, operate(result, OPERATORS.get(index), temp));
        }
    }

    private static int operate(int a, char operator, int b) {

        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }

        return 0;
    }
}
