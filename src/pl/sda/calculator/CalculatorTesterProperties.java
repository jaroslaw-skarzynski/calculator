package pl.sda.calculator;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.util.Properties;

public class CalculatorTesterProperties implements CalculatorTesterPropertiesMBean {
    private static final String PROPERTIES_FILENAME = "/application.properties";
    private static final String ATTEMPS_NUMBER_PROPERTY_NAME = "calculator.attemps.number";
    private static final String VALUE_FROM_PROPERTY_NAME = "calculator.value.from";
    private static final String VALUE_TO_PROPERTY_NAME = "calculator.value.to";
    private static final String DELAY_PROPERTY_NAME = "calculator.delay.millis";

    private int attemptsNumber = 10;
    private int valueFrom = 0;
    private int valueTo = 10;
    private int delayMillis = 1000;

    public CalculatorTesterProperties(String[] args) throws IOException {
        InputStream resourceAsStream = getClass().getResourceAsStream(PROPERTIES_FILENAME);
        Properties properties = new Properties();
        if(resourceAsStream != null) {
            properties.load(resourceAsStream);
            delayMillis = Integer.valueOf(properties.getProperty(DELAY_PROPERTY_NAME, String.valueOf(delayMillis)));
        }

        if (args.length > 0) {
            attemptsNumber = Integer.valueOf(args[0]);
            valueFrom = Integer.valueOf(args[1]);
            valueTo = Integer.valueOf(args[2]);
        } else if (System.getProperty(ATTEMPS_NUMBER_PROPERTY_NAME) != null) {
            attemptsNumber = Integer.valueOf(System.getProperty(ATTEMPS_NUMBER_PROPERTY_NAME));
            valueFrom = Integer.valueOf(System.getProperty(VALUE_FROM_PROPERTY_NAME, String.valueOf(valueFrom)));
            valueTo = Integer.valueOf(System.getProperty(VALUE_TO_PROPERTY_NAME, String.valueOf(valueTo)));
        } else {
            if(resourceAsStream != null) {
                attemptsNumber = Integer.valueOf(properties.getProperty(ATTEMPS_NUMBER_PROPERTY_NAME));
                valueFrom = Integer.valueOf(properties.getProperty(VALUE_FROM_PROPERTY_NAME, String.valueOf(valueFrom)));
                valueTo = Integer.valueOf(properties.getProperty(VALUE_TO_PROPERTY_NAME, String.valueOf(valueTo)));
            }
        }

        try {
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            ObjectName objectName = new ObjectName("pl.sda.calculator:type=properties,name=calculator");
            server.registerMBean(this, objectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getAttemptsNumber() {
        return attemptsNumber;
    }

    @Override
    public int getValueFrom() {
        return valueFrom;
    }

    @Override
    public int getValueTo() {
        return valueTo;
    }

    @Override
    public void setAttemptsNumber(int attemptsNumber) {
        this.attemptsNumber = attemptsNumber;
        System.out.println("Nowa wartość dla parametru attemptsNumber = " + attemptsNumber);
    }

    @Override
    public void setValueFrom(int valueFrom) {
        this.valueFrom = valueFrom;
        System.out.println("Nowa wartość dla parametru valueFrom = " + attemptsNumber);
    }

    @Override
    public void setValueTo(int valueTo) {
        this.valueTo = valueTo;
        System.out.println("Nowa wartość dla parametru valueTo = " + attemptsNumber);
    }

    public int getDelayMillis() {
        return delayMillis;
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
