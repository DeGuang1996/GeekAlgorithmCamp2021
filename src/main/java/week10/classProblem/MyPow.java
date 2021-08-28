package week10.classProblem;

public class MyPow {

    public double myPow(double x, long n) {
        if (n < 0) {
            return 1 / myPow(x, -n);
        }
        double ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                ans *= x;
            }
            n >>= 1;
            x = x * x;
        }
        return ans;
    }
}
