package kakao.blind2022;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/92335
// 구현
class Solution2 {

    public int solution(int n, int k) {

        return (int) Arrays.stream(Integer.toString(n, k).split("0"))
                .filter(v -> v.length() != 0)
                .filter(v -> isPrime(v))
                .count();
    }

    private boolean isPrime(String v) {
        long n = Long.parseLong(v);

        if (n == 1) return false;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }

        return true;
    }
}