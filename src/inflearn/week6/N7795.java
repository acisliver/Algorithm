package inflearn.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 먹을 것인가 먹힐 것인가
// https://www.acmicpc.net/problem/7795
public class N7795 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int[] a = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int[] b = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int count = getCount(a, b);
            System.out.println(count);
        }
    }

    private static int getCount(int[] a, int[] b) {

        int count = 0;

        Arrays.sort(b);

        for (int i = 0; i < a.length; i++) {
            int fishA = a[i];
            int canEat = lowerBound(fishA, b);
            if (canEat >= 0) {
                count += canEat + 1;
            }
        }

        return count;
    }

    private static int lowerBound(int key, int[] arr) {
        int lo = -1;
        int hi = arr.length;

        while (lo + 1 < hi) {
            int mid = (lo + hi) >>> 1;
            int midVal = arr[mid];

            if (key <= midVal) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        return lo;
    }
}
