package groom.week1;

import java.io.*;
import java.util.Arrays;

public class Solution3 {
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();

        permutation(input, new int[4], new boolean[4], 0);

        System.out.println(answer);
    }

    static void permutation(int[] arr, int[] output, boolean[] visited, int depth) {
        if (depth == 4) {
            answer = Math.max(
                    Math.abs(output[0] - output[1]) + Math.abs(output[2] - output[3]),
                    answer
            );
            return;
        }

        for (int i=0; i<arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = arr[i];
                permutation(arr, output, visited, depth + 1);
                output[depth] = 0; // 이 줄은 없어도 됨
                visited[i] = false;;
            }
        }
    }
}
