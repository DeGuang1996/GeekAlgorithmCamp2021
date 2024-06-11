package week01;

public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        int pre = 0, cur = 0;
        while (cur < nums.length) {
            if (nums[cur] != 0) {
                if (nums[pre] == 0) {
                    nums[pre] = nums[pre] + nums[cur];
                    nums[cur] = nums[pre] - nums[cur];
                    nums[pre] = nums[pre] - nums[cur];
                }
                pre++;
            }
            cur++;
        }
    }
}
