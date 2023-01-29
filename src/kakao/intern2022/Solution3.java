package kakao.intern2022;

// https://school.programmers.co.kr/learn/courses/30/lessons/118668
// DP
public class Solution3 {
    public static void main(String[] args) {
        Solution3 s = new Solution3();
        System.out.println(s.solution(10, 10, new int[][]{{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}}));
    }

    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0;
        int maxCop = 0;

        int[][] dp = new int[151][151];

        // 무한대로 초기화
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                int cost = 0;
                if (alp < i) cost += i - alp;
                if (cop < j) cost += j - cop;
                dp[i][j] = cost;
            }
        }

        for (int[] problem : problems) {
            int reqAlp = problem[0];
            int reqCop = problem[1];
            int alpRwd = problem[2];
            int copRwd = problem[3];
            int cost = problem[4];

            maxAlp = Math.max(maxAlp, reqAlp);
            maxCop = Math.max(maxCop, reqCop);

            for (int i = reqAlp; i < dp.length - alpRwd; i++) {
                for (int j = reqCop; j < dp[0].length - copRwd; j++) {
                    dp[i + alpRwd][j + copRwd] = Math.min(
                            Math.min(dp[i + alpRwd][j + copRwd], cost + dp[i][j]),
                            Math.min(dp[i + alpRwd - 1][j + copRwd] + 1, dp[i + alpRwd][j + copRwd - 1] + 1)
                    );
                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}
