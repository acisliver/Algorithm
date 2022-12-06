package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 비숍
// https://www.acmicpc.net/problem/1799
public class N1799 {

    static int N;
    static int ANSWER;
    static int[][] BOARD;
    static boolean[][] VISITED;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ANSWER = 0;
        BOARD = new int[N][N];
        VISITED = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            BOARD[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        dfs(0,true,0); // 검은색 칸 탐색
        dfs(0,false, ANSWER); // 흰색 칸 탐색

        System.out.println(ANSWER);
    }

    static void dfs(int index, boolean black, int bishop) {

        for (int i = index; i < N * N; i++) {
            int x = i / N;
            int y = i % N;

            if (BOARD[x][y] == 0)
                continue;

            if (isBlack(x, y) != black)
                continue;

            if (!check(x, y))
                continue;

            VISITED[x][y] = true;
            dfs(i + 1, black, bishop + 1);
            VISITED[x][y] = false;
        }

        ANSWER = Math.max(ANSWER, bishop);
    }

    static boolean isBlack(int x, int y) {
        return (x % 2 == 0 && y % 2 == 0) || (x % 2 == 1 && y % 2 == 1);
    }

    // 현재 위치에서 윗쪽 대각선에 비숍이 있는지 체크
    // 아랫쪽은 아직 비숍을 두지 않음
    static boolean check(int x, int y) {
        int[] dx = {-1,-1};
        int[] dy = {-1,1};

        for (int i = 0; i < 2; i++){
            int nextX = x;
            int nextY = y;

            while (true) {
                if (0 > nextX || nextX >= N || 0 > nextY || nextY >= N)
                    break;

                if (VISITED[nextX][nextY])
                    return false;

                nextX += dx[i];
                nextY += dy[i];
            }
        }
        return true;
    }
}
