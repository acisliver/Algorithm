package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// ABCDE
// https://www.acmicpc.net/problem/16120
public class N16120 {

    static int N, M;
    static List<List<Integer>> graph = new ArrayList<>();
    static int ANSWER = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        M = input[1];

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int a = input[0];
            int b = input[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (int i = 0; i < N; i++) {
            boolean[] visited = new boolean[N];
            visited[i] = true;
            search(i, 0, visited);
            if (ANSWER == 1) {
                System.out.println(ANSWER);
                return;
            }
        }

        System.out.println(ANSWER);
    }

    private static void search(int n, int count, boolean[] visited) {
        if (count == 4) {
            ANSWER = 1;
            return;
        }

        for (Integer next : graph.get(n)) {
            if (visited[next]) continue;
            visited[next] = true;
            search(next, count + 1, visited);
            visited[next] = false;
        }
    }
}
