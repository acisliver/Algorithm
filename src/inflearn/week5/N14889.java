package inflearn.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 스타트와 링크
// https://www.acmicpc.net/problem/14889
public class N14889 {

    static int N, ANSWER;
    static int[][] ABILITY;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ABILITY = new int[N][N];
        ANSWER = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            ABILITY[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        search(0, new ArrayList<>());

        System.out.println(ANSWER);
    }

    private static void search(int here, List<Integer> team1) {
        if (here > N) return;

        if (team1.size() == N / 2) {
            List<Integer> team2 = IntStream.range(0, N)
                    .boxed()
                    .collect(Collectors.toList());
            team2.removeAll(team1);

            int ability1 = getAbility(team1);
            int ability2 = getAbility(team2);
            ANSWER = Math.min(ANSWER, Math.abs(ability1 - ability2));
            return;
        }

        search(here + 1, team1);

        team1.add(here);
        search(here + 1, team1);
        team1.remove(team1.size() - 1);
    }

    private static int getAbility(List<Integer> team) {
        int ability = 0;

        for (Integer t : team) {
            for (int i = 0; i < N; i++) {
                if (team.contains(i)) {
                    ability += ABILITY[t][i];
                }
            }
        }

        return ability;
    }
}
