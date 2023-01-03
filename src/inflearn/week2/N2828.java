package inflearn.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 사과 담기 게임
// https://www.acmicpc.net/problem/2828
public class N2828 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = input[0];
        int bucketSize = input[1];
        int appleCount = Integer.parseInt(br.readLine());

        int left = 1;
        int right = left + bucketSize - 1;
        int move = 0;
        for (int i = 0; i < appleCount; i++) {

            int appleLocation = Integer.parseInt(br.readLine());

            if (appleLocation >= left && appleLocation <= right) {
                continue;
            }

            if (appleLocation < left) {
                int offset = left - appleLocation;
                move += offset;
                left -= offset;
                right -= offset;
            } else {
                int offset = appleLocation - right;
                move += offset;
                left += offset;
                right += offset;
            }
        }

        System.out.println(move);
    }
}
