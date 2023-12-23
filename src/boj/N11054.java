package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 가장 긴 바이토닉 수열
// https://www.acmicpc.net/problem/11054
public class N11054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] ascLen = new int[N + 1];
        int[] descLen = new int[N + 1];

        List<Integer> ascLis = new ArrayList<>();
        List<Integer> descLis = new ArrayList<>();

        ascLis.add(0);
        descLis.add(0);

        // LIS 구하기
        for (int i = 0; i < N; i++) {
            int cur = arr[i];

            // 1. 현재 숫자가 lis에서 어느 위치인지 확인
            int insertionPoint = Collections.binarySearch(ascLis, cur);

            if (insertionPoint < 0) {   // lis에 cur가 없는 경우
                insertionPoint = ~insertionPoint;
                if (insertionPoint >= ascLis.size()) {  // cur가 lis 마지막에 이어붙일 수 있는 경우
                    ascLis.add(cur);
                    ascLen[i + 1] = ascLis.size() - 1;
                } else {    // cur가 lis 중간에 들어가는 경우
                    ascLis.set(insertionPoint, cur);
                    ascLen[i + 1] = insertionPoint;
                }
            } else {    // 이미 같은 숫자가 들어간 경우
                ascLen[i + 1] = insertionPoint;
            }
        }

        // 역순 LIS 구하기
        for (int i = N - 1; i >= 0; i--) {
            int cur = arr[i];

            // 1. 현재 숫자가 lis에서 어느 위치인지 확인
            int insertionPoint = Collections.binarySearch(descLis, cur);

            if (insertionPoint < 0) {   // lis에 cur가 없는 경우
                insertionPoint = ~insertionPoint;
                if (insertionPoint >= descLis.size()) {  // cur가 lis 마지막에 이어붙일 수 있는 경우
                    descLis.add(cur);
                    descLen[i + 1] = descLis.size() - 1;
                } else {    // cur가 lis 중간에 들어가는 경우
                    descLis.set(insertionPoint, cur);
                    descLen[i + 1] = insertionPoint;
                }
            } else {    // 이미 같은 숫자가 들어간 경우
                descLen[i + 1] = insertionPoint;
            }
        }

        // 구한 LIS와 역순 LIS를 더한 뒤 최대값을 찾기
        // 특정 숫자까지의 LIS와 역순 LIS의 합이 최대가 가장 긴 바이토닉수열이 되기 때문
        int max = 0;
        for (int i = 0; i < descLen.length; i++) {
            int d = descLen[i];
            int a = ascLen[i];
            // 둘을 더하고 1을 빼주는 이유는 현재 숫자까지의 바이토닉수열에서 현재 숫자가 중복으로 계산되었기 때문
            max = Math.max(max, d + a - 1);
        }

        System.out.println(max);
    }
}
