package week04;

public class FindMin {

    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            }
            // 是否有重复元素
            // } else {
            //     right--;
            // }
        }
        return nums[left];
    }
}
