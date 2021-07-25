package week06;

public class CanPartition {

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum >> 1;
        int[] dp = new int[target + 1];
        for (int i = 1; i <= nums.length; i++) {
            for (int j = target; j >= nums[i - 1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i - 1]] + nums[i - 1]);
            }
        }
        return dp[target] == target;
    }
}
