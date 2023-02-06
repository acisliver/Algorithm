package inflearn.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 뱀
// https://www.acmicpc.net/problem/3190
public class N3190 {

    static int N;
    static int[][] MAP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        MAP = new int[N][N];
        MAP[0][0] = 1;
        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            MAP[input[0] - 1][input[1] - 1] = 2;
        }
        int l = Integer.parseInt(br.readLine());
        List<int[]> moves = new LinkedList<>();
        for (int i = 0; i < l; i++) {
            String[] input = br.readLine().split(" ");
            int side = input[1].equals("L") ? -1 : 1;
            moves.add(new int[]{Integer.parseInt(input[0]), side});
        }

        int time = 0;
        int direction = 0;
        // 오른 > 아래 > 왼 > 위
        int[] dR = {0, 1, 0, -1};
        int[] dC = {1, 0, -1, 0};
        LinkedList<int[]> snake = new LinkedList<>();
        snake.add(new int[]{0, 0});

        while (true) {
            int[] head = snake.getLast();

            if (!moves.isEmpty() && moves.get(0)[0] == time) {
                direction += moves.get(0)[1];
                if (direction >= 4) {
                    direction -= 4;
                }
                if (direction < 0) {
                    direction += 4;
                }
                moves.remove(0);
            }

            int nextR = head[0] + dR[direction];
            int nextC = head[1] + dC[direction];

            if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= N) break;
            if (MAP[nextR][nextC] == 1) break;

            if (MAP[nextR][nextC] == 0) {
                int[] tail = snake.removeFirst();
                MAP[tail[0]][tail[1]] = 0;
            }
            MAP[nextR][nextC] = 1;
            snake.add(new int[]{nextR, nextC});

            time += 1;
        }

        System.out.println(time + 1);
    }
}
