package week08.classProblem;

public class LongestPalindrome {

    private long[] preH;
    private long[] sufH;
    private long[] p131;
    int p = (int) (1e9 + 7);
    int n;

    public String longestPalindrome(String s) {
        // dp解法
        // boolean[][] dp = new boolean[s.length()][s.length()];
        // for (int i = 0; i < s.length(); i++) {
        //     dp[i][i] = true;
        //     if (i < s.length() - 1) {
        //         dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        //     }
        // }
        // for (int len = 2; len < s.length(); len++) {
        //     for (int i = 0; i < s.length() - len; i++) {
        //         if (s.charAt(i) == s.charAt(i + len) && dp[i + 1][i + len - 1]) {
        //             dp[i][i + len] = true;
        //         }
        //     }
        // }
        // int maxLen = 0;
        // String res = "";
        // for (int i = 0; i < dp.length; i++) {
        //     for (int j = 0; j < dp[i].length; j++) {
        //         if (dp[i][j] && j - i + 1 > maxLen) {
        //             maxLen = j - i + 1;
        //             res = s.substring(i, j + 1);
        //         }
        //     }
        // }
        // return res;

        // 双向扩展解法
        // String ans = s.substring(0, 1);
        // int maxLen = 1;
        // for (int i = 1; i < s.length(); i++) {
        //     int cur1 = calcLongestPalindrome(s, i, i);
        //     int cur2 = calcLongestPalindrome(s, i, i + 1);
        //     int curLen = Math.max(cur1, cur2);
        //     if (curLen > maxLen) {
        //         maxLen = curLen;
        //         ans = s.substring(i - (curLen - 1) / 2, i - (curLen - 1) / 2 + curLen);
        //     }
        // }
        // return ans;

        // int n = s.length();
        // s = " " + s;
        // int ansLen = 0;
        // int ansStart = 0;
        // for (int i = 1; i <= n; i++) {
        //     int left = i - 1;
        //     int right = i + 1;
        //     while (left > 0 && right <= n && s.charAt(left) == s.charAt(right)) {
        //         left--;
        //         right++;
        //     }
        //     if (right - left - 1 > ansLen) {
        //         ansLen = right - left - 1;
        //         ansStart = left + 1;
        //     }
        // }
        //
        // for (int i = 1; i < n; i++) {
        //     int left = i;
        //     int right = i + 1;
        //     while (left > 0 && right <= n && s.charAt(left) == s.charAt(right)) {
        //         left--;
        //         right++;
        //     }
        //     if (right - left - 1 > ansLen) {
        //         ansLen = right - left - 1;
        //         ansStart = left + 1;
        //     }
        // }
        // return s.substring(ansStart, ansStart + ansLen);

        // Rabin-Karp + 二分
        n = s.length();
        s = " " + s;

        preH = new long[s.length() + 1];
        sufH = new long[s.length() + 2];
        p131 = new long[s.length() + 1];
        p131[0] = 1;
        for (int i = 1; i <= n; i++) {
            preH[i] = (preH[i - 1] * 131 + (s.charAt(i) - 'a' + 1)) % p;
            p131[i] = p131[i - 1] * 131 % p;
        }
        for (int i = n; i > 0; i--) {
            sufH[i] = (sufH[i + 1] * 131 + s.charAt(i) - 'a' + 1) % p;
        }

        int ansLen = 0;
        int ansStart = 0;
        for (int i = 1; i <= n; i++) {
            int leftLen = 0, rightLen = i;
            while (leftLen < rightLen) {
                int midLen = (leftLen + rightLen + 1) / 2;
                int left = i - midLen;
                int right = i + midLen;
                if (isPalindrome(left, right)) {
                    leftLen = midLen;
                } else {
                    rightLen = midLen - 1;
                }
            }
            if (leftLen * 2 + 1 > ansLen) {
                ansLen = leftLen * 2 + 1;
                ansStart = i - leftLen;
            }
        }
        for (int i = 1; i <= n; i++) {
            int leftLen = 0, rightLen = i;
            while (leftLen < rightLen) {
                int midLen = (leftLen + rightLen + 1) / 2;
                int left = i - midLen + 1;
                int right = i + midLen;
                if (isPalindrome(left, right)) {
                    leftLen = midLen;
                } else {
                    rightLen = midLen - 1;
                }
            }
            if (leftLen * 2 > ansLen) {
                ansLen = leftLen * 2;
                ansStart = i - leftLen + 1;
            }
        }
        return s.substring(ansStart, ansStart + ansLen);
    }

    private boolean isPalindrome(int left, int right) {
        if (left > right || left < 1 || right > n) {
            return false;
        }
        return calcPre(left, right) == calcSuf(left, right);
    }

    private int calcPre(int l, int r) {
        return (int) (((preH[r] - preH[l - 1] * p131[r - l + 1]) % p + p) % p);
    }

    private int calcSuf(int l, int r) {
        return (int) (((sufH[l] - sufH[r + 1] * p131[r - l + 1]) % p + p) % p);
    }

    private int calcLongestPalindrome(String s, int left, int right) {
        int ans = 0;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                ans = right - left + 1;
                left--;
                right++;
            } else {
                return ans;
            }
        }
        return ans;
    }
}
