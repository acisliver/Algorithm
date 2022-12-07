package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Dance Dance Revolution
// https://www.acmicpc.net/problem/2342
public class N2342 {

    static List<FootBoard> SEQUENCE;
    static int[][][] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        SEQUENCE = Arrays.stream(br.readLine().split(" "))
                .map(Integer::valueOf)
                .map(FootBoard::footBoardFromNumber)
                .collect(Collectors.toList());
        int steps = FootBoard.values().length;
        DP = new int[SEQUENCE.size() - 1][steps][steps];

        int answer = dfs(FootBoard.START, FootBoard.START, 0);

        System.out.println(answer);
    }

    private static int dfs(FootBoard leftFoot, FootBoard rightFoot, int step) {

        // 마지막까지 탐색후 종료
        if (step == SEQUENCE.size() - 1) {
            return 0;
        }

        // 이미 초기화된 경우 가지치기
        if (DP[step][leftFoot.getNumber()][rightFoot.getNumber()] != 0) {
            return DP[step][leftFoot.getNumber()][rightFoot.getNumber()];
        }

        FootBoard next = SEQUENCE.get(step);

        // 왼발 이동
        int leftPower = leftFoot.move(next) + dfs(next, rightFoot, step + 1);

        // 오른발 이동
        int rightPower = rightFoot.move(next) + dfs(leftFoot, next, step + 1);

        return DP[step][leftFoot.getNumber()][rightFoot.getNumber()] = Math.min(leftPower, rightPower);
    }

    enum FootBoard {
        START(0), UP(1), DOWN(3), LEFT(2), RIGHT(4);

        private final int number;

        FootBoard(int number) {
            this.number = number;
        }

        public static FootBoard footBoardFromNumber(int number) {
            return Arrays.stream(FootBoard.values())
                    .filter(footBoard -> footBoard.number == number)
                    .findAny()
                    .orElseThrow();
        }

        public int getNumber() {
            return number;
        }

        private boolean isOpposite(FootBoard footBoard) {
            return Math.abs(this.number - footBoard.number) == 2;
        }

        private boolean isAdjacency(FootBoard footBoard) {
            if (this == START) {
                return false;
            }

            int offset = Math.abs(this.number - footBoard.number);

            return offset == 1 || offset == 3;
        }

        private int move(FootBoard moveBoard) {

            // 중앙 지점에서 다른 지점으로
            if (this == START) {
                return 2;
            }

            // 동일한 지점
            if (this == moveBoard) {
                return 1;
            }

            // 인접한 지점
            if (this.isAdjacency(moveBoard)) {
                return 3;
            }

            // 반대 지점
            if (this.isOpposite(moveBoard)) {
                return 4;
            }

            throw new IllegalArgumentException();
        }
    }
}
