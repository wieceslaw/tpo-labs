package ru.ifmo.se;

import com.opencsv.CSVReader;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.ifmo.se.log.LogarithmEquation;
import ru.ifmo.se.trig.TrigonometryEquation;
import ru.ifmo.se.utils.CsvLogger;

import java.io.FileReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SystemFunctionTest {
    private static final double accuracy = 0.1;
    private static final double eps = 0.000000000001;
    private static final CsvLogger csvLogger = new CsvLogger();
    public static TrigonometryEquation trigCalculator = mock(TrigonometryEquation.class);
    public static LogarithmEquation logCalculator = mock(LogarithmEquation.class);

    @BeforeAll
    public static void setup() {
        fillMock(logCalculator);
        fillMock(trigCalculator);
    }

    @SneakyThrows
    private static void fillMock(TrigonometryEquation tf) {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/integration/trigFuncData.csv"))) {
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
}