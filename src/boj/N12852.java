package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// 1로 만들기 2
// https://www.acmicpc.net/problem/12852
public class N12852 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] DP = new boolean[N + 1];

        DP[N] = true;
        Queue<Stack<Integer>> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(N);
        queue.offer(stack);

        while (queue.peek().peek() != 1) {
            Stack<Integer> curStack = queue.poll();

            List<Integer> results = Calculation.calculate(curStack.peek());

            for (Integer result : results) {
                if (DP[result]) continue;

                DP[result] = true;
                Stack<Integer> newStack = new Stack<>();
                newStack.addAll(curStack);
                newStack.add(result);
                queue.offer(newStack);
            }

        }

        System.out.println(queue.peek().size() - 1);
        System.out.println(queue.peek().stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    enum Calculation {

        DIVIDE_THREE(
                n -> n % 3 == 0,
                n -> n / 3
        ),
        DIVIDE_TWO(
                n -> n % 2 == 0,
                n -> n / 2
        ),
        MINUS_ONE(
                n -> true,
                n -> n - 1
        ),
        ;

        private final Predicate<Integer> condition;
        private final Function<Integer, Integer> calculation;

        Calculation(Predicate<Integer> condition, Function<Integer, Integer> calculation) {
            this.condition = condition;
            this.calculation = calculation;
        }

        public static List<Integer> calculate(int n) {
            return Arrays.stream(Calculation.values())
                    .filter(cal -> cal.condition.test(n))
                    .map(cal -> cal.calculation.apply(n))
                    .collect(Collectors.toList());
        }

    }
}
