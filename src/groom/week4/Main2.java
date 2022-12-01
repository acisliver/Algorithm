package groom.week4;

import java.io.*;
import java.util.Arrays;

class Main2 {

    static int count = 0;
    static int notYet = 0;
    static int[] DI = {1, -1, 0, 0};
    static int[] DJ = {0, 0, 1 , -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] park = new int[N][N];

        for (int i = 0; i < N; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < N; j++) {
                park[i][j] = input[j];
                if (input[j] > 0) notYet += input[j];
            }
        }


        while (notYet != 0) {
            count += 1;
            park = search(park);
//            System.out.println(Arrays.deepToString(park));
        }

        System.out.println(count);
    }

    private static int[][] search(int[][] park) {

        int[][] newPark = new int[park.length][park.length];

        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park.length; j++) {
                if (park[i][j] == 0) continue;

                newPark[i][j] = park[i][j];

                for (int k = 0; k < 4; k++) {
                    int nextI = i + DI[k];
                    int nextJ = j + DJ[k];

                    if (nextI < 0 || nextJ < 0 || nextI >= park.length || nextJ >= park.length) continue;

                    if (park[nextI][nextJ] == 0) {
                        if (newPark[i][j] > 0) {
                            newPark[i][j] -= 1;
                            notYet -= 1;
                        }
                    }
                }
            }
        }

        return newPark;
    }
}
