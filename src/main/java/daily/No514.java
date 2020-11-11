package daily;

import utils.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 定义 dp[i][j]\textit{dp}[i][j]dp[i][j] 表示从前往后拼写出 key\textit{key}key 的第 iii 个字符， ring\textit{ring}ring 的第 jjj 个字符与 12:0012:0012:00 方向对齐的最少步数（下标均从 000 开始）。
 * <p>
 * 显然，只有当字符串 ring\textit{ring}ring 的第 jjj 个字符需要和 key\textit{key}key 的第 iii 个字符相同时才能拼写出 key\textit{key}key 的第 iii 个字符，因此对于 key\textit{key}key 的第 iii 个字符，需要考虑计算的 ring\textit{ring}ring 的第 jjj 个字符只有 key[i]\textit{key}[i]key[i] 在 ring\textit{ring}ring 中出现的下标集合。我们对每个字符维护一个位置数组 pos[i]\textit{pos}[i]pos[i]，表示字符 iii 在 ring\textit{ring}ring 中出现的位置集合，用来加速计算转移的过程。
 * <p>
 * 对于状态 dp[i][j]\textit{dp}[i][j]dp[i][j]，需要枚举上一次与 12:0012:0012:00 方向对齐的位置 kkk，因此可以列出如下的转移方程：
 * <p>
 * dp[i][j]=min⁡k∈pos[key[i−1]]{dp[i−1][k]+min⁡{abs(j−k),n−abs(j−k)}+1}\textit{dp}[i][j]=\min_{k \in pos[key[i-1]]}\{dp[i-1][k]+\min\{\text{abs}(j-k),n-\text{abs}(j-k)\}+1\} dp[i][j]=k∈pos[key[i−1]]min​{dp[i−1][k]+min{abs(j−k),n−abs(j−k)}+1}
 * <p>
 * 其中 min⁡{abs(j−k),n−abs(j−k)}+1\min\{\text{abs}(j-k),n-\text{abs}(j-k)\}+1min{abs(j−k),n−abs(j−k)}+1 表示在当前第 kkk 个字符与 12:0012:0012:00 方向对齐时第 jjj 个字符旋转到 12:0012:0012:00 方向并按下拼写的最少步数。
 * <p>
 * 最后答案即为 min⁡i=0n−1{dp[m−1][i]}\min_{i=0}^{n-1}\{\textit{dp}[m-1][i]\}mini=0n−1​{dp[m−1][i]}。
 * <p>
 * 这样的做法需要开辟 O(mn)O(mn)O(mn) 的空间来存放 dp\textit{dp}dp 值。考虑到每次转移状态 dp[i][]\textit{dp}[i][]dp[i][] 只会从 dp[i−1][]\textit{dp}[i-1][]dp[i−1][] 转移过来，因此我们可以利用滚动数组优化第一维的空间复杂度，有能力的读者可以尝试实现。下面只给出最朴素的 O(mn)O(mn)O(mn) 空间复杂度的实现。
 */
public class No514 {
    public int findRotateSteps1(String ring, String key) {
        int n = ring.length(), m = key.length();
        List<Integer>[] pos = new List[26];
        for (int i = 0; i < 26; ++i) {
            pos[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; ++i) {
            pos[ring.charAt(i) - 'a'].add(i);
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dp[i], 0x3f3f3f);
        }
        for (int i : pos[key.charAt(0) - 'a']) {
            dp[0][i] = Math.min(i, n - i) + 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j : pos[key.charAt(i) - 'a']) {
                for (int k : pos[key.charAt(i - 1) - 'a']) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.min(Math.abs(j - k), n - Math.abs(j - k)) + 1);
                }
            }
        }
        return Arrays.stream(dp[m - 1]).min().getAsInt();
    }

    /**
     * 用滚动数组优化空间复杂度
     * @param ring
     * @param key
     * @return
     */
    public int findRotateSteps2(String ring, String key) {
        int n = ring.length(), m = key.length();
        List<Integer>[] pos = new List[26];
        for (int i = 0; i < 26; ++i) {
            pos[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; ++i) {
            pos[ring.charAt(i) - 'a'].add(i);
        }
        int[][] dp = new int[2][n];
        int init = 0x3f3f3f;
        for (int i = 0; i < 2; ++i) {
            Arrays.fill(dp[i], init);
        }
        for (int i : pos[key.charAt(0) - 'a']) {
            dp[0][i] = Math.min(i, n - i) + 1;
        }
        for (int i = 1; i < m; ++i) {
            int curr = i & 1;
            int pre = curr ^ 1;
            for (int j : pos[key.charAt(i) - 'a']) {
                for (int k : pos[key.charAt(i - 1) - 'a']) {
                    dp[curr][j] = Math.min(dp[curr][j], dp[pre][k] + Math.min(Math.abs(j - k), n - Math.abs(j - k)) + 1);
                }
            }
            for (int k : pos[key.charAt(i - 1) - 'a']) {
                dp[pre][k] = init;
            }
        }
        return Arrays.stream(dp[(m - 1) & 1]).min().getAsInt();
    }

    /**
     * 使用数组+剪枝
     */
    public int findRotateSteps3(String ring, String key) {
        char[] rcs = ring.toCharArray(); // ring转字符数组
        char[] kcs = key.toCharArray(); // key转字符数组

        int[] rcCount = new int[26]; // ring的每个字符的个数
        for (char rc : rcs) {
            rcCount[toKey(rc)]++;
        }

        int[][] rcIndexes = new int[26][]; // ring的每个字符对应的indexes
        for (int i = 0; i < rcCount.length; i++) {
            rcIndexes[i] = new int[rcCount[i]];
        }

        rcCount = new int[26];
        for (int i = 0; i < rcs.length; i++) {
            int rcKey = toKey(rcs[i]);
            rcIndexes[rcKey][rcCount[rcKey]++] = i;
        }

        int[] rcIndexStep = new int[rcs.length]; // 每次旋转，ring的每个index对应的迭代步数
        for (int curRcIndex : rcIndexes[toKey(kcs[0])]) { // 初始化起始字符的值
            rcIndexStep[curRcIndex] = minStep(rcs.length, 0, curRcIndex) + 1;
        }

        for (int i = 1; i < kcs.length; i++) {
            int preKey = toKey(kcs[i - 1]);
            int curKey = toKey(kcs[i]);

            if (preKey == curKey) { // 前一次字符跟本次相同时，迭代步数+1，继续下一次旋转
                for (int curRcIndex : rcIndexes[curKey]) {
                    rcIndexStep[curRcIndex]++;
                }
                continue;
            }

            for (int curRcIndex : rcIndexes[curKey]) {
                rcIndexStep[curRcIndex] = Integer.MAX_VALUE;
                for (int preRcIndex : rcIndexes[preKey]) { // 前一次字符对应的indexes旋转到本次的index，选择最小的步数
                    int step = rcIndexStep[preRcIndex] + minStep(rcs.length, preRcIndex, curRcIndex) + 1;
                    rcIndexStep[curRcIndex] = Math.min(rcIndexStep[curRcIndex], step);
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int rcIndex : rcIndexes[toKey(kcs[kcs.length - 1])]) { // 最后一个字符对应的indexes，最小的即为结果
            result = Math.min(result, rcIndexStep[rcIndex]);
        }

        return result;
    }

    private int minStep(int rcLength, int index1, int index2) {
        int step = Math.abs(index2 - index1);

        return Math.min(step, rcLength - step);
    }

    private int toKey(char c) {
        return c - 'a';
    }

    public static void main(String[] args) {
        No514 no514 = new No514();
        String ring = "abcgdhfeg";
        String key = "gdg";
        int result = no514.findRotateSteps3(ring, key);
        PrintUtils.print(result);
    }
}
