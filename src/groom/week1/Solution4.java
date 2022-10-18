package groom.week1;

import java.io.*;
import java.util.Arrays;

public class Solution4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();

        int answer = 0;

        for (int i = 1; i < input.length; i++) {
            int n = input[i];

            if (isPrime(i + 1) < 3) answer += n;
        }

        System.out.println(answer);
    }

    private static int isPrime(int num) {
        int count = 0;

        for(int i = 1; i <= num; i++)
        {
            if(num % i == 0)
                count += 1;
            if(count >= 3)
                return count;
        }

        return count;
    }
}
