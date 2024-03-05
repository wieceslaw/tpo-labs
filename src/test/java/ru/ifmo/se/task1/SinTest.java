package ru.ifmo.se.task1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class SinTest {
    @Test
    void testWrongInputs() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Sin().execute(Double.POSITIVE_INFINITY, 50)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Sin().execute(Double.NEGATIVE_INFINITY, 50)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Sin().execute(Double.NaN, 100))
        );
    }

    @ParameterizedTest(name = "sin({0})")
    @ValueSource(doubles = {-2 * Math.PI, -1.5 * Math.PI, -Math.PI, -0.5 * Math.PI, 0, 0.5 * Math.PI, Math.PI, 1.5 * Math.PI, 2 * Math.PI})
    void testPiDots(double param) {
        assertEquals(Math.sin(param), new Sin().execute(param, 50), 0.0001);
    }

    @ParameterizedTest(name = "sin({0}) = {1}")
    @MethodSource({"values"})
    void testBetweenDotsFromCsv1(double x, double y) {
        assertEquals(y, new Sin().execute(x, 50), 0.0001);
    }

    private static Stream<Arguments> values() {
        return Stream.of(
                Arguments.of(-2.14, -0.8423),
                Arguments.of(-1.17, -0.9208),
                Arguments.of(-0.32, -0.3146),
                Arguments.of(0.19, 0.1889),
                Arguments.of(1.99, 0.9134),
                Arguments.of(3.0, 0.1411),
                Arguments.of(10.0, -0.544),
                Arguments.of(20.0, 0.912945),
                Arguments.of(50.0, -0.262375),
                Arguments.of(-50.0, 0.262375)
        );
    }
}
