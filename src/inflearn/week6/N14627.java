package inflearn.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 파닭파닭
// https://www.acmicpc.net/problem/14627
public class N14627 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int pa = input[0];
        int chicken = input[1];
        int[] pas = new int[pa];
        int max = 0;
        long sum = 0;
        for (int i = 0; i < pa; i++) {
            pas[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, pas[i]);
            sum += pas[i];
        }

        long lo = 0;
        long hi = 1_000_000_001L;

        while (lo + 1 < hi) {
            long mid = (lo + hi) >>> 1;

            if (check(mid, chicken, pas)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        System.out.println(sum - lo * chicken);
    }

    private static boolean check(long pa, int chicken, int[] pas) {

        int count = 0;
        for (int p : pas) {
            count += p / pa;
        }

        return count >= chicken;
    }
}
