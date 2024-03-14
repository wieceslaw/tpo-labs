package ru.ifmo.se.trig;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.ifmo.se.utils.CsvLogger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinTest {
    private final CsvLogger csvLogger = new CsvLogger();
    private final Sin sin = new Sin();
    private final double accuracy = 0.1;
    private final double eps = 0.0000001;

    @ParameterizedTest
    @CsvFileSource(resources = "/inputTrig/sinData.csv")
    @DisplayName("sin(x) test")
    void sinTest(Double divisible, Double divider, Double trueResult) {
        try {
            csvLogger.setFilePath("src/test/resources/results/trig/sin.csv");
            double x = divisible * Math.PI / divider;
            double result = sin.evaluate(x, eps);
            csvLogger.logger(x, result);
            assertEquals(trueResult, result, accuracy);
        } catch (ArithmeticException e) {
            assertEquals("x should be <= 0", e.getMessage());
        }
    }
}