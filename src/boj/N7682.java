package boj;

import java.util.Scanner;

// https://www.acmicpc.net/problem/7682
// 틱택토
// 구현 - dfs
public class N7682 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {
            String testCase = sc.nextLine();

            if (testCase.equals("end")) break;

            System.out.println(isLast(testCase));
        }
    }

    public static String isLast(String str) {

        char[][] map = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                map[i][j] = str.charAt(i + j);
            }
        }

        return dfs(map, 0) ? "valid" : "invalid";
    }

    public static boolean dfs(char[][] map, int visited) {

        if (visited == (1 << 9) - 1) {
            return true;
        }

        for (int k = 0; k < 9; k++) {
            if ((visited &= (1 << k)) == 1) continue;

            if (isWin(map, visited)) return false;
        }

        return false;
    }

    public static boolean isWin(char[][] map, int visited) {

        for (int k = 0; k < map.length; k++) {
            if ((visited &= (1 << k)) == 0) continue;


        }
        return true;
    }
}
