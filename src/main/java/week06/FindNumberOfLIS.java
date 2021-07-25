package week06;

import java.util.Arrays;

public class FindNumberOfLIS {

    public int findNumberOfLIS(int[] nums) {
        int[] length = new int[nums.length];
        int[] count = new int[nums.length];
        Arrays.fill(length, 1);
        Arrays.fill(count, 1);
        int maxLen = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (length[j] + 1 > length[i]) {
                        length[i] = length[j] + 1;
                        count[i] = count[j];
                    } else if (length[j] + 1 == length[i]) {
                        count[i] += count[j];
                    }
                }
            }
            maxLen = Math.max(maxLen, length[i]);
        }
        int ans = 0;
        for (int i = 0; i < length.length; i++) {
            if (length[i] == maxLen) {
                ans += count[i];
            }
        }
        return ans;
    }
}
