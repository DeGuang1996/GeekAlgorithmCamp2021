package week02;

import java.util.ArrayList;
import java.util.List;

public class Combine {

    private List<List<Integer>> res;
    private List<Integer> temp;

    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>();
        temp = new ArrayList<>();
        calCombine(n, k, 1);
        return res;
    }

    private void calCombine(int n, int k, int index) {
        if (temp.size() > k || temp.size() + n - index + 1 < k) {
            return;
        }
        if (index == n + 1) {
            if (temp.size() == k) {
                res.add(new ArrayList<>(temp));
            }
            return;
        }
        temp.add(index);
        calCombine(n, k, index + 1);
        temp.remove(Integer.valueOf(index));
        calCombine(n, k ,index + 1);
    }
}
