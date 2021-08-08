package week08.classProblem;

public class StrStr {

    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        haystack = " " + haystack;
        needle = " " + needle;
        int p = (int) 1e9 + 7;
        long tHash = 0;
        for (int i = 1; i <= m; i++) {
            tHash = (tHash * 131 + needle.charAt(i) - 'a' + 1) % p;
        }
        long[] sHash = new long[n + 1];
        long[] p131 = new long[n + 1];
        p131[0] = 1;
        for (int i = 1; i <= n; i++) {
            sHash[i] = (sHash[i - 1] * 131 + haystack.charAt(i) - 'a' + 1) % p;
            p131[i] = p131[i - 1] * 131 % p;
        }
        for (int i = m; i < n; i++) {
            if (calcHash(sHash, p131, p, i - m + 1, i) == tHash && haystack.substring(i - m + 1, i + 1).equals(needle.substring(1))) {
                // 前面加了一个空格
                return i - m;
            }
        }
        return -1;
    }

    private long calcHash(long[] sHash, long[] p131, int p, int l, int r) {
        return ((sHash[r] - sHash[l - 1] * p131[r - l + 1] % p) + p) % p;
    }
}
