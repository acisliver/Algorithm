package leetcode;

// Palindromic Substrings
public class N647 {
    public int countSubstrings(String s) {
        int answer = 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            answer += 1;
        }

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                answer += 1;
            }

        }

        for(int len = 3; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                if (s.charAt(i) == s.charAt(i + len - 1) && dp[i + 1][i + len - 2]) {
                    dp[i][i + len - 1] = true;
                    answer += 1;
                }
            }
        }

        return answer;
    }
}
