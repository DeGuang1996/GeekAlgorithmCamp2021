package week04;

import week03.TreeNode;

public class InorderSuccessor {

    // private TreeNode ans = new TreeNode(Integer.MAX_VALUE);

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode cur = root;
        TreeNode ans = null;
        while (cur != null) {
            if (cur.val <= p.val) {
                cur = cur.right;
            } else {
                ans = cur;
                cur = cur.left;
            }
        }
        return ans;


        // if (root == null) {
        //     return null;
        // } else if (root.val > p.val && root.val < ans.val) {
        //     ans = root;
        // }
        // inorderSuccessor(root.left, p);
        // inorderSuccessor(root.right, p);
        // if (ans.val == Integer.MAX_VALUE) {
        //     return null;
        // }
        // return ans;
    }
}
