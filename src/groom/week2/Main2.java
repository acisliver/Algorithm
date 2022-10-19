package groom.week2;

import java.io.*;

public class Main2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int answer = 1;

        char prev = str.charAt(0);
        for (int i = 1; i < n; i++) {
            char cur = str.charAt(i);
            if (prev != cur) answer++;
            prev = cur;
        }

        System.out.println(answer);
    }
}
