package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

import static java.lang.System.in;

// 숨바꼭질 3
// https://www.acmicpc.net/problem/13549
public class N13549 {

    static int N, K;
    static int[] DISTANCE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        K = input[1];
        DISTANCE = new int[100_001];
        Arrays.fill(DISTANCE, -1);
        go(N, K);
        System.out.println(DISTANCE[K]);
    }

    public static void go(int n, int k) {
        LinkedList<Integer> queue = new LinkedList<>(); // deque
        queue.offer(n);
        DISTANCE[n] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == k) {
                return;
            }

            if (cur * 2 <= 100000 && DISTANCE[cur * 2] == -1) {
                queue.addFirst(cur * 2); // 높은 우선순위
                DISTANCE[cur * 2] = DISTANCE[cur];
            }

            if (cur > 0 && DISTANCE[cur - 1] == -1) {
                queue.offer(cur - 1);
                DISTANCE[cur - 1] = DISTANCE[cur] + 1;
            }

            if (cur < 100000 && DISTANCE[cur + 1] == -1) {
                queue.offer(cur + 1);
                DISTANCE[cur + 1] = DISTANCE[cur] + 1;
            }
        }
    }
}
