package inflearn.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

// 부등호
// https://www.acmicpc.net/problem/2529
public class N2529 {

    static int InequalitySignCount;
    static Numbers MIN;
    static Numbers MAX;
    static char[] InequalitySigns;
    static boolean[] VISITED;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        InequalitySignCount = Integer.parseInt(br.readLine());
        InequalitySigns = String.join("", br.readLine().split(" ")).toCharArray();
        MIN = Numbers.maxInstance(InequalitySignCount + 1);
        MAX = Numbers.minInstance(InequalitySignCount + 1);
        int[] numbers = new int[InequalitySignCount + 1];
        Arrays.fill(numbers, -1);
        VISITED = new boolean[10];

        search(0, numbers);

        System.out.println(MAX);
        System.out.println(MIN);
    }

    private static void search(int here, int[] numbers) {

        if (here > InequalitySignCount) {
            if (check(numbers)) {
                MIN = MIN.getMin(new Numbers(numbers.clone()));
                MAX = MAX.getMax(new Numbers(numbers.clone()));
                return;
            }
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (numbers[here] != -1) continue;
            if (VISITED[i]) continue;

            numbers[here] = i;
            VISITED[i] = true;

            search(here + 1, numbers);

            numbers[here] = -1;
            VISITED[i] = false;
        }
    }

    private static boolean check(int[] numbers) {

        for (int i = 0; i < InequalitySigns.length; i++) {
            char inequalitySign = InequalitySigns[i];
            int number1 = numbers[i];
            int number2 = numbers[i + 1];
            boolean result = false;
            switch (inequalitySign) {
                case '<':
                    result = number1 < number2;
                    break;
                case '>':
                    result = number1 > number2;
                    break;
            }
            if (!result) return false;
        }

        return true;
    }

    static class Numbers implements Comparable<Numbers> {

        int[] numbers;


        private Numbers() {

        }

        Numbers(int[] numbers) {
            this.numbers = numbers;
        }

        static Numbers maxInstance(int capacity) {
            Numbers instance = new Numbers();
            instance.numbers = new int[capacity];
            Arrays.fill(instance.numbers, 9);
            return instance;
        }

        static Numbers minInstance(int capacity) {
            Numbers instance = new Numbers();
            instance.numbers = new int[capacity];
            Arrays.fill(instance.numbers, 0);
            return instance;
        }

        Numbers getMax(Numbers others) {
            return this.compareTo(others) > 0 ? this : others;
        }

        Numbers getMin(Numbers others) {
            return this.compareTo(others) < 0 ? this : others;
        }

        @Override
        public int compareTo(Numbers others) {
            for (int i = 0; i < numbers.length; i++) {
                int number = this.numbers[i];
                int other = others.numbers[i];
                if (number == other) continue;

                return Integer.compare(number, other);
            }
            return 0;
        }

        @Override
        public String toString() {
            return Arrays.stream(this.numbers)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(""));
        }
    }
}
