package daily;

import utils.PrintUtils;

public class No31 {
    public void nextPermutation1(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public void nextPermutation2(int[] nums) {
        int length = nums.length;
        int little = -1;
        for (int i = length - 1; i >= 1; i--) {
            if (nums[i] > nums[i - 1]) {
                little = i - 1;
                break;
            }
        }
        if (little != -1) {
            for (int i = length - 1; i > little; i--) {
                if (nums[i] > nums[little]) {
                    swap(nums, little, i);
                    break;
                }
            }
        }
        reverse(nums, little + 1, length - 1);
    }

    private void swap(int[] nums, int little, int large) {
        int temp = nums[little];
        nums[little] = nums[large];
        nums[large] = temp;
    }

    private void reverse(int[] nums, int little, int large) {
        while (little < large) {
            swap(nums, little++, large--);
        }
    }

    public static void main(String[] args) {
        No31 no31 = new No31();
        int[] input = {2, 3, 1};
        no31.nextPermutation1(input);
        PrintUtils.print(input);
    }
}
