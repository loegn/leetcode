package daily;

import pojo.TreeNode;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 */
public class No103 {
    /**
     * 官方解答
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;

        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<Integer>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<Integer>(levelList));
            isOrderLeft = !isOrderLeft;
        }

        return ans;
    }

    /**
     * 我的解答
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        if (root == null) {
            return Collections.EMPTY_LIST;
        }
        List<List<Integer>> result = new ArrayList<>();
        //从右向左
        boolean l2r = false;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        List<Integer> rootList = new ArrayList<>();
        rootList.add(root.val);
        result.add(rootList);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> currList = new ArrayList<>(deque.size());
            for (int i = 0; i < size; i++) {
                if (l2r) {
                    TreeNode curr = deque.removeLast();
                    TreeNode left = curr.left;
                    if (left != null) {
                        currList.add(left.val);
                        deque.addFirst(left);
                    }
                    TreeNode right = curr.right;
                    if (right != null) {
                        currList.add(right.val);
                        deque.addFirst(right);
                    }
                } else {
                    TreeNode curr = deque.removeFirst();
                    TreeNode right = curr.right;
                    if (right != null) {
                        currList.add(right.val);
                        deque.addLast(right);
                    }
                    TreeNode left = curr.left;
                    if (left != null) {
                        currList.add(left.val);
                        deque.addLast(left);
                    }
                }
            }
            l2r = !l2r;
            if (currList.size() > 0) {
                result.add(currList);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        No103 no103 = new No103();

    }
}
