package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 청소년 상어
// https://www.acmicpc.net/problem/19236
public class N19236 {

    static final int[] DR = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] DC = {0, -1, -1, -1, 0, 1, 1, 1};
    static Fish[][] SPACE = new Fish[4][4];
    static int[][] FISHES = new int[20][2];
    static int ANSWER = 0;
    static int DEPTH = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < 8; j += 2) {
                int number = input[j];
                int direction = input[j + 1] - 1;
                Fish fish = new Fish(number, direction);
                SPACE[i][j / 2] = fish;
                FISHES[number] = new int[]{i, j / 2};
            }
        }

        SPACE[0][0].isEaten = true;
        moveShark(0, 0, SPACE[0][0].direction, SPACE[0][0].number);
        System.out.println(ANSWER);
    }

    private static void moveShark(int r, int c, int d, int eat) {
        ANSWER = Math.max(eat, ANSWER);
//        debug(r, c, eat);

        Fish[][] beforeMove = copySpace();
        int[][] beforeFishes = copyFishes();

        moveFish(r, c);

        for (int i = 1; i < 4; i++) {
            int nR = r + DR[d] * i;
            int nC = c + DC[d] * i;

            if (nR < 0 || nC < 0 || nR >= 4 || nC >= 4) continue;

            if (SPACE[nR][nC].isEaten) continue;

            DEPTH++;
            SPACE[nR][nC].isEaten = true;
            moveShark(nR, nC, SPACE[nR][nC].direction, eat + SPACE[nR][nC].number);
            SPACE[nR][nC].isEaten = false;
            DEPTH--;
        }

        SPACE = beforeMove;
        FISHES = beforeFishes;
//        System.out.println("restore");
//        debug(r, c, eat);
    }

    private static void debug(int r, int c, int eat) {
        final char[] directionMark = {'↑', '↖', '←', '↙', '↓', '↘', '→', '↗' };
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Fish fish = SPACE[i][j];
                if (i == r && j == c) {
                    sb.append(" S").append(directionMark[fish.direction]).append(" ");
                    continue;
                }
                if (fish.isEaten) {
                    sb.append("[] ");
                    continue;
                }
                sb
                        .append(String.format("%2d",fish.number))
                        .append(directionMark[fish.direction])
                        .append(" ");
            }
            sb.append("\n");
        }
        System.out.printf("Depth: %d\n", DEPTH);
        System.out.println(sb);
        System.out.printf("Eat: %d\n\n", eat);
    }

    private static Fish[][] copySpace() {
        Fish[][] copy = new Fish[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copy[i][j] = new Fish(SPACE[i][j]);
            }
        }

        return copy;
    }

    private static int[][] copyFishes() {
        int[][] copy = new int[20][2];

        for (int i = 1; i <= 16; i++) {
            copy[i] = new int[]{FISHES[i][0], FISHES[i][1]};
        }

        return copy;
    }

    private static void moveFish(int sR, int sC) {
        for (int i = 1; i <= 16; i++) {
            final int[] fishPos = FISHES[i];
            final int r = fishPos[0];
            final int c = fishPos[1];
            Fish fish = SPACE[r][c];
            final int direction = fish.direction;

            if (fish.isEaten) continue;

            for (int j = 0; j < 8; j++) {
                int nR = r + DR[(direction + j) % 8];
                int nC = c + DC[(direction + j) % 8];

                if (nR < 0 || nC < 0 || nR >= 4 || nC >= 4) continue;

                if (nR == sR && nC == sC) continue;

                fish.direction = (direction + j) % 8;

                Fish temp = SPACE[r][c];
                SPACE[r][c] = SPACE[nR][nC];
                SPACE[nR][nC] = temp;

                FISHES[SPACE[r][c].number] = new int[]{r, c};
                FISHES[SPACE[nR][nC].number] = new int[]{nR, nC};
//                System.out.printf("Move #%d\n", i);
//                debug(sR, sC, -1);
                break;
            }
        }
    }

    static class Fish {
        int number;
        int direction;
        boolean isEaten = false;

        public Fish(int number, int direction) {
            this.number = number;
            this.direction = direction;
        }

        public Fish(Fish fish) {
            this.number = fish.number;
            this.direction = fish.direction;
            this.isEaten = fish.isEaten;
        }
    }
}
