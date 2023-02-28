package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 카드 정렬하기
// https://www.acmicpc.net/problem/1715
public class N1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> cardCounts = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            cardCounts.offer(Integer.parseInt(br.readLine()));
        }
        int totalSum = 0;
        while (cardCounts.size() > 1) {
            int sum = cardCounts.poll() + cardCounts.poll();
            totalSum += sum;
            cardCounts.offer(sum);
        }

        System.out.println(totalSum);
    }
}
