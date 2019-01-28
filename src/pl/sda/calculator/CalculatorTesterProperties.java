package pl.sda.calculator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CalculatorTesterProperties {
    private static final String PROPERTIES_FILENAME = "/application.properties";
    private static final String ATTEMPS_NUMBER_PROPERTY_NAME = "calculator.attemps.number";
    private static final String VALUE_FROM_PROPERTY_NAME = "calculator.value.from";
    private static final String VALUE_TO_PROPERTY_NAME = "calculator.value.to";

    private int attemptsNumber = 10;
    private int valueFrom = 0;
    private int valueTo = 10;

    public CalculatorTesterProperties(String[] args) throws IOException {
        if (args.length > 0) {
            attemptsNumber = Integer.valueOf(args[0]);
            valueFrom = Integer.valueOf(args[1]);
            valueTo = Integer.valueOf(args[2]);
        } else if (System.getProperty(ATTEMPS_NUMBER_PROPERTY_NAME) != null) {
            attemptsNumber = Integer.valueOf(System.getProperty(ATTEMPS_NUMBER_PROPERTY_NAME));
            valueFrom = Integer.valueOf(System.getProperty(VALUE_FROM_PROPERTY_NAME, String.valueOf(valueFrom)));
            valueTo = Integer.valueOf(System.getProperty(VALUE_TO_PROPERTY_NAME, String.valueOf(valueTo)));
        } else {
            InputStream resourceAsStream = getClass().getResourceAsStream(PROPERTIES_FILENAME);
            if(resourceAsStream != null) {
                Properties properties = new Properties();
                properties.load(resourceAsStream);

                attemptsNumber = Integer.valueOf(properties.getProperty(ATTEMPS_NUMBER_PROPERTY_NAME));
                valueFrom = Integer.valueOf(properties.getProperty(VALUE_FROM_PROPERTY_NAME, String.valueOf(valueFrom)));
                valueTo = Integer.valueOf(properties.getProperty(VALUE_TO_PROPERTY_NAME, String.valueOf(valueTo)));
            }
        }
    }

    public int getAttemptsNumber() {
        return attemptsNumber;
    }

    public int getValueFrom() {
        return valueFrom;
    }

    public int getValueTo() {
        return valueTo;
    }

    @Override
    public String toString() {
        return "CalculatorTesterProperties{" +
                "attemptsNumber=" + attemptsNumber +
                ", valueFrom=" + valueFrom +
                ", valueTo=" + valueTo +
                '}';
    }
}
