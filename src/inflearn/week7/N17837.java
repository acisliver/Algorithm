package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 새로운 게임 2
// https://www.acmicpc.net/problem/17837
public class N17837 {

    static int N, K;
    static Kan[][] BOARD;
    static List<int[]> MALS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        K = input[1];
        BOARD = new Kan[N + 1][N + 1];
        MALS = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 1; j <= N; j++) {
                BOARD[i][j] = new Kan(input[j - 1]);
            }
        }

        for (int i = 0; i < K; i++) {
            int[] mal = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            MALS.add(mal);
            BOARD[mal[0]][mal[1]].mals.add(i);
        }

        System.out.println(play());
    }

    static int play() {

        int[] dR = {0, 0, 0, -1, 1};
        int[] dC = {0, 1, -1, 0, 0};

        int turn = 0;

        while (turn <= 1000) {
            turn++;

            // 순서대로 말 이동
            for (int n = 0; n < MALS.size(); n++) {
                int[] mal = MALS.get(n);
                int curR = mal[0];
                int curC = mal[1];
                Kan curKan = BOARD[curR][curC];
                List<Integer> curMals = curKan.mals;

                // 현재 칸에 말이 모두 모였으면 게임 종료
                if (curMals.size() == N) return turn;

                // 말 이동
                int direction = mal[2];
                int nextR = curR + dR[direction];
                int nextC = curC + dC[direction];

                // 파란색인 경우
                if (nextR < 1 || nextC < 1 || nextR > N || nextC > N || BOARD[nextR][nextC].color == 2) {
                    mal[2] = getReverse(direction);
                    nextR = curR + dR[mal[2]];
                    nextC = curC + dC[mal[2]];
                    if (nextR < 1 || nextC < 1 || nextR > N || nextC > N || BOARD[nextR][nextC].color == 2) {
                        continue;
                    }
                }

                Kan nextKan = BOARD[nextR][nextC];
                List<Integer> nextMals = nextKan.mals;

                // 흰색인 경우
                if (nextKan.color == 0) {
                    moveToWhite(n, curMals, nextR, nextC, nextMals);
                    if (isOver()) return turn;
                    continue;
                }

                // 빨간색인 경우
                if (nextKan.color == 1) {
                    moveToRed(n, curMals, nextR, nextC, nextMals);
                    if (isOver()) return turn;
                    continue;
                }

            }

        }

        return -1;
    }

    private static boolean isOver() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (BOARD[i][j].mals.size() >= 4) return true;
            }
        }
        return false;
    }

    private static void moveToWhite(int n, List<Integer> curMals, int nextR, int nextC, List<Integer> nextMals) {
        // 어디까지 n번 말인지 확인
        int index = curMals.indexOf(n);

        // 움직일 말들
        List<Integer> movers = new ArrayList<>();

        // 현재 칸에서 움직일 말 추출
        int size = curMals.size();
        for (int i = index; i < size; i++) {
            int mover = curMals.remove(index);
            movers.add(mover);
        }

        // 움직인 말들의 좌표 이동
        for (Integer mover : movers) {
            MALS.get(mover)[0] = nextR;
            MALS.get(mover)[1] = nextC;
            nextMals.add(mover);
        }
    }

    private static void moveToRed(int n, List<Integer> curMals, int nextR, int nextC, List<Integer> nextMals) {

        int index = curMals.indexOf(n);

        List<Integer> movers = new ArrayList<>();

        int size = curMals.size();
        for (int i = index; i < size; i++) {
            int mover = curMals.remove(index);
            movers.add(mover);
        }

        // 움직일 말들을 뒤집어준 뒤 다음 칸에 추가
        Collections.reverse(movers);

        for (Integer mover : movers) {
            MALS.get(mover)[0] = nextR;
            MALS.get(mover)[1] = nextC;
            nextMals.add(mover);
        }
    }

    private static int getReverse(int direction) {
        switch (direction) {
            case 1:
                return 2;
            case 2:
                return 1;
            case 3:
                return 4;
            case 4:
                return 3;
        }
        return -1;
    }

    static class Kan {

        int color;
        List<Integer> mals;

        public Kan(int color) {
            this.color = color;
            this.mals = new ArrayList<>();
        }

    }

}
