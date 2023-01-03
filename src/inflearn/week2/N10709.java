package inflearn.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 기상캐스터
// https://www.acmicpc.net/problem/10709
public class N10709 {

    static int H, W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        H = input[0];
        W = input[1];

        int[][] cloudPredictions = new int[H][W];
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < H; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < row.length; j++) {
                String cloud = row[j];
                if (cloud.equals("c")) {
                    cloudPredictions[i][j] = 0;
                } else {
                    if (j == 0) {
                        cloudPredictions[i][j] = -1;
                    } else {
                        if (cloudPredictions[i][j - 1] == -1) {
                            cloudPredictions[i][j] = -1;
                        } else {
                            cloudPredictions[i][j] = cloudPredictions[i][j - 1] + 1;
                        }
                    }
                }
            }
        }

        for (int[] cloudPrediction : cloudPredictions) {
            for (int i : cloudPrediction) {
                result.append(i).append(" ");
            }
            result.append("\n");
        }

        System.out.println(result);
    }
}
