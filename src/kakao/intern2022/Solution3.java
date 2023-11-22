package kakao.intern2022;

// https://school.programmers.co.kr/learn/courses/30/lessons/118668
// DP
public class Solution3 {
    public static void main(String[] args) {
        Solution3 s = new Solution3();
        int solution = s.solution(0, 0, new int[][]{{0, 0, 30, 2, 1},{150, 150, 30, 30, 100}});
        System.out.println(solution);
    }

    private static final int INF = 123456789;

    private static int[][] dp = new int[152][152];

    public int solution(int alp, int cop, int[][] problems) {
        // 주어진 능력이 더 크다면 maxAlp, maxCop가 alp, cop일 것임
        int maxAlp = alp;
        int maxCop = cop;
        for (int[] problem : problems) {
            int reqAlp = problem[0];
            int reqCop = problem[1];
            maxAlp = Math.max(reqAlp, maxAlp);
            maxCop = Math.max(reqCop, maxCop);
        }

        // 무한대로 초기화
        for (int i = 0; i <= 150; i++) {
            for (int j = 0; j <= 150; j++) {
                if (i <= alp && j <= cop) continue;
                dp[i][j] = INF;
            }
        }

        for (int i = alp; i <= 150 ; i++) {
            for (int j = cop; j <= 150; j++) {
                dp[i + 1][j] = Math.min(dp[i][j] + 1, dp[i + 1][j]);
                dp[i][j + 1] = Math.min(dp[i][j] + 1, dp[i][j + 1]);
                for (int[] problem : problems) {
                    int reqAlp = problem[0];
                    int reqCop = problem[1];
                    int alpRwd = problem[2];
                    int copRwd = problem[3];
                    int cost = problem[4];

                    if (i < reqAlp) continue;
                    if (j < reqCop) continue;


                    int totalAlp = i + alpRwd;
                    int totalCop = j + copRwd;
                    if (totalAlp > maxAlp) {
                        totalAlp = maxAlp;
                    }
                    if (totalCop > maxCop) {
                        totalCop = maxCop;
                    }

                    dp[totalAlp][totalCop] = Math.min(dp[i][j] + cost, dp[totalAlp][totalCop]);
                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}
