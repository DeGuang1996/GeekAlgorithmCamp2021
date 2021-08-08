package week08.classProblem;

public class RepeatedStringMatch {

    public int repeatedStringMatch(String a, String b) {
        // Java API解法
        // if (a.contains(b)) {
        //     return 1;
        // }
        //
        // int min = b.length() / a.length();
        // StringBuilder sb = new StringBuilder();
        // for (int i = 1; i <= min + 2; i++) {
        //     sb.append(a);
        //     if (i >= min && sb.toString().contains(b)) {
        //         return i;
        //     }
        // }
        // return -1;

        // Rabin-Karp
        int multiply = b.length() / a.length() + 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < multiply; i++) {
            sb.append(a);
        }
        a = sb.toString();
        int index = strStr(a, b);
        if (index == -1) {
            return -1;
        } else {
            int aLen = a.length() / multiply;
            return (index + b.length() + aLen - 1) / aLen;
        }
    }

    private int strStr(String haystack, String needle) {
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

    public static void main(String[] args) {
        String a = "abcd";
        String b = "cdabcdab";
        RepeatedStringMatch repeatedStringMatch = new RepeatedStringMatch();
        repeatedStringMatch.repeatedStringMatch(a, b);
    }
}
