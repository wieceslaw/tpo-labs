package ru.ifmo.se.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author amifideles
 */
public class SinTest {
    @ParameterizedTest(name = "sin({0})")
    @DisplayName("Check PI dots")
    @ValueSource(doubles = {-2 * Math.PI, -1.5 * Math.PI, -Math.PI, -0.5 * Math.PI, 0, 0.5 * Math.PI, Math.PI, 1.5 * Math.PI, 2 * Math.PI})
    void checkPiDots(double param) {
        assertEquals(Math.sin(param), new Sin().calc(param, 50), 0.0001);
    }

    @ParameterizedTest(name = "sin({0}) = {1}")
    @DisplayName("Check csv elements")
    @CsvFileSource(resources = "/table_values.csv", numLinesToSkip = 1, delimiter = ';')
    void checkBetweenDotsFromCsv(double x, double y) {
        assertEquals(y, new Sin().calc(x, 50), 0.0001);
    }

    @ParameterizedTest(name = "sin({0}) = {1}")
    @DisplayName("Check csv elements")
    @CsvFileSource(resources = "/table_values_high_accuracy.csv", numLinesToSkip = 1, delimiter = ';')
    void checkBetweenDotsFromCsvHighAccuracy(double x, double y) {
        assertEquals(y, new Sin().calc(x, 50), 0.0000001);
    }

    @Test
    @DisplayName("Check illegal inputs")
    void checkWrongInputs() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Sin().calc(Double.POSITIVE_INFINITY, 50)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Sin().calc(Double.NEGATIVE_INFINITY, 50)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Sin().calc(Double.NaN, 100))
        );
    }
}
