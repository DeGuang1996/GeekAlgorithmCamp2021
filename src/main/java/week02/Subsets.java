package week02;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    private List<List<Integer>> res;
    private List<Integer> temp;

    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        temp = new ArrayList<>();
        calSubsets(nums, 0);
        return res;
    }

    private void calSubsets(int[] nums, int index) {
        if (index == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        temp.add(nums[index]);
        calSubsets(nums, index + 1);
        temp.remove(Integer.valueOf(nums[index]));
        calSubsets(nums, index + 1);
    }
}
