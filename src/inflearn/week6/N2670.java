package inflearn.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 연속부분최대곱
// https://www.acmicpc.net/problem/2670
public class N2670 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        double[] arr = new double[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Double.parseDouble(br.readLine());
        }
        double b = arr[0];
        double max = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i] * b) b = arr[i];
            else b *= arr[i];
            max = Math.max(b, max);
        }

        System.out.printf("%.3f", max + 0.00001);
    }
}
