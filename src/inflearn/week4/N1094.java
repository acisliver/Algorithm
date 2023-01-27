package inflearn.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 막대기
// https://www.acmicpc.net/problem/1094
/*
    길이가 64인 막대를 자를 때마다 절반의 길이인 막대를 얻는다.
    즉, 필요한 막대는 2진수로 나타낼 수 있다.
    예) 23 => 10111
 */
public class N1094 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int value = 1 << i;
            if ((x & value) == value) count += 1;
        }

        System.out.println(count);
    }
}
