package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 축구
// https://www.acmicpc.net/problem/1344
public class N1344 {

    private static final double[][][] DP = new double[20][20][20];
    private static final boolean[] isPrime = new boolean[20];

    private static double A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = Double.parseDouble(br.readLine()) / 100;
        B = Double.parseDouble(br.readLine()) / 100;
        fillIsPrime();
        fillDP();
        System.out.println(go(0, 0, 0));
    }

    private static void fillIsPrime() {
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i < isPrime.length; i++) {
            for (int j = i + i; j < isPrime.length; j += i) {
                isPrime[j] = false;
            }
        }
    }

    private static void fillDP() {
        for (int i = 0; i < DP.length; i++) {
            for (int j = 0; j < DP.length; j++) {
                for (int k = 0; k < DP.length; k++) {
                        DP[i][j][k] = -1;
                }
            }
        }
    }

    private static double go(int idx, int x, int y) {
        if (idx == 18) {
            return isPrime[x] || isPrime[y] ? 1.0 : 0.0;
        }

        double result = DP[idx][x][y];
        if (result > -0.5) return result;   // 값이 있다면 DP return 부동소수점이라서 == 사용 불가

        result = 0.0;
        result += go(idx + 1, x + 1, y) * A * (1 - B);
        result += go(idx + 1, x, y + 1) * (1 - A) * B;
        result += go(idx + 1, x + 1, y + 1) * A * B;
        result += go(idx + 1, x, y) * (1 - A) * (1 - B);

        DP[idx][x][y] = result;
        return result;
    }
}
