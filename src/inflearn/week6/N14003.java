package inflearn.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 가장 긴 증가하는 부분 수열 5
// https://www.acmicpc.net/problem/14003
public class N14003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        List<Integer> lis = new ArrayList<>();
        int[] prev = new int[n];

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (lis.isEmpty()) {
                lis.add(num);
                continue;
            }
            int insertionPoint = Collections.binarySearch(lis, num);
            if (insertionPoint < 0) {
                insertionPoint = ~insertionPoint;
                if (insertionPoint >= lis.size()) {
                    lis.add(num);
                    prev[i] = lis.size() - 1;
                } else {
                    lis.set(insertionPoint, num);
                    prev[i] = insertionPoint;
                }
            } else {
                prev[i] = insertionPoint;
            }
        }

        Stack<Integer> stack = new Stack<>();
        int index = lis.size() - 1;
        for (int i = prev.length - 1; i >= 0; i--) {
            if (prev[i] == index) {
                stack.push(arr[i]);
                index -= 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(lis.size());
        System.out.println(sb);
    }
}
