package inflearn.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 용돈 관리
// https://www.acmicpc.net/problem/6236
public class N6236 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = input[0];
        int m = input[1];
        int[] expenditures = new int[n];
        int sumExpenditure = 0;
        for (int i = 0; i < n; i++) {
            expenditures[i] = Integer.parseInt(br.readLine());
            sumExpenditure += expenditures[i];
        }

        int lo = 0;
        int hi = sumExpenditure + 1;

        while (lo + 1 < hi) {
            int mid = (lo + hi) >>> 1;

            if (check(mid, m, expenditures)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        System.out.println(hi);
    }

    private static boolean check(int withdrawMoney, int m , int[] expenditures) {
        int count = 1;
        int money = withdrawMoney;

        for (int expenditure : expenditures) {
            if (expenditure <= money) {
                money -= expenditure;
            } else {
                money = withdrawMoney;
                count += 1;
                if (expenditure > money) {
                    return false;
                } else {
                    money -= expenditure;
                }
            }
        }

        return count <= m;
    }
}
