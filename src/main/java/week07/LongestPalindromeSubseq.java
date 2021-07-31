package week07;

public class LongestPalindromeSubseq {

    public int longestPalindromeSubseq(String s) {
        // 区间DP
        // int[][] dp = new int[s.length()][s.length()];
        // for (int i = 0; i < s.length(); i++) {
        //     dp[i][i] = 1;
        // }
        // for (int len = 2; len <= s.length(); len++) {
        //     for (int i = 0; i < s.length() - len + 1; i++) {
        //         int j = i + len - 1;
        //         dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
        //         if (s.charAt(i) == s.charAt(j)) {
        //             dp[i][j] = dp[i + 1][j - 1] + 2;
        //         }
        //     }
        // }
        // return dp[0][s.length() - 1];

        // i,j 代表字符串的i到j处
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        for (int i = s.length() - 2; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }

    public static void main(String[] args) {
        String s = "cbbd";
        LongestPalindromeSubseq longestPalindromeSubseq = new LongestPalindromeSubseq();
        longestPalindromeSubseq.longestPalindromeSubseq(s);
    }
}
