package week07;

import week03.TreeNode;

import java.util.HashMap;

public class Rob {

    private HashMap<TreeNode, int[]> dp;

    public int rob(TreeNode root) {
        dp = new HashMap<>();
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dp.put(root, new int[]{0, root.val});
        if (root.left != null) {
            dp.get(root)[0] += dfs(root.left);
            dp.get(root)[1] += dp.get(root.left)[0];
        }
        if (root.right != null) {
            dp.get(root)[0] += dfs(root.right);
            dp.get(root)[1] += dp.get(root.right)[0];
        }
        return Math.max(dp.get(root)[0], dp.get(root)[1]);
    }
}
