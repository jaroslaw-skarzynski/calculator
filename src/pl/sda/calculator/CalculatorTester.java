package pl.sda.calculator;

import pl.sda.calculator.core.Calculator;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class CalculatorTester {
    private CalculatorTesterProperties testerProperties;
    private Calculator calculator;

    private CalculatorTester(String[] args) throws IOException {
        testerProperties = new CalculatorTesterProperties(args);
        calculator = new Calculator();
    }

    private void runTests() {
        System.out.printf("%nParametry testara: attemptsNumber=%d, valueFrom=%d, valueTo=%d%n%n",
                testerProperties.getAttemptsNumber(), testerProperties.getValueFrom(), testerProperties.getValueTo());

        for (int i = 1; i <= testerProperties.getAttemptsNumber(); i++) {
            int methodNumber = getRandomInteger(1, 7);
            String counter = String.format("[%d/%d]", i, testerProperties.getAttemptsNumber());
            int a, b, result;
            double x, y, resultDouble;
            switch (methodNumber) {
                case 1:
                    a = getRandomInteger();
                    b = getRandomInteger();
                    result = calculator.add(a, b);
                    System.out.printf("%s %d + %d = %d%n", counter, a, b, result);
                    break;
                case 2:
                    a = getRandomInteger();
                    b = getRandomInteger();
                    result = calculator.multiply(a, b);
                    System.out.printf("%s %d * %d = %d%n", counter, a, b, result);
                    break;
                case 3:
                    a = getRandomInteger();
                    b = getRandomInteger();
                    result = calculator.substraction(a, b);
                    System.out.printf("%s %d - %d = %d%n", counter, a, b, result);
                    break;
                case 4:
                    x = getRandomDouble();
                    resultDouble = calculator.cos(x);
                    System.out.printf("%s cos(%.2f) = %.2f%n", counter, x, resultDouble);
                    break;
                case 5:
                    x = getRandomDouble();
                    resultDouble = calculator.sin(x);
                    System.out.printf("%s sin(%.2f) = %.2f%n", counter, x, resultDouble);
                    break;
                case 6:
                    x = getRandomDouble();
                    y = getRandomDouble();
                    resultDouble = calculator.max(x, y);
                    System.out.printf("%s max(%.2f, %.2f) = %.2f%n", counter, x, y, resultDouble);
                    break;
                case 7:
                    x = getRandomDouble();
                    y = getRandomDouble();
                    resultDouble = calculator.min(x, y);
                    System.out.printf("%s min(%.2f, %.2f) = %.2f%n", counter, x, y, resultDouble);
                    break;
            }
        }
    }

    private int getRandomInteger() {
        return getRandomInteger(testerProperties.getValueFrom(), testerProperties.getValueTo());
    }

    private int getRandomInteger(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to + 1);
    }

    private double getRandomDouble() {
        return ThreadLocalRandom.current().nextDouble(testerProperties.getValueFrom(), testerProperties.getValueTo());
    }

    public static void main(String[] args) throws IOException {
        CalculatorTester calculatorTester = new CalculatorTester(args);
        calculatorTester.runTests();
    }
}
