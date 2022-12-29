package inflearn.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 나는야 포켓몬 마스터 이다솜
// https://www.acmicpc.net/problem/1620
public class N1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = nm[0];
        int m = nm[1];
        Map<String, String> encyclopedia = new HashMap<>();
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String number = String.valueOf(i + 1);
            String name = br.readLine();
            encyclopedia.put(number, name);
            encyclopedia.put(name, number);
        }

        for (int i = 0; i < m; i++) {
            String question = br.readLine();
            answer.append(encyclopedia.get(question)).append("\n");
        }

        System.out.println(answer);
    }
}
