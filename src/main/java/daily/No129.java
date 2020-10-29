package daily;

import pojo.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class No129 {
    public int sumNumbers1(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int prevSum) {
        if (root == null) {
            return 0;
        }
        int sum = prevSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }

    public int sumNumbers2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        Queue<Integer> numQueue = new LinkedList<Integer>();
        nodeQueue.offer(root);
        numQueue.offer(root.val);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int num = numQueue.poll();
            TreeNode left = node.left, right = node.right;
            if (left == null && right == null) {
                sum += num;
            } else {
                if (left != null) {
                    nodeQueue.offer(left);
                    numQueue.offer(num * 10 + left.val);
                }
                if (right != null) {
                    nodeQueue.offer(right);
                    numQueue.offer(num * 10 + right.val);
                }
            }
        }
        return sum;
    }

//    int result = 0;
//
//    public int sumNumbers3(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//        dfs(root, 0);
//        return result;
//    }
//
//    private void dfs(TreeNode root, int sum) {
//        sum = sum * 10 + root.val;
//        if (root.left == null && root.right == null) {
//            result += sum;
//            return;
//        }
//        if (root.left != null) {
//            dfs(root.left, sum);
//        }
//        if (root.right != null) {
//            dfs(root.right, sum);
//        }
//    }

    public static void main(String[] args) {
        No129 no129 = new No129();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        int result = no129.sumNumbers2(root);
        System.out.println(result);
    }
}
