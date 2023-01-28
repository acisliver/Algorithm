package inflearn.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 종이 조각
// https://www.acmicpc.net/problem/14391
/*
    0은 가로 1은 세로를 의미하는 자료구조를 만든다.
    예시 입력에서
    0 0 0 1
    1 1 0 1
    1 1 1 1
    0 0 1 1
    이것을 비트마스킹으로 저장
 */
public class N14391 {

    static int N, M;
    static int[][] PAPER;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        M = input[1];
        int answer = 0;
        PAPER = new int[N][M];

        for (int i = 0; i < N; i++) {
            PAPER[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        // 모든 경우를 확인
        // 00...00 ~ 11...11
        for (int cutCase = 0; cutCase < (1 << (N * M)); cutCase++) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                int square = 0;
                for (int j = 0; j < M; j++) {
                    int bitIndex = i * M + j;
                    if ((cutCase & (1 << bitIndex)) == 0) { // 가로일 경우
                        square = square * 10 + PAPER[i][j];
                    } else {
                        sum += square;
                        square = 0;
                    }
                }
                sum += square;
            }

            for (int j = 0; j < M; j++) {
                int square = 0;
                for (int i = 0; i < N; i++) {
                    int bitIndex = i * M + j;
                    if ((cutCase & (1 << bitIndex)) != 0) { // 세로일 경우
                        square = square * 10 + PAPER[i][j];
                    } else {
                        sum += square;
                        square = 0;
                    }
                }
                sum += square;
            }

            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }

}
