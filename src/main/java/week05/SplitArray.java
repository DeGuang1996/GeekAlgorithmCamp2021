package week05;

public class SplitArray {

    public int splitArray(int[] nums, int m) {
        int left = 0, right = 0;
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isValid(nums, m , mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    private boolean isValid(int[] nums, int m, int total) {
        int groupSum = 0;
        int groupCount = 1;
        for (int num : nums) {
            if (groupSum + num <= total) {
                groupSum += num;
            } else {
                groupCount++;
                groupSum = num;
            }
        }
        return groupCount <= m;
    }
}
