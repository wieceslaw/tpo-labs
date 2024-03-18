package ru.ifmo.se;

import com.opencsv.CSVReader;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.ifmo.se.log.Ln;
import ru.ifmo.se.log.Log2;
import ru.ifmo.se.log.Log3;
import ru.ifmo.se.log.LogarithmEquation;
import ru.ifmo.se.trig.*;
import ru.ifmo.se.utils.CsvLogger;

import java.io.FileReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SystemFunctionTest {
    private static final double accuracy = 0.1;
    private static final double eps = 0.000000000000000000000001;
    private static final CsvLogger csvLogger = new CsvLogger();
    public static TrigonometryEquation trigCalculator = mock(TrigonometryEquation.class);
    public static LogarithmEquation logCalculator = mock(LogarithmEquation.class);
    private final Ln ln = new Ln();
    private final Log2 log2 = new Log2(ln);
    private final Log3 log3 = new Log3(ln);
    private final Sin sin = new Sin();
    private final Cos cos = new Cos(sin);
    private final Tan tan = new Tan(sin, cos);
    private final Cot cot = new Cot(sin, cos);
    private final Sec sec = new Sec(cos);
    private final Csc csc = new Csc(sin);

    @BeforeAll
    public static void setup() {
        fillMock(logCalculator);
        fillMock(trigCalculator);
    }

    @SneakyThrows
    private static void fillMock(TrigonometryEquation tf) {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/unit/trigFuncData.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                final double x = Double.parseDouble(record[0]);
                final double y = Double.parseDouble(record[1]);
                when(tf.evaluate(x, eps)).thenReturn(y);
            }
        }
    }

    @SneakyThrows
    private static void fillMock(LogarithmEquation tf) {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/InputLog/logFuncData.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                final double x = Double.parseDouble(record[0]);
                final double y = Double.parseDouble(record[1]);
                when(tf.evaluate(x, eps)).thenReturn(y);
            }
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/calcData.csv")
    @DisplayName("allMock")
    void allMock(Double x, Double trueResult) {
        SystemFunction calculator = new SystemFunction(logCalculator, trigCalculator);
        double result = calculator.evaluate(x, eps);
        assertEquals(trueResult, result, accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/calcData.csv")
    @DisplayName("all test without mock")
    void allTest(Double x, Double trueResult) {
        SystemFunction calculator = new SystemFunction(new LogarithmEquation(ln, log2, log3), new TrigonometryEquation(cos, sin, cot, sec, tan, csc));
        double result = calculator.evaluate(x, eps);
        assertEquals(trueResult, result, accuracy);
    }
}