package inflearn.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

// 경찰차
// https://www.acmicpc.net/problem/2618
public class N2618 {

    static int N, W;
    static boolean[][] MAP;
    static int[][] CASES;
    static int[][] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());
        MAP = new boolean[N + 1][N + 1];
        CASES = new int[W][2];
        DP = new int[W][2];
        for (int i = 0; i < W; i++) {
            CASES[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        DP[0][0] = getDistance(new int[]{1, 1}, CASES[0]);
        DP[0][1] = getDistance(new int[]{N, N}, CASES[0]);
        DP[1][0] = Math.min(
                getDistance(CASES[0], CASES[1]) + DP[0][0],
                getDistance(new int[]{1, 1}, CASES[1]) + DP[0][1]
        );
        DP[1][1] = Math.min(
                getDistance(CASES[0], CASES[1]) + DP[0][1],
                getDistance(new int[]{N, N}, CASES[1]) + DP[0][0]
        );
        for (int i = 2; i < W; i++) {
            DP[i][0] = Math.min(
                getDistance(CASES[i - 1], CASES[i]) + DP[i - 1][0],
                getDistance(CASES[i - 2], CASES[i]) + DP[i - 1][1]
            );
            DP[i][1] = Math.min(
                    getDistance(CASES[i - 1], CASES[i]) + DP[i - 1][1],
                    getDistance(CASES[i - 2], CASES[i]) + DP[i - 1][0]
            );
        }

        System.out.println(Math.min(DP[W - 1][0], DP[W - 1][1]));
        printChoice();
    }

    private static void printChoice() {
        Stack<Integer> stack = new Stack<>();
        int curCar;
        if (DP[W - 1][0] > DP[W - 1][1]) {
            curCar = 2;
        } else {
            curCar = 1;
        }
        stack.push(curCar);

        for (int i = W - 2; i >= 0; i--) {
            int car1 = curCar - DP[i][0];
            int car2 = curCar - DP[i][1];
            int dist = getDistance(CASES[i + 1], CASES[i]);
            if (car1 == dist) stack.push(1);
            else stack.push(2);
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    private static int getDistance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}
