package oneToHundred;

/**
 * @date : 2019/02/27 11:22
 * @author: liangenmao
 */
public class IsMatches {
    public static boolean isMatch(String s, String p){
        int m = s.length(), n = p.length();
        boolean dp[][] = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for(int i = 0; i <= m; ++i) {
            for(int j = 1; j <= n; ++j) {
                if(p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2] || i > 0 && j > 1 && dp[i - 1][j] && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.');
                } else {
                    dp[i][j] = i > 0 && dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
                }
            }
        }
        return dp[m][n];
    }
}
