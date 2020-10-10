package daily;

import pojo.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class No94 {
    /**
     * 递归
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(result, root);
        return result;
    }

    private void inorder(List<Integer> result, TreeNode root) {
        if (root != null) {
            inorder(result, root.left);
            result.add(root.val);
            inorder(result, root.right);
        }
    }

    /**
     * 迭代
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> stk = new LinkedList<TreeNode>();
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    /**
     * Morris
     * 如果 xxx 无左孩子，先将 xxx 的值加入答案数组，再访问 xxx 的右孩子，即 x=x.rightx = x.\textit{right}x=x.right。
     * 如果 xxx 有左孩子，则找到 xxx 左子树上最右的节点（即左子树中序遍历的最后一个节点，xxx 在中序遍历中的前驱节点），我们记为 predecessor\textit{predecessor}predecessor。根据 predecessor\textit{predecessor}predecessor 的右孩子是否为空，进行如下操作。
     * 如果 predecessor\textit{predecessor}predecessor 的右孩子为空，则将其右孩子指向 xxx，然后访问 xxx 的左孩子，即 x=x.leftx = x.\textit{left}x=x.left。
     * 如果 predecessor\textit{predecessor}predecessor 的右孩子不为空，则此时其右孩子指向 xxx，说明我们已经遍历完 xxx 的左子树，我们将 predecessor\textit{predecessor}predecessor 的右孩子置空，将 xxx 的值加入答案数组，然后访问 xxx 的右孩子，即 x=x.rightx = x.\textit{right}x=x.right。
     * 重复上述操作，直至访问完整棵树。
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode predecessor = null;

        while (root != null) {
            if (root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明左子树已经访问完了，我们需要断开链接
                else {
                    res.add(root.val);
                    predecessor.right = null;
                    root = root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }
}
