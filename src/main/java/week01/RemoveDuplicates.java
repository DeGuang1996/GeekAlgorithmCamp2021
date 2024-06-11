package week01;

public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int pre = 0, cur = 1;
        while (cur < nums.length) {
            if (nums[pre] != nums[cur]) {
                pre++;
                nums[pre] = nums[cur];
            }
            cur++;
        }
        return pre + 1;
    }
}
