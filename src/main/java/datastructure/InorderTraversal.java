package datastructure;

import org.junit.jupiter.api.Test;
import pojo.TreeNode;
import utils.PrintUtils;

import java.util.*;

/**
 * @date : 2019/05/30 16:58:14
 * @author: liangenmao
 */
public class InorderTraversal {
    /**
     * 递归
     */
    public List<Integer> result1(TreeNode root) {
        if (root == null) {
            return Collections.EMPTY_LIST;
        }
        List<Integer> result = new ArrayList<>();
        result.addAll(result1(root.left));
        result.add(root.val);
        result.addAll(result1(root.right));
        return result;
    }

    /**
     * 将节点左子树全部压栈
     * 弹出栈顶所有没有右子树的元素并将其加入遍历结果，直到出现右子树
     * 若当前元素有右子树，则将当前元素加入遍历结果
     * 指向当前节点右子树
     * 若当前节点为空，则结束遍历；若当前节点不为空，则继续循环上述操作
     */
    public List<Integer> result2(TreeNode root) {
        Stack<TreeNode> treeNodeStack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        TreeNode node = root;
        while (node != null) {
            while (node != null) {
                treeNodeStack.push(node);
                node = node.left;
            }
            while (treeNodeStack.size() > 0 && (node = treeNodeStack.pop()).right == null) {
                result.add(node.val);
            }
            // node不可能为null
            if (node.right != null) {
                result.add(node.val);
            }
            node = node.right;
        }
        return result;
    }

    /**
     * 将节点左子树全部压栈
     * 如果栈不为空则弹出栈顶元素并将其加入遍历结果，指向当前节点右子树
     * 若当前节点和栈均为空，则结束遍历；否则继续循环上述操作
     */
    public List<Integer> result3(TreeNode root) {
        Stack<TreeNode> treeNodeStack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        TreeNode node = root;
        while (node != null || !treeNodeStack.isEmpty()) {
            while (node != null) {
                treeNodeStack.push(node);
                node = node.left;
            }
            if (!treeNodeStack.isEmpty()) {
                node = treeNodeStack.pop();
                result.add(node.val);
                node = node.right;
            }
        }
        return result;
    }

    @Test
    public void inorderTraversal() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        root.right = node2;
        node2.left = node3;
        Object result = result1(root);
        PrintUtils.print(result);
    }
}
