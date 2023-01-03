package inflearn.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

// 빈도 정렬
// https://www.acmicpc.net/problem/2910
public class N2910 {

    static int N, C;
    static int[] MESSAGE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nc = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = nc[0];
        C = nc[1];

        MESSAGE = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Map<Integer, Integer> frequencies = new HashMap<>();
        Map<Integer, Integer> firstAppearance = new HashMap<>();
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < MESSAGE.length; i++) {
            int m = MESSAGE[i];
            if (!frequencies.containsKey(m)) {
                frequencies.put(m, 1);
                firstAppearance.put(m, i);
            } else {
                frequencies.put(m, frequencies.get(m) + 1);
            }
        }

        List<Integer> keys =  frequencies.entrySet().stream()
                .sorted((e1, e2) -> {
                    int compare = Integer.compare(e2.getValue(), e1.getValue());
                    if (compare == 0) {
                        return Integer.compare(firstAppearance.get(e1.getKey()), firstAppearance.get(e2.getKey()));
                    }
                    return compare;
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        for (Integer key : keys) {
            int frequency = frequencies.get(key);
            for (int i = 0; i < frequency; i++) {
                answer.append(key).append(" ");
            }
        }

        System.out.println(answer);
    }
}
