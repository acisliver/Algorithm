package inflearn.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// 패션왕 신해빈
// https://www.acmicpc.net/problem/9375
public class N9375 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCount = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < testCount; i++) {
            int cases = 1;

            Map<String, Integer> clothes = new HashMap<>();
            int count = Integer.parseInt(br.readLine());

            for (int j = 0; j < count; j++) {
                String[] cloth = br.readLine().split(" ");
                clothes.put(cloth[1], clothes.getOrDefault(cloth[1], 0) + 1);
            }

            for (Integer value : clothes.values()) {
                cases *= value + 1;
            }

            sb.append(cases - 1).append("\n");
        }

        System.out.println(sb);
    }

}

