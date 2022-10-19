package groom.week2;

import java.io.*;
import java.util.Arrays;

public class Main1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] scores = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::valueOf)
                    .toArray();

            double average = Arrays.stream(scores)
                    .reduce(Integer::sum)
                    .getAsInt() / (double) n;

            int pass = 0;

            for (int score : scores) {
                if (score >= average) {
                    pass += 1;
                }
            }

            System.out.printf("%d/%d\n", pass, n);
        }
    }
}
