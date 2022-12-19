package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 가장 긴 증가하는 부분 수열 5
// https://www.acmicpc.net/problem/14003
public class N14003 {

    static int N;
    static int[] ARRAY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        ARRAY = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        List<Integer> lis = new ArrayList<>();
        List<Integer> orders = new ArrayList<>();
        lis.add(0);

        for (int i : ARRAY) {
            if (lis.get(lis.size() - 1) < i) {
                lis.add(i);
                orders.add(lis.size() - 1);
            } else {
                int index = Collections.binarySearch(lis, i);

                // 삽입 포인트에 추가
                if (index < 0) {
                    int insertionPoint = index * -1 - 1;
                    lis.set(insertionPoint, i);
                    orders.add(insertionPoint);
                } else {
                    orders.add(index);
                }
            }

        }

        StringBuilder sb = new StringBuilder();
        int lisSize = lis.size() - 1;
        sb.append(lisSize).append("\n");

        Stack<Integer> stack = new Stack<>();
        for (int i = orders.size() - 1; i >= 0; i--) {
            if (orders.get(i) == lisSize) {
                lisSize -= 1;
                stack.push(ARRAY[i]);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}
