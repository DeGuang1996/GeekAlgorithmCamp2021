package week01;

public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        // 移动左侧
        int lastZeroIdx = (int) 1e9;
        for (int i = 0; i < nums.length ; i++) {
            if (nums[i] == 0) {
                lastZeroIdx = Math.min(lastZeroIdx, i);
            } else {
                if (lastZeroIdx < nums.length) {
                    swap(nums, i, lastZeroIdx);
                    lastZeroIdx++;
                }
            }
        }

        // 移动右侧
        // int lastZeroIdx = -1;
        // for (int i = nums.length - 1; i >= 0; i--) {
        //     if (nums[i] == 0) {
        //         lastZeroIdx = Math.max(lastZeroIdx, i);
        //     } else {
        //         if (lastZeroIdx >= 0) {
        //             swap(nums, i, lastZeroIdx);
        //             lastZeroIdx--;
        //         }
        //     }
        // }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
