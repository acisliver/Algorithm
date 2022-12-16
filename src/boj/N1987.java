package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// 알파벳
// https://www.acmicpc.net/problem/1987
public class N1987 {

    static int R, C, ANSWER;
    static String[][] BOARD;
    static final int[] DR = {1, -1, 0, 0};
    static final int[] DC = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] RC = br.readLine().split(" ");
        R = Integer.parseInt(RC[0]);
        C = Integer.parseInt(RC[1]);
        ANSWER = 0;
        BOARD = new String[R][C];

        for (int i = 0; i < R; i++) {
            BOARD[i] = br.readLine().split("");
        }

        Set<String> record = new HashSet<>();
        dfs(0, 0, record);

        System.out.println(ANSWER);
    }

    private static void dfs(int r, int c, Set<String> record) {

        record.add(BOARD[r][c]);
        ANSWER = Math.max(ANSWER, record.size());

        for (int i = 0; i < 4; i++) {
            int nextR = r + DR[i];
            int nextC = c + DC[i];

            if (nextR < 0 || nextC < 0 || nextR >= R || nextC >= C) continue;

            String nextCell = BOARD[nextR][nextC];

            if (record.contains(nextCell)) continue;
            
            dfs(nextR, nextC, record);
            record.remove(nextCell);
        }
    }
}
