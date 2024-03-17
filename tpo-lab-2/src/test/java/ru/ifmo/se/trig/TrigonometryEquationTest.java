package ru.ifmo.se.trig;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TrigonometryEquationTest {

    public static Sin sin = mock(Sin.class);
    public static Cos cos = mock(Cos.class);
    public static Tan tan = mock(Tan.class);
    public static Cot cot = mock(Cot.class);
    public static Sec sec = mock(Sec.class);
    public static Csc csc = mock(Csc.class);
    private static final double eps = 0.0000001;
    private final double accuracy = 0.01;


    @BeforeAll
    public static void setup() {
        fillMock(sin, "src/test/resources/unit/sinData.csv");
        fillMock(cos, "src/test/resources/unit/cosData.csv");
        fillMock(tan, "src/test/resources/unit/tanData.csv");
        fillMock(cot, "src/test/resources/unit/cotData.csv");
        fillMock(sec, "src/test/resources/unit/secData.csv");
        fillMock(csc, "src/test/resources/unit/cscData.csv");

    }

    private static void fillMock(TrigFunction tf, String tableName) {
        try (CSVReader csvReader = new CSVReader(new FileReader(tableName))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                final double x = Double.parseDouble(record[0]);
                final double y = Double.parseDouble(record[1]);
                when(tf.evaluate(x, TrigonometryEquationTest.eps)).thenReturn(y);
            }
        } catch (IOException | CsvException ignored) {
        }
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/unit/trigFuncData.csv")
    @DisplayName("trigonometric function test with full mocks")
    void trigFuncTest(Double input, Double trueResult) {
        TrigonometryEquation trigonometricCalculator = new TrigonometryEquation(cos, sin, cot, sec, tan, csc);
        double result = trigonometricCalculator.evaluate(input, eps);
        assertEquals(trueResult, result, accuracy);
    }
}