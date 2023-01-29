package kakao.blind2021;

// https://school.programmers.co.kr/learn/courses/30/lessons/72413
// Floyd-Warshall
public class Solution4 {
    public static void main(String[] args) {
        System.out.println(new Solution4().solution(6, 4, 6, 2,
                new int[][]{
                        {4, 1, 10},
                        {3, 5, 24},
                        {5, 6, 2},
                        {3, 1, 41},
                        {5, 1, 24},
                        {4, 6, 50},
                        {2, 4, 66},
                        {2, 3, 22},
                        {1, 6, 25}
                }));
    }

    public static int INF = 123456789;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = INF;

        int[][] distance = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) distance[i][j] = 0;
                else distance[i][j] = INF;
            }     
        }

        for (int[] fare : fares) {
            int n1 = fare[0] - 1;
            int n2 = fare[1] - 1;
            int weight = fare[2];
            
            distance[n1][n2] = weight;
            distance[n2][n1] = weight;
        }

        // k번 노드를 거쳐 가는 경우
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    distance[i][j] = Math.min(
                            distance[i][j],
                            distance[i][k] + distance[k][j]
                    );
                }
            }
        }

        // 같이 어디까지 타고갈지 정하기
        for (int i = 0; i < n; i++) {
            int temp = 0;

            // s -> i 까지 같이 타고감
            if (i != s - 1) temp += distance[s - 1][i];

            // i -> a, b로 각자 집으로 감
            temp += distance[i][a - 1];
            temp += distance[i][b - 1];

            answer = Math.min(answer, temp);
        }

        return answer;
    }

//    private class Node implements Comparable<Node>{
//
//        public int num;
//        public int weight;
//
//        Node(int num, int weight) {
//            this.num = num;
//            this.weight = weight;
//        }
//
//        @Override
//        public int compareTo(Node node) {
//            return this.weight - node.weight;
//        }
//    }
}
