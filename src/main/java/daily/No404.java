package daily;

import pojo.TreeNode;

public class No404 {
    public int sumOfLeftLeaves1(TreeNode root) {
        return root != null ? dfs(root) : 0;
    }

    public int dfs(TreeNode node) {
        int ans = 0;
        if (node.left != null) {
            ans += isLeafNode(node.left) ? node.left.val : dfs(node.left);
        }
        if (node.right != null && !isLeafNode(node.right)) {
            ans += dfs(node.right);
        }
        return ans;
    }

    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

    private int sum = 0;

    public int sumOfLeftLeaves2(TreeNode root) {
        dfs(root, false);
        return sum;
    }

    private void dfs(TreeNode root, boolean left) {
        if (root == null) {
            return;
        }
        if (left && root.left == null && root.right == null) {
            sum += root.val;
        } else {
            dfs(root.left, true);
            dfs(root.right, false);
        }
    }
}
