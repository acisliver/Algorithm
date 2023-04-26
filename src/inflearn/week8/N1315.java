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
    static int[][][] DP = new int[1004][1004][54];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        QUESTS = new int[N][3];
        for (int i = 0; i < N; i++) {
            QUESTS[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }


        for (int s = 1; s <= 1000; s++) {
            for (int i = 1; i <= 1000; i++) {
                for (int q = 1; q <= N; q++) {
                    int[] quest = QUESTS[q];
                    int reqStr = quest[0];
                    int reqInt = quest[1];
                    int point = quest[2];
                    if (reqStr <= s || reqInt <= i) {
                        DP[s][i][q] += 1;

                    }
                }
            }
        }

    }

}
