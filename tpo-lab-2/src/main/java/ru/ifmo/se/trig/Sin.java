package ru.ifmo.se.trig;

import static java.lang.Double.NaN;
import static java.lang.Math.PI;

public class Sin extends TrigFunction {

    @Override
    public Double evaluate(Double x, Double eps) {
        x = validateInput(x);
        if (x.equals(NaN)) return NaN;

        if (x == 0 || x == -PI) {
            return 0.0;
        }

        int n = 1;
        double resultSin = 0;
        double xx = x * x;
        double pow = x;
        double fact = 1;
        int sign = 1;

        while (true) {
            double term = sign * pow / fact;
            if (Math.abs(term) <= eps) {
                break;
            }
            resultSin += term;

            sign = -sign;
            fact *= (n + 1) * (n + 2);
            pow *= xx;
            n += 2;
        }

        return resultSin;
    }
}