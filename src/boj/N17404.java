package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// RGB거리 2
// https://www.acmicpc.net/problem/17404
public class N17404 {

    static int N;
    static int[][] COSTS, DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        COSTS = new int[N][3];
        for (int i = 0; i < N; i++) {
            COSTS[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        DP = new int[N][3];

        int minCost = IntStream.of(
                        paintStartWith(Color.RED),
                        paintStartWith(Color.GREEN),
                        paintStartWith(Color.BLUE)
                )
                .min()
                .getAsInt();

        System.out.println(minCost);
    }

    // 첫 번째 집의 색을 정하고 집 칠하기를 시작한다
    private static int paintStartWith(Color startColor) {

        initializeDP(startColor);

        for (int house = 1; house < N; house++) {   // house번째 집의 색을 칠할 것임

            if (house == N - 1) {
                paintLastHouse(startColor);
                break;
            }

            for (Color color : Color.values()) {
                Color.paint(house, color);
            }
        }

        return Arrays.stream(DP[N - 1])
                .filter(cost -> cost > 0)
                .min()
                .getAsInt();
    }

    // 첫 번째 집 외의 집은 무한대로 초기화 한다
    private static void initializeDP(Color startColor) {
        Arrays.fill(DP[0], 1001);   // 최대 비용 1000보다 높은 숫자로 초기화
        DP[0][startColor.color] = COSTS[0][startColor.color];
    }

    public static void paintLastHouse(Color startColor) {

        List<Color> usableColor = Arrays.stream(Color.values())
                .filter(color -> color != startColor)   // 처음 칠한 색은 사용하지 못한다.
                .collect(Collectors.toList());

        for (Color color : usableColor) {
            Color.paint(N - 1, color);
        }
    }

    enum Color {

        RED(0), GREEN(1), BLUE(2);

        private final int color;

        Color(int color) {
            this.color = color;
        }

        public static void paint(int house, Color color) {

            List<Color> otherColor = Arrays.stream(Color.values())
                    .filter(c -> c != color)
                    .collect(Collectors.toList());

            DP[house][color.color] =
                    Math.min(
                            DP[house - 1][otherColor.get(0).color],
                            DP[house - 1][otherColor.get(1).color]
                    ) + COSTS[house][color.color];
        }
    }
}
