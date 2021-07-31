package week07;

import java.util.Arrays;

public class MergeStones {

    public int mergeStones(int[] stones, int k) {
        int MOD = 1 << 20;
        int[] preSum = new int[stones.length + 1];
        for (int i = 0; i < stones.length; i++) {
            preSum[i + 1] = preSum[i] + stones[i];
        }

        int[][][] dp = new int[stones.length][stones.length][k + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], MOD);
            }
        }
        for (int i = 0; i < stones.length; i++) {
            dp[i][i][1] = 0;
        }
        for (int len = 2; len <= stones.length; len++) {
            for (int left = 0; left <= stones.length - len; left++) {
                int right = left + len - 1;
                for (int i = 2; i <= k; i++) {
                    for (int p = left; p < right; p++) {
                        // for (int j = 1; j <= i - 1; j++) {
                        //     dp[left][right][i] = Math.min(dp[left][right][i], dp[left][p][j] + dp[p + 1][right][i - j]);
                        // }
                        dp[left][right][i] = Math.min(dp[left][right][i], dp[left][p][1] + dp[p + 1][right][i - 1]);
                    }
                }
                dp[left][right][1] = Math.min(dp[left][right][1], dp[left][right][k] + preSum[right + 1] - preSum[left]);
            }
        }
        if (dp[0][stones.length - 1][1] >= MOD) {
            return -1;
        }
        return dp[0][stones.length - 1][1];
    }
}
