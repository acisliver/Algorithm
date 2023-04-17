package inflearn.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 드래곤 커브
// https://www.acmicpc.net/problem/15685
public class N15685 {

    static int N;
    static boolean[][] MAP = new boolean[104][104];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int[] curve = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            draw(curve);
        }

        int answer = countSquare();
        System.out.println(answer);
    }

    private static void draw(int[] curve) {

        List<int[]> lines = new ArrayList<>();
        lines.add(new int[]{curve[0], curve[1], curve[2]});

        int[] dX = {1, 0, -1, 0};
        int[] dY = {0, -1, 0, 1};

        int x = curve[0];
        int y = curve[1];
        int d = curve[2];
        int g = curve[3];

        int endX = x + dX[d];
        int endY = y + dY[d];

        MAP[y][x] = true;
        MAP[endY][endX] = true;

        while (g-- > 0) {
            int size = lines.size() - 1;
            for (int i = size; i >= 0; i--) {
                int[] line = lines.get(i);

                int curD = line[2];
                int nD = getNextDegree(curD);
                int[] newLine = {endX, endY, nD};

                endX = endX + dX[nD];
                endY = endY + dY[nD];

                if (endX < 0 || endY < 0 || endX > 100 || endY > 100) continue;

                lines.add(newLine);
                MAP[endY][endX] = true;
            }
        }
    }

    private static int getNextDegree(int currentDegree) {
        return (currentDegree + 1) % 4;
    }

    private static int countSquare() {
        int squareCount = 0;

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (MAP[i][j]) {
                    if (MAP[i + 1][j] && MAP[i + 1][j + 1] && MAP[i][j + 1]) {
                        squareCount += 1;
                    }
                }
            }
        }

        return squareCount;
    }
}
