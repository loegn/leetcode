package oneThousandAndOneHundredToTwoHundred;

import org.junit.jupiter.api.Test;
import pojo.TreeNode;
import utils.PrintUtils;

import java.util.*;

import utils.ParamUtils;

/**
 * @date : 2019/07/08 15:03:28
 * @author: liangenmao
 */
public class DelNodes1110 {
    public List<TreeNode> result1(TreeNode root, int[] to_delete) {
        if (root == null) {
            return Collections.EMPTY_LIST;
        }
        List<TreeNode> result = new ArrayList<>();
        Set<Integer> needDelete = new HashSet<>();
        for (int i : to_delete) {
            needDelete.add(i);
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                helper(queue.poll(), null, false, needDelete, queue, result);
            }
        }
        return result;
    }

    private void helper(TreeNode treeNode, TreeNode father, boolean right, Set<Integer> needDelete, Queue<TreeNode> queue, List<TreeNode> result) {
        if (treeNode == null) {
            return;
        }
        if (needDelete.contains(treeNode.val)) {
            if (treeNode.left != null) {
                queue.offer(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.offer(treeNode.right);
            }
            if (father != null) {
                if (right) {
                    father.right = null;
                } else {
                    father.left = null;
                }
            }
        } else {
            if (father == null) {
                result.add(treeNode);
            }
            helper(treeNode.left, treeNode, false, needDelete, queue, result);
            helper(treeNode.right, treeNode, true, needDelete, queue, result);
        }
    }

    public List<TreeNode> result2(TreeNode root, int[] to_delete) {
        Set<Integer> del = new HashSet<>();
        for (int i : to_delete) {
            del.add(i);
        }
        List<TreeNode> res = new ArrayList<>();
//        dfs(del, root, 1,res);
        return res;
    }

//    void dfs(unordered_set<int>&del, TreeNode*&root, int flag,List<TreeNode> res) {
//        if (!root) return;
//        int found = del.count(root -> val);
//        dfs(del, root -> left, found);
//        dfs(del, root -> right, found);
//        if (!found && flag) res.push_back(root);
//        if (found) root = nullptr;
//    }

    @Test
    public void delNodes() {
        String ints = "";
        TreeNode root = null;
        int[] to_delete = ParamUtils.getInt(ints);
        Object result = result1(root, to_delete);
        PrintUtils.print(result);
    }
}
