package week06;

public class MinDistance {

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                int min = Integer.MAX_VALUE;
                // delete
                min = Math.min(min, dp[i - 1][j] + 1);
                // insert
                min = Math.min(min, dp[i][j - 1] + 1);
                // modify
                min = Math.min(min, dp[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1));
                dp[i][j] = min;
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
