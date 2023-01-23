package inflearn.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 완전 이진 트리
// https://www.acmicpc.net/problem/9934
public class N9934 {

    static int LEVEL, INDEX;
    static int[] HISTORY;
    static int[][] FULL_BINARY_TREE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LEVEL = Integer.parseInt(br.readLine());
        INDEX = 0;
        HISTORY = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        FULL_BINARY_TREE = new int[LEVEL][(int) Math.pow(2, LEVEL - 1)];

        search(0, 0);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < FULL_BINARY_TREE.length; i++) {
            for (int j = 0; j < FULL_BINARY_TREE[0].length; j++) {
                if (FULL_BINARY_TREE[i][j] == 0) continue;
                sb.append(FULL_BINARY_TREE[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void search(int level, int node) {
        if (level == LEVEL) {
            return;
        }

        // left
        search(level + 1, node * 2);
        // allocate
        FULL_BINARY_TREE[level][node] = HISTORY[INDEX];
        INDEX += 1;
        // right
        search(level + 1, node * 2 + 1);
    }
}
