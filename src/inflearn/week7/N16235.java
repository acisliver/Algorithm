package inflearn.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 나무 재테크
// https://www.acmicpc.net/problem/16235
public class N16235 {
    static int N, M, K;
    static int[][] NUTRIENTS;

    static Kan[][] BOARD;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        M = input[1];
        K = input[2];

        NUTRIENTS = new int[N][N];
        for (int i = 0; i < N; i++) {
            NUTRIENTS[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        BOARD = new Kan[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                BOARD[i][j] = new Kan();
            }
        }

        for (int i = 0; i < M; i++) {
            input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int r = input[0] - 1;
            int c = input[1] - 1;
            int age = input[2];
            Tree tree = new Tree(age);
            BOARD[r][c].liveTrees.add(tree);
        }

        while (K-- > 0) {
            spring();
            summer();
            fall();
            winter();
        }

        int liveTreesCount = getLiveTrees();
        System.out.println(liveTreesCount);
    }

    private static void spring() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                var curKan = BOARD[i][j];
                var liveTrees = curKan.liveTrees;
                var temp = new ArrayList<Tree>();
                while (!liveTrees.isEmpty()) {
                    Tree tree = liveTrees.poll();
                    if (curKan.nutrient >= tree.age) {
                        curKan.nutrient -= tree.age;
                        tree.age += 1;
                        temp.add(tree);
                    } else {
                        curKan.deadTrees.add(tree);
                    }
                }
                liveTrees.addAll(temp);
            }
        }
    }

    private static void summer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                var curKan = BOARD[i][j];
                var deadTrees = curKan.deadTrees;
                for (var deadTree : deadTrees) {
                    int nutrient = deadTree.age / 2;
                    curKan.nutrient += nutrient;
                }
                deadTrees.clear();
            }
        }
    }

    private static void fall() {
        final int[] dR = {-1, -1, -1, 0, 0, 1, 1, 1};
        final int[] dC = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                var curKan = BOARD[i][j];
                var liveTrees = curKan.liveTrees;
                int newTreeCount = 0;
                for (var liveTree : liveTrees) {
                    if (liveTree.age % 5 == 0) {
                        newTreeCount += 1;
                    }
                }

                if (newTreeCount == 0) continue;

                for (int k = 0; k < 8; k++) {
                    int nI = i + dR[k];
                    int nJ = j + dC[k];

                    if (nI < 0 || nJ < 0 || nI >= N || nJ >= N) continue;

                    for (int l = 0; l < newTreeCount; l++) {
                        BOARD[nI][nJ].liveTrees.add(new Tree(1));
                    }
                }
            }
        }
    }

    private static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                var curKan = BOARD[i][j];
                curKan.nutrient += NUTRIENTS[i][j];
            }
        }
    }

    private static int getLiveTrees() {
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                var curKan = BOARD[i][j];
                result += curKan.liveTrees.size();
            }
        }

        return result;
    }
    static class Kan {

        int nutrient = 5;
        PriorityQueue<Tree> liveTrees = new PriorityQueue<>();
        List<Tree> deadTrees = new ArrayList<>();
    }

    static class Tree implements Comparable<Tree> {
        int age;

        public Tree(int age) {
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return Integer.compare(this.age, o.age);
        }
    }

}
