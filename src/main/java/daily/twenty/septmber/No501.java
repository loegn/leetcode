package daily.twenty.septmber;

import pojo.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class No501 {
    int base, count, maxCount;
    List<Integer> answer = new ArrayList<Integer>();

    public int[] findMode(TreeNode root) {
        TreeNode cur = root, pre = null;
        while (cur != null) {
            if (cur.left == null) {
                update(cur.val);
                cur = cur.right;
                continue;
            }
            pre = cur.left;
            while (pre.right != null && pre.right != cur) {
                pre = pre.right;
            }
            if (pre.right == null) {
                pre.right = cur;
                cur = cur.left;
            } else {
                pre.right = null;
                update(cur.val);
                cur = cur.right;
            }
        }
        int[] mode = new int[answer.size()];
        for (int i = 0; i < answer.size(); ++i) {
            mode[i] = answer.get(i);
        }
        return mode;
    }

    public void update(int x) {
        if (x == base) {
            ++count;
        } else {
            count = 1;
            base = x;
        }
        if (count == maxCount) {
            answer.add(base);
        }
        if (count > maxCount) {
            maxCount = count;
            answer.clear();
            answer.add(base);
        }
    }


    List<Integer> nums = new ArrayList<>();
//    int maxCount;
    int curr, currCount;

    public int[] findMode2(TreeNode root) {
        dfs(root);
        int[] result = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            result[i] = nums.get(i);
        }
        return result;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        int value = root.val;
        if (curr == value) {
            currCount++;
        } else {
            if (currCount > maxCount) {
                nums.clear();
                maxCount = currCount;
            } else if (currCount == maxCount) {
                nums.add(curr);
            } else {

            }
            currCount = 0;
            curr = value;
        }
        dfs(root.right);
    }
}
