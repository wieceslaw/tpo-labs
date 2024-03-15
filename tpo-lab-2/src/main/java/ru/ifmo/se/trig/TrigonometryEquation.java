package ru.ifmo.se.trig;

import lombok.RequiredArgsConstructor;
import ru.ifmo.se.MathFunction;

@RequiredArgsConstructor
public class TrigonometryEquation extends MathFunction {
    private final Cos cos;
    private final Sin sin;
    private final Cot cot;
    private final Sec sec;
    private final Tan tan;
    private final Csc csc;

    @Override
    public Double evaluate(Double x, Double epsilon) {
        Double cos = this.cos.evaluate(x, epsilon);
        Double sin = this.sin.evaluate(x, epsilon);
        Double cot = this.cot.evaluate(x, epsilon);
        Double sec = this.sec.evaluate(x, epsilon);
        Double tan = this.tan.evaluate(x, epsilon);
        Double csc = this.csc.evaluate(x, epsilon);
        return (Math.pow((Math.pow(((Math.pow(((((((sec + tan) / csc) + sec) / sin) - (cos * Math.pow((csc + sin), 3))) / sec), 2) - ((sin + (sec * csc)) - (((tan / sec) - sec) - cos))) / sin), 2) + (sin - (tan * Math.pow(((csc - sec) + cos), 3)))), 2) - (Math.pow(sec, 3) + tan)) / ((((((tan - cos) - sin) / (((sin + sec) / (((sin * cos) / csc) * sin)) + (cos / csc))) / (((sin * sec) - sin) / (sec * cot))) + sin) / (((((Math.pow(csc, 3) / csc) * cos) / cot) * ((sec + tan) - ((csc - (sin + cos)) * sec))) / (((cot + csc) + cot) * ((tan / sin) + (csc - csc)))));
    }
}
