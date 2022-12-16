package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 줄 세우기
// https://www.acmicpc.net/problem/2252
public class N2252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        List<List<Integer>> graph = new ArrayList<>();  // 학생 간 앞뒤 관계 그래프로 표현
        List<Integer> indegree = new ArrayList<>();     // 각 학생의 진입차수

        for (int i = 0; i < N; i++) {
            graph.add(new LinkedList<>());
            indegree.add(0);
        }

        for (int i = 0; i < M; i++) {

            int[] numbers = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int previousNumber = numbers[0] - 1;
            int nextNumber = numbers[1] - 1;

            graph.get(previousNumber).add(nextNumber);
            indegree.set(nextNumber, indegree.get(nextNumber) + 1);
        }

        Queue<Integer> queue = new LinkedList<>();
        List<Integer> answer = new ArrayList<>();

        // 진입 차수가 0인 학생 큐에 넣기
        IntStream.range(0, indegree.size())
                .filter(index -> indegree.get(index) == 0)
                .forEach(queue::offer);

        // 위상 정렬
        while (!queue.isEmpty()) {
            Integer curNumber = queue.poll();
            answer.add(curNumber + 1);

            List<Integer> nextNumbers = graph.get(curNumber);

            for (Integer number : nextNumbers) {
                indegree.set(number, indegree.get(number) - 1);

                if (indegree.get(number) == 0) {
                    queue.offer(number);
                }
            }
        }

        System.out.println(answer.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

}
