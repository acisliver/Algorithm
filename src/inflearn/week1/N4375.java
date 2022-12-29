package inflearn.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1
// https://www.acmicpc.net/problem/4375
public class N4375 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while ((input = br.readLine()) != null) {
            int n = Integer.parseInt(input);
            int target = 1;
            int digit = 1;
            while (true) {
                if (target % n == 0) {
                    System.out.println(digit);
                    break;
                } else {
                    target = (target * 10 + 1) % n;
                    digit += 1;
                }
            }
        }
    }
}
