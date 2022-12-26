package inflearn.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 팰린드롬인지 확인하기
// https://www.acmicpc.net/problem/10988
public class N10988 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        int left = 0;
        int right = word.length() - 1;
        int answer = 1;

        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                answer = 0;
                break;
            }
            left += 1;
            right -= 1;
        }

        System.out.println(answer);
    }
}
