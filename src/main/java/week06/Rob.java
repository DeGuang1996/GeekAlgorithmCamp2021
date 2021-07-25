package week06;

import java.util.Arrays;

public class Rob {

    public int rob1(int[] nums) {
        int[] dp = new int[nums.length + 1];
        dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[nums.length];
    }

    public int rob2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] numsWithStart = new int[nums.length - 1];
        int[] numsWithEnd = new int[nums.length - 1];
        System.arraycopy(nums, 0, numsWithStart, 0, numsWithStart.length);
        System.arraycopy(nums, 1, numsWithEnd, 0, numsWithEnd.length);
        return Math.max(rob1(numsWithStart), rob1(numsWithEnd));
    }
}
