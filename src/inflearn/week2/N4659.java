package inflearn.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

// 비밀번호 발음하기
// https://www.acmicpc.net/problem/4659
public class N4659 {

    static List<Character> GATHERS = List.of('a', 'e', 'i', 'o', 'u');

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String password;

        while (!(password = br.readLine()).equals("end")) {
            if (checkPassword(password)) {
                System.out.printf("<%s> is acceptable.\n", password);
            } else {
                System.out.printf("<%s> is not acceptable.\n", password);
            }
        }
    }

    private static boolean checkPassword(String password) {
        if (!containGather(password)) return false;
        if (isContinuous(password)) return false;
        if (isContinuousWithSameChar(password)) return false;

        return true;
    }

    private static boolean containGather(String password) {

        for (int i = 0; i < password.length(); i++) {
            if (GATHERS.contains(password.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    private static boolean isContinuous(String password) {

        int gatherCount = 0;
        int consonantCount = 0;

        for (int i = 0; i < password.length(); i++) {
            if (GATHERS.contains(password.charAt(i))) {
                gatherCount += 1;
                consonantCount = 0;
            } else {
                consonantCount += 1;
                gatherCount = 0;
            }

            if (gatherCount > 2 || consonantCount > 2) {
                return true;
            }
        }

        return false;
    }

    private static boolean isContinuousWithSameChar(String password) {

        List<Character> exceptions = List.of('e', 'o');
        char prevChar = password.charAt(0);

        for (int i = 1; i < password.length(); i++) {
            if (prevChar == password.charAt(i)) {
                if (exceptions.contains(prevChar)) {
                    continue;
                }
                return true;
            }
            prevChar = password.charAt(i);
        }

        return false;
    }
}
