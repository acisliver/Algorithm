package leetcode;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
// DP
public class MaxProfit {
    public static void main(String[] args) {
        System.out.println(new MaxProfit().maxProfit(new int[] {7,1,5,3,6,4}));
    }

    public int maxProfit(int[] price) {
        int[] dp = new int[price.length];
        int answer = 0;

        dp[0] = price[0];

        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 1], price[i]);
            answer = Math.max(answer, price[i] - dp[i - 1]);
        }

        return answer;
    }
}
