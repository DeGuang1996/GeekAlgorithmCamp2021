package week06;

public class CanJump {

    public boolean canJump(int[] nums) {

        // dp 解法
        // boolean[] dp = new boolean[nums.length];
        // dp[0] = true;
        // for (int i = 0; i < nums.length; i++) {
        //     if (!dp[i]) {
        //         return false;
        //     }
        //     for (int j = i + nums[i]; j >= i; j--) {
        //         if (j >= nums.length - 1) {
        //             return true;
        //         }
        //         if (dp[j]) {
        //             break;
        //         }
        //         dp[j] = true;
        //     }
        // }
        // return dp[nums.length - 1];

        // 贪心解法
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > ans) {
                return false;
            }
            ans = Math.max(ans, i + nums[i]);
            if (ans >= nums.length - 1) {
                return true;
            }
        }
        return true;
    }
}
