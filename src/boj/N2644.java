package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.lang.System.in;

// 촌수계산
// https://www.acmicpc.net/problem/2644
public class N2644 {

    static int N, A, B, M;
    static int ANSWER = -1;
    static boolean[] VISTED;
    static List<List<Integer>> GRAPH = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        N = Integer.parseInt(br.readLine());
        VISTED = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            GRAPH.add(new LinkedList<>());
        }
        int[] ints = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        A = ints[0];
        B = ints[1];
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            ints = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = ints[0];
            int to = ints[1];
            GRAPH.get(from).add(to);
            GRAPH.get(to).add(from);
        }

        VISTED[A] = true;
        solve(A, 0);

        System.out.println(ANSWER);
    }

    private static void solve(int from, int count) {
        if (from == B) {
            ANSWER = count;
            return;
        }

        for (Integer to : GRAPH.get(from)) {
            if (VISTED[to]) continue;
            VISTED[to] = true;
            solve(to, count + 1);
            VISTED[to] = false;
        }
    }
}
