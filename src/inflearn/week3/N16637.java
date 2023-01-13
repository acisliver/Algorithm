package inflearn.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

// 괄호 추가하기
// https://www.acmicpc.net/problem/16637
public class N16637 {

    static int N, ANSWER;
    static List<Character> EXPRESSION;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ANSWER = 0;
        getMax(0, 1);
    }

    private static void getMax(int result, int index) {
        if (index + 1 == N) {
            ANSWER = Math.max(ANSWER, result);
            return;
        }


    }
}
