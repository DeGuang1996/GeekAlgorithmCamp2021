package week04;

public class SearchRange {

    public int[] searchRange(int[] nums, int target) {
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
        //     int begin = right, end = right;
        //     while (begin >= 0 && nums[begin] == target) {
        //         begin--;
        //     }
        //     while (end < nums.length && nums[end] == target) {
        //         end++;
        //     }
        //     return new int[] {begin + 1, end - 1};
        // }
        // return new int[] {-1,-1};

        int begin = -1;
        int end = -1;
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (r + l) / 2;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (r < nums.length && nums[r] == target) {
            begin = r;
        }

        l = -1;
        r = nums.length - 1;
        while (l < r) {
            int mid = (r + l + 1) / 2;
            if (nums[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        if (l >= 0 && nums[l] == target) {
            end = l;
        }

        return new int[] {begin, end};
    }

    public static void main(String[] args) {
        SearchRange searchRange = new SearchRange();
        searchRange.searchRange(new int[]{5,7,7,8,8,10}, 0);
    }

}
