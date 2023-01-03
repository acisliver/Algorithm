package inflearn.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 쿼드트리
// https://www.acmicpc.net/problem/1992
public class N1992 {

    static int N;
    static int[][] IMAGE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        IMAGE = new int[N][N];

        for (int i = 0; i < N; i++) {
            IMAGE[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        String result = quad(0, 0, N);

        System.out.println(result);
    }

    private static String quad(int row, int col, int size) {

        if (canCompress(row, col, size)) {
            return String.valueOf(IMAGE[row][col]);
        }

        int offset = size / 2;

        String topLeft = quad(row, col, offset);
        String topRight = quad(row, col + offset, offset);
        String bottomLeft = quad(row + offset, col, offset);
        String bottomRight = quad(row + offset, col + offset, offset);

        return "(" + topLeft + topRight + bottomLeft + bottomRight + ")";
    }

    private static boolean canCompress(int row, int col, int size) {
        int value = IMAGE[row][col];

        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (value != IMAGE[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}
