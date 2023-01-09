package inflearn.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 치킨 배달
// https://www.acmicpc.net/problem/15686
public class N15686 {

    static int N, M, DISTANCE;
    static List<int[]> CHICKENS, HOUSES;
    static int[][] CITY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        M = input[1];
        CHICKENS = new ArrayList<>();
        HOUSES = new ArrayList<>();
        CITY = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                CITY[i][j] = Integer.parseInt(st.nextToken());
                if (CITY[i][j] == 2) {
                    CHICKENS.add(new int[]{i, j});
                } else if (CITY[i][j] == 1) {
                    HOUSES.add(new int[]{i, j});
                }
            }
        }

        DISTANCE = Integer.MAX_VALUE;

        solve(0, M, new ArrayList<>(M));

        System.out.println(DISTANCE);
    }

    private static void solve(int index, int count, List<int[]> chosenChickens) {
        if (count == chosenChickens.size()) {
            int distance = 0;
            for (int[] house : HOUSES) {
                distance += getDistance(house, chosenChickens);
            }

            DISTANCE = Math.min(DISTANCE, distance);
            return;
        }

        for (int i = index; i < CHICKENS.size(); i++) {
            chosenChickens.add(CHICKENS.get(i));
            solve(i + 1, count, chosenChickens);
            chosenChickens.remove(CHICKENS.get(i));
        }
    }

    private static int getDistance(int[] house, List<int[]> chickens) {
        int distance = Integer.MAX_VALUE;

        for (int[] chicken : chickens) {
            distance = Math.min(distance, getManhattanDistance(house, chicken));
        }

        return distance;
    }

    private static int getManhattanDistance(int[] house, int[] chicken) {
        return Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]);
    }


}
