package inflearn.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 낚시왕
// https://www.acmicpc.net/problem/17143
public class N17143 {

    static int R, C, M;
    static Shark[][] FISHING;
    static List<Shark> SHARKS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        R = input[0];
        C = input[1];
        M = input[2];
        FISHING = new Shark[R][C];
        SHARKS = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int r = input[0] - 1;
            int c = input[1] - 1;
            int speed = input[2];
            int d = input[3];
            int size = input[4];
            Shark shark = new Shark(r, c, speed, d, size);
            FISHING[r][c] = shark;
            SHARKS.add(shark);
        }

        int answer = 0;

        for (int i = 0; i < C; i++) {
            answer += catchShark(i);
            moveShark();
            eatShark();
        }

        System.out.println(answer);
    }

    private static int catchShark(int col) {

        Shark caughtShark = null;

        for (int i = 0; i < R; i++) {
            Shark shark = FISHING[i][col];
            if (shark == null) continue;
            if (shark.r != i || shark.c != col) {
                FISHING[i][col] = null;
                continue;
            }
            caughtShark = FISHING[i][col];
            FISHING[i][col] = null;
            break;
        }

        if (caughtShark == null) return 0;

        SHARKS.remove(caughtShark);
        return caughtShark.size;
    }

    private static void moveShark() {
        for (Shark shark : SHARKS) {
            shark.move();
        }
    }

    private static void eatShark() {
        List<Shark> eatenShark = new ArrayList<>();

        for (Shark shark : SHARKS) {
            int r = shark.r;
            int c= shark.c;

            if (FISHING[r][c] == null) {
                FISHING[r][c] = shark;
                continue;
            }

            if (FISHING[r][c].r != r || FISHING[r][c].c != c) {
                FISHING[r][c] = shark;
                continue;
            }

            if (shark.size > FISHING[r][c].size) {
                eatenShark.add(FISHING[r][c]);
                FISHING[r][c] = shark;
            } else if (shark.size < FISHING[r][c].size) {
                eatenShark.add(shark);
            }
        }

        SHARKS.removeAll(eatenShark);
    }

    static class Shark {

        private int r;
        private int c;
        private Direction direction;
        private int speed;
        private int size;

        public Shark(int r, int c, int speed, int number, int size) {
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.direction = Direction.getDirection(number);
            this.size = size;
        }

        public void move() {
            switch (direction) {
                case UP:
                    moveUp(r, speed);
                    break;
                case DOWN:
                    moveDown(r, speed);
                    break;
                case RIGHT:
                    moveRight(c, speed);
                    break;
                case LEFT:
                    moveLeft(c, speed);
                    break;
                default:
                    break;
            }
        }

        private void moveUp(int r, int speed) {
            if (r < speed) {
                speed -= r;
                r = 0;
                int where = speed / (R - 1);
                speed = speed % (R - 1);
                if (where % 2 == 0) {
                    r += speed;
                    this.direction = Direction.DOWN;
                } else {
                    r = R - 1;
                    r -= speed;
                }
            } else {
                r -= speed;
            }

            this.r = r;
        }

        private void moveDown(int r, int speed) {
            int offset = R - r - 1;
            if (offset < speed) {
                speed -= offset;
                r = R - 1;
                int where = speed / (R - 1);
                speed = speed % (R - 1);
                if (where % 2 == 0) {
                    this.direction = Direction.UP;
                    r -= speed;
                } else {
                    r = 0;
                    r += speed;
                }
            } else {
                r += speed;
            }

            this.r = r;
        }

        private void moveRight(int c, int speed) {
            int offset = C - c - 1;
            if (offset < speed) {
                speed -= offset;
                c = C - 1;
                int where = speed / (C - 1);
                speed = speed % (C - 1);
                if (where % 2 == 0) {
                    this.direction = Direction.LEFT;
                    c -= speed;
                } else {
                    c = 0;
                    c += speed;
                }
            } else {
                c += speed;
            }

            this.c = c;
        }

        private void moveLeft(int c, int speed) {
            if (c < speed) {
                speed -= c;
                c = 0;
                int where = speed / (C - 1);
                speed = speed % (C - 1);
                if (where % 2 == 0) {
                    this.direction = Direction.RIGHT;
                    c += speed;
                } else {
                    c = C - 1;
                    c -= speed;
                }
            } else {
                c -= speed;
            }

            this.c = c;
        }

        enum Direction {
            UP(1), DOWN(2), RIGHT(3), LEFT(4);

            private final int number;

            Direction(int number) {
                this.number = number;
            }

            static Direction getDirection(int number) {
                return Arrays.stream(Direction.values())
                        .filter(d -> d.number == number)
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException(String.valueOf(number)));
            }
        }
    }
}
