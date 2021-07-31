package week07;

import week03.TreeNode;

import java.util.HashMap;

public class MaxPathSum {

    class NodePath {
        public TreeNode leftPath;
        public TreeNode rightPath;
        public int pathSumValue;
        public int leftPathValue;
        public int rightPathValue;
    }

    private int ans;
    private HashMap<TreeNode, NodePath> hashMap;

    public int maxPathSum(TreeNode root) {
        ans = Integer.MIN_VALUE;
        hashMap = new HashMap<>();
        dfs(root);

        TreeNode node = findPath(root);
        printPath(node, ans, true);
        System.out.println();
        return ans;
    }

    // dfs找答案
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);

        // 记录左右路径和
        NodePath nodePath = new NodePath();
        if (left > 0) {
            nodePath.leftPath = root.left;
            nodePath.leftPathValue = left;
        }
        if (right > 0) {
            nodePath.rightPath = root.right;
            nodePath.rightPathValue = right;
        }
        int cur = root.val + Math.max(0, left) + Math.max(0, right);
        nodePath.pathSumValue = cur;
        hashMap.put(root, nodePath);

        ans = Math.max(ans, cur);
        return Math.max(Math.max(left, right), 0) + root.val;
    }

    private TreeNode findPath(TreeNode root) {
        // 找和为结果的顶点
        if (root == null) {
            return null;
        }
        // 处理特殊case
        if (hashMap.get(root).pathSumValue == ans && !(root.val == 0 &&
                (hashMap.get(root).leftPathValue == ans || hashMap.get(root).rightPathValue == ans))) {
            return root;
        }
        TreeNode left = findPath(root.left);
        if (left != null) {
            return left;
        }
        return findPath(root.right);
    }

    private void printPath(TreeNode root, int pathSumValue, boolean inOrder) {
        // 打印路径
        if (root == null) {
            return;
        }
        if (hashMap.get(root).pathSumValue == pathSumValue) {
            printPath(hashMap.get(root).leftPath, hashMap.get(root).leftPathValue, true);
            System.out.println(root.val);
            printPath(hashMap.get(root).rightPath, hashMap.get(root).rightPathValue, false);
        } else if (hashMap.get(root).leftPathValue + root.val == pathSumValue) {
            if (inOrder) {
                printPath(hashMap.get(root).leftPath, hashMap.get(root).leftPathValue, true);
                System.out.println(root.val);
            } else {
                System.out.println(root.val);
                printPath(hashMap.get(root).leftPath, hashMap.get(root).leftPathValue, false);
            }
        } else if (hashMap.get(root).rightPathValue + root.val == pathSumValue) {
            System.out.println(root.val);
            printPath(hashMap.get(root).rightPath, hashMap.get(root).rightPathValue, inOrder);
        }
    }
}
