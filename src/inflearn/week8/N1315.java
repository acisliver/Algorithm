package inflearn.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// RPG
// https://www.acmicpc.net/problem/1315
public class N1315 {

    static int N;
    static int[][] QUESTS;
    static int[][] DP = new int[1004][1004];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        QUESTS = new int[N][3];
        for (int i = 0; i < N; i++) {
            QUESTS[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        DP[1][1] = 1;

        search(1, 1, 0, new boolean[N]);

        System.out.println(DP[1][1]);
    }

    private static void search(int strength, int intelli, int count, boolean[] visited) {
        DP[strength][intelli] = Math.max(count, DP[strength][intelli]);

        for (int j = 0; j < QUESTS.length; j++) {
            int[] quest = QUESTS[j];
            int s = quest[0];
            int i = quest[1];
            int p = quest[2];

            if (visited[j]) continue;

            if (s > strength && i > intelli) continue;

            visited[j] = true;
            for (int k = 0; k <= p; k++) {
                search(strength + k, intelli + p - k, count + 1, visited);
            }
            visited[j] = false;
        }
    }


}
