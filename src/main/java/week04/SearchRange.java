package week04;

public class SearchRange {

    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (right < nums.length && nums[right] == target) {
            int begin = right, end = right;
            while (begin >= 0 && nums[begin] == target) {
                begin--;
            }
            while (end < nums.length && nums[end] == target) {
                end++;
            }
            return new int[] {begin + 1, end - 1};
        }
        return new int[] {-1,-1};
    }
}
