package ru.ifmo.se.task1;

/**
 * @author amifideles
 */
public class Sin {
    public double execute(double x, int n) {
        if (Double.isInfinite(x) || Double.isNaN(x)) {
            throw new IllegalArgumentException("Argument can't be infinite or null!");
        }
        double PI2 = Math.PI * 2;
        x = x % PI2;
        double result = 0;
        double xx = x * x;
        double pow = x;
        double fact = 1;
        int sign = 1;
        for (int i = 1; i < n; i += 2) {
            fact *= i;
            result += sign * pow / fact;
            sign = -sign;
            fact *= (i + 1);
            pow *= xx;
        }
        return result;
    }
}
