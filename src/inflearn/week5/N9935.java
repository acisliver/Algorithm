package inflearn.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 문자열 폭발
// https://www.acmicpc.net/problem/9935
public class N9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] string = br.readLine().toCharArray();
        String bomb = br.readLine();
        StringBuilder sb = new StringBuilder();

        for (char c : string) {
            sb.append(c);
            if (sb.length() < bomb.length()) continue;
            if (sb.substring(sb.length() - bomb.length(), sb.length()).equals(bomb)) {
                sb.delete(sb.length() - bomb.length(), sb.length());
            }
        }

        if(sb.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(sb);
        }
    }
}
