package inflearn.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 보석 상자
// https://www.acmicpc.net/problem/2792
/*
    1. 입력의 범위가 너무 크므로 이진 탐색 적용
    2. mid값을 최대로 보석을 나누어 주었을 때 몇명이 필요한지 계산
    3. n보다 많은 사람이 필요하다면 mid를 내림
    4. n이하의 사람이 필요하다면 answer 갱신 및 mid를 올려봄
 */
public class N2792 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = input[0];
        int m = input[1];
        int max = 0;
        int[] jewelry = new int[m];
        for (int i = 0; i < m; i++) {
            jewelry[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, jewelry[i]);
        }

        int lo = 0;
        int hi = max + 1;
        int sum;
        int answer = 0;
        while (lo + 1< hi) {
            int mid = (lo + hi) >>> 1;
            sum = 0;
            for (int i = 0; i < m; i++) {   // mid를 최대로 몇 명에게 나눠줄 수 있는지
                sum += jewelry[i] / mid;    // 보석 jewelry[i]개를 mid개로 쪼개 k명에게 준다.
                if (jewelry[i] % mid != 0) {// mid보다 작은 개수의 보석이 남아서 한 명한테 더 줌
                    sum += 1;
                }
            }

            if (sum > n) {  // 질투심이 더 커야한다.
                lo = mid + 1;
            } else {        // 질투심이 더 작아도 될거 같다.
                hi = mid - 1;
                answer = mid;
            }
        }

        System.out.println(answer);
    }
}
