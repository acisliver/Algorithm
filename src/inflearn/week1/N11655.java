package inflearn.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ROT13
// https://www.acmicpc.net/problem/11655
public class N11655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        StringBuilder crypto = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (Character.isUpperCase(letter)) {
                if (letter + 13 > 'Z') {
                    crypto.append((char) (letter - 13));
                } else {
                    crypto.append((char) (letter + 13));
                }

            } else if (Character.isLowerCase(letter)) {
                if (letter + 13 > 'z') {
                    crypto.append((char) (letter - 13));
                } else {
                    crypto.append((char) (letter + 13));
                }
            } else {
                crypto.append(letter);
            }
        }

        System.out.println(crypto);
    }
}
