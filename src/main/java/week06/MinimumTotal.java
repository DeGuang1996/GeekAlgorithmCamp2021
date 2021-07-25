package week06;

import java.util.List;

public class MinimumTotal {

    public int minimumTotal(List<List<Integer>> triangle) {
        int[] ans = new int[triangle.size()];
        for (int i = 0; i < triangle.get(triangle.size() - 1).size(); i++) {
            ans[i] = triangle.get(triangle.size() - 1).get(i);
        }
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                ans[j] = triangle.get(i).get(j) + Math.min(ans[j], ans[j + 1]);
            }
        }
        return ans[0];
    }
}
