package week02;

public class NextPermutation {

    public void nextPermutation(int[] nums) {
        int left = nums.length - 2;
        while (left >= 0 && nums[left] >= nums[left + 1]) {
            left--;
        }
        if (left >= 0) {
            int right = nums.length - 1;
            while (right > left && nums[right] <= nums[left]) {
                right--;
            }
            swap(nums, left, right);
        }
        reverse(nums, left + 1);
    }

    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] + nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] = nums[i] - nums[j];
    }

    private void reverse(int[] nums, int begin) {
        int end = nums.length - 1;
        while (end > begin) {
            swap(nums, begin, end);
            begin++;
            end--;
        }
    }
}
