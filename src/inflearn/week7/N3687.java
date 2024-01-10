package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 성냥개비
// https://www.acmicpc.net/problem/3687
public class N3687 {

    static final int[] COUNTS = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
    static int[] MIN = new int[101];
    static int[] MAX = new int[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(getMin(n));
        }
    }

    static int getMin(int n) {

        MIN[2] = 1;
        MIN[3] = 7;
        MIN[4] = 4;
        MIN[5] = 2;
        MIN[6] = 6;
        MIN[7] = 8;
        MIN[8] = 16;
        MIN[9] = 19;

        for (int i = 10; i <= n; i++) {
            int count = i;
            count -= 2;
            int zero = count / 6;
            int rest = count % 6;
            MIN[i] = MIN[rest] + 10 * (zero + 1);
        }

        return MIN[n];
    }
}
