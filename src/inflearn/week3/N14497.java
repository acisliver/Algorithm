package inflearn.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 주난의 난(難)
//https://www.acmicpc.net/problem/14497
public class N14497 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int r = input[0];
        int c = input[1];
        char[][] classRoom = new char[r][c];
        input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] joonan = new int[]{input[0] - 1, input[1] - 1};
        int[] choco = new int[]{input[2] - 1, input[2] - 1};

        for (int i = 0; i < r; i++) {
            classRoom[i] = br.readLine().toCharArray();
        }
    }
}
