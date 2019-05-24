package oneToHundred;

import org.junit.jupiter.api.Test;

/**
 * @date : 2019/03/25 17:28
 * @author: liangenmao
 */
public class RemoveElement {
    public int result1(int[] nums, int val) {
        int curr = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val){
                nums[curr] = nums[i];
                curr ++;
            }
        }
        return curr;
    }

    public int result2(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1];
                // reduce array size by one
                n--;
            } else {
                i++;
            }
        }
        return n;
    }

    @Test
    public void removeElement() {
        int[] ints = new int[]{
                3,2,2,3
        };
        int val = 3;
        Object result = result1(ints,val);
        System.out.println(result);
    }
}
