package programers;

import java.util.Arrays;

// N개의 최소공배수
// https://school.programmers.co.kr/learn/courses/30/lessons/12953
public class N12953 {

    public static void main(String[] args) {
        N12953 n = new N12953();
        System.out.println(n.solution(new int[]{2,6,8,14}));
    }
    public int solution(int[] arr) {
        return Arrays.stream(arr)
                .reduce(this::lcm)
                .orElseThrow(IllegalArgumentException::new);
    }

    private int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    private int gcd(int a, int b) {
        int bigger = Math.max(a, b);
        int smaller = Math.min(a, b);

        int rest = bigger % smaller;

        if (rest == 0) {
            return smaller;
        }

        return gcd(smaller, rest);
    }
}
