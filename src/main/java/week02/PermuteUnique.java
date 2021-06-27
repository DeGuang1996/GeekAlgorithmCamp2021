package week02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermuteUnique {

    private boolean[] used;
    private List<List<Integer>> res;
    private List<Integer> temp;

    public List<List<Integer>> permuteUnique(int[] nums) {
        used = new boolean[nums.length];
        res = new ArrayList<>();
        temp = new ArrayList<>();
        Arrays.sort(nums);
        calPermuteUnique(nums, 0);
        return res;
    }

    private void calPermuteUnique(int[] nums, int index) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < used.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue;
            }
            temp.add(nums[i]);
            used[i] = true;
            calPermuteUnique(nums, index + 1);
            used[i] = false;
            temp.remove(index);
        }
    }
}
