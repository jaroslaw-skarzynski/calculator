package pl.sda.calculator.core;

import pl.sda.math.calculator.MathCalculator;
import pl.sda.secret_package.TopSecretCalculator;

public class Calculator {
    private MathCalculator mathCalculator = new MathCalculator();
    private TopSecretCalculator secretCalculator = new TopSecretCalculator();

	public int add(int a, int b) {
		return a + b;
	}
	
	public int multiply(int a, int b) {
		return a * b;
	}

	public int substraction(int a, int b) {
		return a - b;
	}

    public double cos(double a) {
        return mathCalculator.cos(a);
    }

    public double sin(double a) {
        return mathCalculator.sin(a);
    }

    public double max(double a, double b) {
        return secretCalculator.max(a, b);
    }

    public double min(double a, double b) {
        return secretCalculator.min(a, b);
    }
}