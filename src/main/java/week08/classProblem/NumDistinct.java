package week08.classProblem;

public class NumDistinct {

    public int numDistinct(String s, String t) {
        // 朴素二维dp
        // int n = s.length();
        // int m = t.length();
        // s = " " + s;
        // t = " " + t;
        // int[][] dp = new int[n + 1][m + 1];
        // for (int i = 0; i <= n; i++) {
        //     dp[i][0] = 1;
        // }
        // for (int i = 1; i <= n; i++) {
        //     for (int j = 1; j <= m; j++) {
        //         dp[i][j] = dp[i - 1][j];
        //         if (s.charAt(i) == t.charAt(j)) {
        //             dp[i][j] += dp[i - 1][j - 1];
        //         }
        //     }
        // }
        // return dp[n][m];

        // 空间优化
        int[] dp = new int[t.length() + 1];
        dp[0] = 1;
        for (int i = 0; i < s.length(); i++){
            for (int j = t.length() - 1; j >= 0; j--) {
                if (t.charAt(j) == s.charAt(i)) {
                    dp[j + 1] += dp[j];
                }
            }
        }
        return dp[t.length()];
    }
}
