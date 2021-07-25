package week06;

public class MaxProfit {

    public int maxProfit1(int[] prices) {
        // 贪心解法
        // int buy = -prices[0];
        // int ans = 0;
        // for (int i = 1; i < prices.length; i++) {
        //     ans = Math.max(ans, buy + prices[i]);
        //     buy = Math.max(buy, -prices[i]);
        // }
        // return ans;

        // 只能进行一笔交易，dp[0]代表买入最大收益，dp[1]代表卖出最大收益
        int[] dp = new int[2];
        dp[0] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[1] = Math.max(dp[1], prices[i] + dp[0]);
            dp[0] = Math.max(dp[0], -prices[i]);
        }
        return dp[1];
    }

    public int maxProfit2(int[] prices) {

        // 贪心解法
        // int ans = 0;
        // for (int i = 1; i < prices.length; i++) {
        //     if (prices[i - 1] < prices[i]) {
        //         ans += prices[i] - prices[i - 1];
        //     }
        // }
        // return ans;

        if (prices.length <= 1) {
            return 0;
        }
        // 最多进行prices.length / 2笔交易，dp[i][0]代表最多进行第i次买入交易的最大收益，dp[i][1]代表最多进行第i次卖出交易的最大收益
        // int[][] dp = new int[prices.length / 2][2];
        // for (int i = 0; i < dp.length; i++) {
        //     dp[i][0] = -prices[0];
        // }
        //
        // for (int i = 1; i < prices.length; i++) {
        //     dp[0][1] = Math.max(dp[0][1], prices[i] + dp[0][0]);
        //     dp[0][0] = Math.max(dp[0][0], -prices[i]);
        //     for (int j = 1; j < dp.length; j++) {
        //         dp[j][0] = Math.max(dp[j][0], dp[j - 1][1] - prices[i]);
        //         dp[j][1] = Math.max(dp[j][1], prices[i] + dp[j][0]);
        //     }
        // }
        // return dp[dp.length - 1][1];

        // dp[i][0]代表第i天持有股票的最大收益，dp[i][1]代表第i天不持有股票的最大收益
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
        }
        return dp[dp.length - 1][1];
    }

    public int maxProfit3(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        // 最多进行2笔交易，dp[i][0]代表最多进行第i次买入交易的最大收益，dp[i][1]代表最多进行第i次卖出交易的最大收益
        int[][] dp = new int[2][2];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            dp[0][1] = Math.max(dp[0][1], prices[i] + dp[0][0]);
            dp[0][0] = Math.max(dp[0][0], -prices[i]);
            dp[1][0] = Math.max(dp[1][0], dp[0][1] - prices[i]);
            dp[1][1] = Math.max(dp[1][1], prices[i] + dp[1][0]);
        }
        return dp[1][1];
    }

    public int maxProfit4(int k, int[] prices) {
        if (prices.length <= 1 || k == 0) {
            return 0;
        }
        // 最多进行k笔交易，dp[i][0]代表最多进行第i次买入交易的最大收益，dp[i][1]代表最多进行第i次卖出交易的最大收益
        int[][] dp = new int[k][2];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            dp[0][1] = Math.max(dp[0][1], prices[i] + dp[0][0]);
            dp[0][0] = Math.max(dp[0][0], -prices[i]);
            for (int j = 1; j < dp.length; j++) {
                dp[j][0] = Math.max(dp[j][0], dp[j - 1][1] - prices[i]);
                dp[j][1] = Math.max(dp[j][1], prices[i] + dp[j][0]);
            }
        }
        return dp[dp.length - 1][1];
    }

    public int maxProfitWithFee(int[] prices, int fee) {
        if (prices.length <= 1) {
            return 0;
        }
        // dp[i][0]代表第i天持有股票的最大收益，dp[i][1]代表第i天不持有股票的最大收益
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
        }
        return dp[dp.length - 1][1];
    }

    public int maxProfitWithCoolDown(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        // dp[i][0]代表第i天持有股票的最大收益，dp[i][1]代表第i天冷冻的最大收益，dp[i][2]代表第i天不持有股票的最大收益
        int[][] dp = new int[prices.length][3];
        dp[0][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][0] + prices[i]);
        }
        return Math.max(dp[dp.length - 1][1], dp[dp.length - 1][2]);
    }
}
