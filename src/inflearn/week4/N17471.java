package inflearn.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 게리맨더링
// https://www.acmicpc.net/problem/17471
/*
    1. 선서구를 나눈다 (부분집합)
    2. 나눈 선거구가 유효한지 검사한다.
        2.1. 나누어진 선거구에 적어도 하나의 선거구가 있는지 검사
        2.2. 나누어진 선거구가 연결되어 있는지 검사 (BFS)
    3. 선거구 인구수 총합 계산
    4. 최소 인구수의 차 구하기
 */
public class N17471 {

    static int N, ANSWER;
    static int[] POPULARITY;
    static boolean[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ANSWER = Integer.MAX_VALUE;
        POPULARITY = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        graph = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int connection = input[0];
            for (int j = 0; j < connection; j++) {
                int next = input[j + 1] - 1;
                graph[i][next] = true;
            }
        }

        divideWard(0, new boolean[N]);

        if (ANSWER == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(ANSWER);
    }

    private static void divideWard(int index, boolean[] selected) {

        if (index == N) {
            List<Integer> ward1 = new ArrayList<>();
            List<Integer> ward2 = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                if (selected[i]) {
                    ward1.add(i);
                } else {
                    ward2.add(i);
                }
            }

            if (isValid(ward1) && isValid(ward2)) {
                int sum1 = getSum(ward1);
                int sum2 = getSum(ward2);
                ANSWER = Math.min(ANSWER, Math.abs(sum1 - sum2));
            }
            return;
        }

        selected[index] = true;
        divideWard(index + 1, selected);

        selected[index] = false;
        divideWard(index + 1, selected);
    }

    private static boolean isValid(List<Integer> ward) {
        return !ward.isEmpty() && isConnected(ward);
    }

    private static boolean isConnected(List<Integer> ward) {

        if (ward.isEmpty()) return false;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N];
        int start = ward.get(0);
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            boolean[] nexts = graph[cur];

            for (int i = 0; i < nexts.length; i++) {
                boolean next = nexts[i];
                if (!next) continue;
                if (visited[i]) continue;
                if (!ward.contains(i)) continue;

                visited[i] = true;
                queue.offer(i);
            }
        }

        for (Integer w : ward) {
            if (!visited[w]) return false;
        }

        return true;
    }

    private static int getSum(List<Integer> ward) {
        return ward.stream()
                .mapToInt(w -> POPULARITY[w])
                .sum();
    }
}
