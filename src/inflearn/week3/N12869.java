package inflearn.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

// 뮤탈리스크
// https://www.acmicpc.net/problem/12869
public class N12869 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<Integer > healths = Arrays.stream(br.readLine().split(" "))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        int size = 3 - healths.size();
        for (int i = 0; i < size; i++) {
            healths.add(0);
        }

        int count = attack(healths.get(0),healths.get(1), healths.get(2));

        System.out.println(count);
    }


    private static int attack(int scv1, int scv2, int scv3) {

        int[][] damages =
                {
                        {9, 3, 1},
                        {9, 1, 3},
                        {3, 1, 9},
                        {3, 9, 1},
                        {1, 3, 9},
                        {1, 9, 3}
                };
        int[][][] visited = new int[61][61][61];
        visited[scv1][scv2][scv3] = 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{scv1, scv2, scv3});

        while (!queue.isEmpty()) {

            int[] curHealth = queue.poll();
            int cur1 = curHealth[0];
            int cur2 = curHealth[1];
            int cur3 = curHealth[2];

            if (visited[0][0][0] > 0) break;

            for (int[] damage : damages) {
                int next1 = Math.max(0, cur1 - damage[0]);
                int next2 = Math.max(0, cur2 - damage[1]);
                int next3 = Math.max(0, cur3 - damage[2]);
                if (visited[next1][next2][next3] != 0) continue;
                visited[next1][next2][next3] = visited[cur1][cur2][cur3] + 1;
                queue.offer(new int[]{next1, next2, next3});
            }

        }

        return visited[0][0][0] - 1;
    }

}
