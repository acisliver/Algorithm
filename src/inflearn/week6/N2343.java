package inflearn.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// https://www.acmicpc.net/problem/2343
// 기타 레슨
public class N2343 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = input[0];
        int m = input[1];
        List<Integer> lectures = Arrays.stream(br.readLine().split(" "))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        int totalLectureTime = lectures.stream()
                .reduce(Integer::sum)
                .get();
        int lo = 0;
        int hi = totalLectureTime + 1;

        while (lo + 1 < hi) {
            int mid = (lo + hi) >>> 1;

            if (check(mid, lectures, m)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        System.out.println(hi);
    }

    private static boolean check(int n, List<Integer> lectures, int m) {
        for (Integer lecture : lectures) {
            if (lecture > n) {
                return false;
            }
        }

        int blueray = n;
        int count = 0;
        for (Integer lecture : lectures) {
            if (blueray - lecture < 0) {
                blueray = n;
                count += 1;
            }
            blueray -= lecture;
        }

        if (blueray != n) {
            count += 1;
        }

        return count <= m;
    }
}
