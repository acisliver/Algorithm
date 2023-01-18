package inflearn.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Function;

// 숨바꼭질 5
// https://www.acmicpc.net/problem/17071
public class N17071 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int catcher = input[0];
        int hider = input[1];
        int[][] catchers = new int[2][500_001];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(catcher);
        catchers[0][catcher] = 1;
        int turn = 0;

        while (!queue.isEmpty()) {

            if (catchers[turn % 2][hider] != 0) {
                System.out.println(turn);
                return;
            }

            turn += 1;
            hider += turn;
            if (hider > 500_000) break;

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curCatcher = queue.poll();
                for (Move move : Move.values()) {
                    int next = move.move.apply(curCatcher);
                    if (next < 0 || next >= catchers[0].length) continue;
                    if (catchers[turn % 2][next] != 0) continue;
                    catchers[turn % 2][next] = catchers[(turn + 1) % 2][curCatcher] + 1;
                    queue.offer(next);
                }
            }
        }
        System.out.println(-1);
    }

    enum Move {
        PLUS(pos -> pos + 1),
        MINUS(pos -> pos - 1),
        MULTIPLY(pos -> pos * 2);

        private final Function<Integer, Integer> move;

        Move(Function<Integer, Integer> move) {
            this.move = move;
        }
    }
}
