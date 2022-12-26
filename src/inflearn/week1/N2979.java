package inflearn.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 트럭 주차
// https://www.acmicpc.net/problem/2979
public class N2979 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] fees = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] count = new int[101];
        int fee = 0;
        for (int i = 0; i < 3; i++) {
            String[] time = br.readLine().split(" ");
            int start = Integer.parseInt(time[0]);
            int end = Integer.parseInt(time[1]);

            for (int j = start; j < end; j++) {
                count[j] += 1;
            }
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] == 1) {
                fee += fees[0];
            } else if (count[i] == 2) {
                fee += fees[1] * 2;
            } else if (count[i] == 3) {
                fee += fees[2] * 3;
            }
        }

        System.out.println(fee);
    }
}
