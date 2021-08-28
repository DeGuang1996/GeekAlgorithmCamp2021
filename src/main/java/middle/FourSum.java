package middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length == 0 || nums[nums.length - 1] < 0) {
            return ans;
        }
        for (int i = 0; i <= nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j <= nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int left = target - nums[i] - nums[j];
                // 从 j + 1 开始找两个数的和等于left

                int begin = j + 1, end = nums.length - 1;
                while (begin < end) {
                    if (nums[begin] + nums[end] == left) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]); temp.add(nums[j]); temp.add(nums[begin]); temp.add(nums[end]);
                        ans.add(temp);
                        begin++; end--;
                        while (begin < end && nums[begin] == nums[begin - 1]) {
                            begin++;
                        }
                        while (begin < end && nums[end] == nums[end + 1]) {
                            end--;
                        }
                    } else if (nums[begin] + nums[end] > left) {
                        end--;
                    } else {
                        begin++;
                    }
                }
            }
        }
        return ans;
    }
}
