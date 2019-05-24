package oneToHundred;

/**
 * @date : 2019/02/27 11:16
 * @author: liangenmao
 */
public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] h = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result = result2(h);
        System.out.println(result);
    }

    public static int result1(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int volume = Math.min(height[i], height[j]) * (j - i);
                if (volume > max) {
                    max = volume;
                }
            }
        }
        return max;
    }

    public static int result2(int[] height) {
        int max = 0;
        for (int i = 0, j = height.length - 1; i < j; ) {
            int volume = Math.min(height[i], height[j]) * (j - i);
            if (volume > max) {
                max = volume;
            }
            if (height[i] > height[j]) {
                j--;
            } else {
                i++;
            }
        }
        return max;
    }
}
