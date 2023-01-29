package inflearn.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// 보석 도둑
// https://www.acmicpc.net/problem/1202
/*
    최대한 모든 가방에 보석을 넣는게 이득
    1. 보석을 무게 오른차순, 가치 내림차순으로 정렬
    2. 가방을 오름차순으로 정렬
    3. 가방을 하나씩 사용하며 넣을 수 있는 보석을 PQ에 담음
    4. PQ가 비지 않았다면 가방에 보석을 넣음
    5. 이전에 넣을 수 있었던 보석을 넣을 수도 있음
 */
public class N1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = input[0];
        int k = input[1];
        PriorityQueue<Jewelry> jewelries = new PriorityQueue<>((o1, o2) -> {
            if (o1.weight == o2.weight) {
                return o2.value - o1.value;
            }
            return o1.weight - o2.weight;
        });
        PriorityQueue<Integer> bags = new PriorityQueue<>();
        long totalValue = 0;

        for (int i = 0; i < n; i++) {
            input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Jewelry jewelry = new Jewelry(input[0], input[1]);
            jewelries.add(jewelry);
        }

        for (int i = 0; i < k; i++) {
            int bag = Integer.parseInt(br.readLine());
            bags.add(bag);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        while (!bags.isEmpty()) {
            int bag = bags.poll();
            // 넣을 수 있는 보석을 모두 pq에 넣는다.
            while (!jewelries.isEmpty() && jewelries.peek().weight <= bag) {
                Jewelry jewelry = jewelries.poll();
                pq.offer(jewelry.value);
            }

            // 1. 현재 가방에 들어갈 보석을 정함
            // 2. 만약 이번 반복에서 while에서 보석을 찾지 못했다면
            //    이전에 넣을 수 있었던 보석중 최대 가치인 보석을 넣음
            // 3. 이전에도 넣을 수 있는 보석을 찾지 못한 경우 보석을 넣지 못함
            if (!pq.isEmpty()) {
                totalValue += pq.poll();
            }
        }

        System.out.println(totalValue);
    }

    static class Jewelry {
        int weight;
        int value;

        public Jewelry(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
