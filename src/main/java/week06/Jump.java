package week06;

import java.util.Arrays;

public class Jump {

    public int jump(int[] nums) {
        // 贪心解法
        // int ans = 0;
        // int end = 0;
        // int max = 0;
        // for (int i = 0; i < nums.length - 1; i++) {
        //     max = Math.max(max, i + nums[i]);
        //     if (end == i) {
        //         end = max;
        //         ans++;
        //     }
        // }
        // return ans;

        // dp解法
        if (nums.length == 1) {
            return 0;
        }

        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + nums[i]; j >= i; j--) {
                if (j >= nums.length - 1) {
                    return dp[i] + 1;
                }
                if (dp[i] != Integer.MAX_VALUE) {
                    break;
                }
                dp[i] = Math.min(dp[i], dp[j] + 1);
            }
        }
        return dp[nums.length - 1];
    }
}
