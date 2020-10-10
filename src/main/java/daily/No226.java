package daily;

import com.sun.xml.internal.org.jvnet.mimepull.CleanUpExecutorFactory;
import pojo.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class No226 {
    public TreeNode invertTree1(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        if (root == null) {
            return root;
        }
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.removeFirst();
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
                if (node.left != null) {
                    deque.addLast(node.left);
                }
                if (node.right != null) {
                    deque.addLast(node.right);
                }
            }
        }
        return root;
    }

    /**
     * 递归
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
