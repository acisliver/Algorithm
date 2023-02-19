package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 파이프 옮기기 1
// https://www.acmicpc.net/problem/17070
public class N17070 {

    static int N;
    static int[][] MAP, DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        MAP = new int[N][N];
        DP = new int[N][N];
        for (int i = 0; i < N; i++) {
            MAP[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }


        System.out.println(search(0, 1, Direction.HORIZONTAL));
    }

    private static int search(int r, int c, Direction direction) {
        if (r == N - 1 && c == N - 1) return 1;

        switch (direction) {
            case HORIZONTAL:
                if (c + 1 < N && MAP[r][c + 1] != 1) {
                    DP[r][c + 1] += search(r, c + 1, Direction.HORIZONTAL);
                }
                if (r + 1 < N && c + 1 < N && MAP[r + 1][c + 1] != 1 && MAP[r][c + 1] != 1 && MAP[r + 1][c] != 1) {
                    DP[r + 1][c + 1] += search(r + 1, c + 1, Direction.DIAGONAL);
                }
                break;
            case VERTICAL:
                if (r + 1 < N && MAP[r + 1][c] != 1) {
                    DP[r + 1][c] += search(r + 1, c, Direction.VERTICAL);
                }
                if (r + 1 < N && c + 1 < N && MAP[r + 1][c + 1] != 1 && MAP[r][c + 1] != 1 && MAP[r + 1][c] != 1) {
                    DP[r + 1][c + 1] += search(r + 1, c + 1, Direction.DIAGONAL);
                }
                break;
            case DIAGONAL:
                if (r + 1 < N && MAP[r + 1][c] != 1) {
                    DP[r + 1][c] += search(r + 1, c, Direction.VERTICAL);
                }
                if (c + 1 < N && MAP[r][c + 1] != 1) {
                    DP[r][c + 1] += search(r, c + 1, Direction.HORIZONTAL);
                }
                if (r + 1 < N && c + 1 < N && MAP[r + 1][c + 1] != 1 && MAP[r][c + 1] != 1 && MAP[r + 1][c] != 1) {
                    DP[r + 1][c + 1] += search(r + 1, c + 1, Direction.DIAGONAL);
                }
                break;
        }

        return DP[N - 1][N - 1];
    }

    enum Direction {
        HORIZONTAL, VERTICAL, DIAGONAL
    }
}
