package inflearn.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

// 효율적인 해킹
// https://www.acmicpc.net/problem/1325
public class N1325 {

    static int N, M, MAX_HACKED_COMPUTER;
    static List<Integer> HACKED_START_COMPUTERS;
    static List<List<Integer>> COMPUTERS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        M = input[1];
        MAX_HACKED_COMPUTER = 0;
        HACKED_START_COMPUTERS = new LinkedList<>();
        COMPUTERS = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            COMPUTERS.add(new LinkedList<>());
        }

        for (int i = 0; i < M; i++) {
            input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int slave = input[0] - 1;
            int master = input[1] - 1;
            COMPUTERS.get(master).add(slave);
        }

        for (int computer = 0; computer < N; computer++) {
            int hacked = hack(computer, new boolean[N]);
            if (hacked > MAX_HACKED_COMPUTER) {
                HACKED_START_COMPUTERS.clear();
                MAX_HACKED_COMPUTER = hacked;
                HACKED_START_COMPUTERS.add(computer + 1);
            } else if (hacked == MAX_HACKED_COMPUTER) {
                HACKED_START_COMPUTERS.add(computer + 1);
            }
        }

        Collections.sort(HACKED_START_COMPUTERS);

        System.out.println(HACKED_START_COMPUTERS.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static int hack(int master,  boolean[] visited) {
        visited[master] = true;
        int hacked = 1;

        for (Integer slave : COMPUTERS.get(master)) {
            if (visited[slave]) continue;
            hacked += hack(slave, visited);
        }

        return hacked;
    }


}
