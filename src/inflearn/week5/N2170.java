package inflearn.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// 선 긋기
// https://www.acmicpc.net/problem/2170
public class N2170 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Line> lines = new PriorityQueue<>(
                Comparator
                        .comparing(Line::getStart)
                        .thenComparing(Comparator.comparing(Line::getEnd).reversed())
        );

        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Line line = new Line(input[0], input[1]);
            lines.offer(line);
        }

        int len = 0;
        Line curLine = lines.poll();

        while (!lines.isEmpty()) {
            Line line = lines.poll();
            int start = line.getStart();
            int end = line.getEnd();

            if (curLine.end >= start) {
                curLine.end = Math.max(end, curLine.end);
            } else {
                len += curLine.end - curLine.start;
                curLine = line;
            }
        }

        len += curLine.end - curLine.start;

        System.out.println(len);
    }

    static class Line {
        int start;
        int end;

        Line(int start, int end) {
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
