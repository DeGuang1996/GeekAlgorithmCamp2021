package week02;

public class MyPow {

    public double myPow(double x, long n) {
        if (n < 0) {
            return 1 / myPow(x, -n);
        }
        if (n == 0) {
            return 1;
        }
        double temp = myPow(x, n / 2);
        if (n % 2 == 0) {
            return temp * temp;
        }
        return temp * temp * x;
    }
}
