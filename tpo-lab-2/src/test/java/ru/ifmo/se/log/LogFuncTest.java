package ru.ifmo.se.log;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.ifmo.se.utils.CsvLogger;

import java.io.File;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogFuncTest {

    private final Ln ln = new Ln();
    private final Log2 log2 = new Log2(ln);
    private final Log3 log3 = new Log3(ln);
    private final CsvLogger csvLogger = new CsvLogger();
    private final double accuracy = 0.01;
    private final double eps = 0.0001;

    @BeforeAll
    public void clearFiles() {
        String directoryPath = "src/test/resources/results/log/";
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files) {
            csvLogger.setFilePath(String.valueOf(file));
            csvLogger.clearFile();
        }
    }


    @Test
    @DisplayName("checkX test")
    void checkTest() {
        assertEquals(NaN, ln.evaluate(NaN, eps));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/lnData.csv")
    @DisplayName("ln(x) test")
    void lnTest(Double x, Double trueResult) {
        csvLogger.setFilePath("src/test/resources/results/log/ln.csv");
        double result = ln.evaluate(x, eps);
        csvLogger.logger(x, result);
        assertEquals(trueResult, result, accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/log3Data.csv")
    @DisplayName("log3(x) test")
    void log3Test(Double x, Double trueResult) {
            csvLogger.setFilePath("src/test/resources/results/log/log3.csv");
            double result = log3.evaluate(x, eps);
            csvLogger.logger(x, result);
            assertEquals(trueResult, result, accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InputLog/log2Data.csv")
    @DisplayName("log2(x) test")
    void log2Test(Double x, Double trueResult) {
            csvLogger.setFilePath("src/test/resources/results/log/log2.csv");
            double result = log2.evaluate(x, eps);
            csvLogger.logger(x, result);
            assertEquals(trueResult, result, accuracy);
    }
}
