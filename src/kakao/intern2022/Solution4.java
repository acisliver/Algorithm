package kakao.intern2022;

import java.util.Arrays;
import java.util.LinkedList;

// https://school.programmers.co.kr/learn/courses/30/lessons/118669?language=java
public class Solution4 {
    public static void main(String[] args) {
        Solution4 s = new Solution4();
        System.out.println(Arrays.toString(s.solution(7,
                new int[][]{
                        {1, 2, 5}, {1, 4, 1}, {2, 3, 1}, {2, 6, 7}, {4, 5, 1}, {5, 6, 1}, {6, 7, 1}
                },
                new int[]{3, 7},
                new int[]{1, 5})));
    }

    static int MIN_INTENSITY = 10_000_001;
    static int SUMMIT = 50_001;
    static int[] SUMMITS;
    static int[] GATES;

    /**
     * @param n       지점 수
     * @param paths   등산로 정보
     * @param gates   출입구
     * @param summits 정상
     * @return intensity가 최소가 되는 등산코스
     */
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        SUMMITS = summits;
        GATES = gates;

        LinkedList<LinkedList<int[]>> graph = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<>());
        }

        for (int[] path : paths) {
            int p1 = path[0] - 1;
            int p2 = path[1] - 1;
            int w = path[2];

            graph.get(p1).add(new int[]{p2, w});
            graph.get(p2).add(new int[]{p1, w});
        }

        for (int gate : gates) {
            boolean[] visited = new boolean[n];
            visited[gate - 1] = true;
            search(gate - 1, visited, 0, graph);
        }

        return new int[]{SUMMIT + 1, MIN_INTENSITY};
    }

    private void search(int cur, boolean[] visited, int maxIntensity, LinkedList<LinkedList<int[]>> graph) {

        if (isSummit(cur)) {
            if (maxIntensity < MIN_INTENSITY) {
                MIN_INTENSITY = maxIntensity;
                SUMMIT = cur;
            } else if (maxIntensity == MIN_INTENSITY) {
                SUMMIT = Math.min(cur, SUMMIT);
            }
            return;
        }

        for (int[] node : graph.get(cur)) {
            int next = node[0];
            int intensity = node[1];

            if (visited[next]) continue;

            if (isGate(next)) continue;

            visited[next] = true;
            search(next, visited, Math.max(maxIntensity, intensity), graph);
            visited[next] = false;
        }

    }

    private boolean isSummit(int point) {
        for (int summit : SUMMITS) {
            if (point == summit - 1) return true;
        }

        return false;
    }

    private boolean isGate(int point) {
        for (int gate : GATES) {
            if (point == gate - 1) return true;
        }

        return false;
    }
}
