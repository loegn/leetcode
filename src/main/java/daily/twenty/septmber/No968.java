package daily.twenty.septmber;

import com.sun.org.apache.bcel.internal.generic.ATHROW;
import pojo.TreeNode;

public class No968 {
    public int minCameraCover1(TreeNode root) {
        int[] array = dfs(root);
        return array[1];
    }

    public int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{Integer.MAX_VALUE / 2, 0, 0};
        }
        int[] leftArray = dfs(root.left);
        int[] rightArray = dfs(root.right);
        int[] array = new int[3];
        array[0] = leftArray[2] + rightArray[2] + 1;
        array[1] = Math.min(array[0], Math.min(leftArray[0] + rightArray[1], rightArray[0] + leftArray[1]));
        array[2] = Math.min(array[0], leftArray[1] + rightArray[1]);
        return array;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode curr = root.right = new TreeNode(0);
        curr = curr.right = new TreeNode(0);
        curr.left = new TreeNode(0);
        curr.right = new TreeNode(0);
        No968 no968 = new No968();
        int result = no968.minCameraCover1(root);
        System.out.println(result);
    }
}
