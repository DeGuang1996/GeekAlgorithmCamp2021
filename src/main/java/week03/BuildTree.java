package week03;

public class BuildTree {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return doBuildTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode doBuildTree(int[] inorder, int[] postorder, int inBegin, int inEnd, int postBegin, int postEnd) {
        if (inBegin > inEnd || postBegin > postEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postEnd]);
        int value = postorder[postEnd];
        int mid = inBegin;
        while (mid <= inEnd && inorder[mid] != value) {
            mid++;
        }
        root.left = doBuildTree(inorder, postorder, inBegin, mid - 1, postBegin, postBegin + mid - inBegin - 1);
        root.right = doBuildTree(inorder, postorder, mid + 1, inEnd, postBegin + mid - inBegin, postEnd - 1);
        return root;
    }
}
