package groom.week1;

import java.io.*;

public class Solution2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        String name = input[1];

        int answer = 0;
        for (int i = 0; i < n; i++) {
            String friend = br.readLine();
            if (friend.contains(name)) answer++;
        }

        System.out.println(answer);
    }
}
