package week04;

public class Search {

    public int search(int[] nums, int target) {
        // 双端包含二分
        // int left = 0, right = nums.length - 1;
        // while (left <= right) {
        //     int mid = left + (right - left) / 2;
        //     if (nums[mid] == target) {
        //         return mid;
        //     } else if (nums[mid] > target) {
        //         right = mid - 1;
        //     } else {
        //         left = mid + 1;
        //     }
        // }
        // return -1;

        // 单端包含二分
        // int left = 0, right = nums.length;
        // while (left < right) {
        //     int mid = left + (right - left) / 2;
        //     if (nums[mid] >= target) {
        //         right = mid;
        //     } else {
        //         left = mid + 1;
        //     }
        // }
        // if (right < nums.length && nums[right] == target) {
        //     return right;
        // }
        // return -1;

        // 单端包含二分
        int left = -1, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        if (right >= 0 && nums[right] == target) {
            return right;
        }
        return -1;
    }
}
