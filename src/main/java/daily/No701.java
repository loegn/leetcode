package daily;

import pojo.TreeNode;

/**
 * https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/solution/er-cha-sou-suo-shu-zhong-de-cha-ru-cao-zuo-by-le-3/
 */
public class No701 {
    public TreeNode insertIntoBST1(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode pos = root;
        while (pos != null) {
            if (val < pos.val) {
                if (pos.left == null) {
                    pos.left = new TreeNode(val);
                    break;
                } else {
                    pos = pos.left;
                }
            } else {
                if (pos.right == null) {
                    pos.right = new TreeNode(val);
                    break;
                } else {
                    pos = pos.right;
                }
            }
        }
        return root;
    }

    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        dfs(root, val);
        return root;
    }

    private void dfs(TreeNode root, int val) {
        if (val > root.val) {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                dfs(root.right, val);
            }
        }
        if (val < root.val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                dfs(root.left, val);
            }
        }
    }
}
