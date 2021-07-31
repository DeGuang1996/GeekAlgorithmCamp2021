package week07;

import java.util.Arrays;

public class MaxCoins {

    public int maxCoins(int[] nums) {
        int[] newNums = new int[nums.length + 2];
        System.arraycopy(nums, 0, newNums, 1, nums.length);
        newNums[0] = 1;
        newNums[nums.length + 1] = 1;
        int[][] dp = new int[nums.length + 2][nums.length + 2];

        // for (int len = 1; len <= nums.length; len++) {
        //     for (int left = 1; left <= nums.length - len + 1; left++) {
        //         int right = left + len - 1;
        //         for (int i = left; i <= right; i++) {
        //             dp[left][right] = Math.max(dp[left][right], dp[left][i - 1] + dp[i + 1][right] + newNums[left - 1] * newNums[i] * newNums[right + 1]);
        //         }
        //     }
        // }
        return dfs(dp, 1, nums.length, newNums);
    }

    private int dfs(int[][] dp, int left, int right, int[] nums) {
        if (left > right) {
            return 0;
        }
        if (dp[left][right] >= 0) {
            return dp[left][right];
        }
        for (int i = left; i <= right; i++) {
            dp[left][right] = Math.max(dp[left][right], dfs(dp, left, i - 1, nums) + dfs(dp, i + 1, right, nums) +
                    nums[left - 1] * nums[i] * nums[right + 1]);
        }
        return dp[left][right];
    }
}
