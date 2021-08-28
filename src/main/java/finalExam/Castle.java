package finalExam;

import java.util.Scanner;

public class Castle {

    private static int n;
    private static int num;
    private static long[] ans = new long[5001];
    private static long[] pre = new long[5001];
    private static long[] end = new long[5001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            num = sc.nextInt();
            pre[i] = pre[i - 1] + num;
        }
        for (int i = 1; i <= n; i++) {
            for (int k = i - 1; k >= 0; k--) {
                if (pre[i] - pre[k] >= end[k]) {
                    ans[i] = ans[k] + 1;
                    end[i] = pre[i] - pre[k];
                    break;
                }
            }
        }
        System.out.println(n - ans[n]);
    }
}
