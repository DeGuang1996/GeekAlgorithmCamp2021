package week02;

import java.util.ArrayList;
import java.util.List;

public class Permute {

    private boolean[] used;
    private List<List<Integer>> res;
    private List<Integer> temp;

    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        res = new ArrayList<>();
        temp = new ArrayList<>();
        calPermute(nums, 0);
        return res;
    }

    private void calPermute(int[] nums, int index) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                temp.add(nums[i]);
                used[i] = true;
                calPermute(nums, index + 1);
                used[i] = false;
                temp.remove(index);
            }
        }
    }
}
