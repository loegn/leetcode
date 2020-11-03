package daily;

public class No941 {
    public boolean validMountainArray1(int[] A) {
        int N = A.length;
        int i = 0;

        // 递增扫描
        while (i + 1 < N && A[i] < A[i + 1]) {
            i++;
        }

        // 最高点不能是数组的第一个位置或最后一个位置
        if (i == 0 || i == N - 1) {
            return false;
        }

        // 递减扫描
        while (i + 1 < N && A[i] > A[i + 1]) {
            i++;
        }

        return i == N - 1;
    }

    public boolean validMountainArray2(int[] A) {
        if (A.length < 3 || A[1] < A[0]) {
            return false;

        }
        for (int i = 2; i < A.length; i++) {
            if (A[i] < A[i - 1]) {
                for (int j = i; j < A.length; j++) {
                    if (A[j] >= A[j - 1]) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        No941 no941 = new No941();
        int[] input = {1, 3, 2};
        no941.validMountainArray1(input);
    }
}
