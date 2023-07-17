package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.System.in;

// 요세푸스 문제
// https://www.acmicpc.net/problem/1158
public class N1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int n = input[0];
        int k = input[1];

        List<Integer> numbers = IntStream.range(1, n + 1)
                .boxed()
                .toList();
        LinkedList<Integer> queue = new LinkedList<>(numbers);
        List<Integer> answer = new ArrayList<>();

        int index = 0;
        while (!queue.isEmpty()) {
            index += k - 1;
            while (index >= queue.size()) {
                index -= queue.size();
            }
            answer.add(queue.remove(index));
        }

        System.out.println(answer.stream().map(String::valueOf).collect(Collectors.joining(", ", "<", ">")));
    }
}
