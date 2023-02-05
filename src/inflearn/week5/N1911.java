package inflearn.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 흙길 보수하기
// https://www.acmicpc.net/problem/1911
public class N1911 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = input[0];
        int l = input[1];
        List<Puddle> puddles = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            puddles.add(new Puddle(input[0], input[1]));
        }

        puddles.sort(Comparator.comparing(Puddle::getStart).thenComparing(Puddle::getEnd));

        int cover = 0;
        int answer = 0;
        for (Puddle puddle : puddles) {
            if (puddle.start > cover) {
                int len = puddle.end - puddle.start;
                cover = puddle.start;
                while (len > 0) {
                    answer += 1;
                    len -= l;
                    cover += l;
                }
            } else if (cover < puddle.end) {
                int len = puddle.end - cover;
                while (len > 0) {
                    answer += 1;
                    len -= l;
                    cover += l;
                }
            } else {
                continue;
            }
        }

        System.out.println(answer);
    }

    static class Puddle {
        int start;
        int end;

        public Puddle(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }
}
