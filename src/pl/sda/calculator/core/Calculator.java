package pl.sda.calculator.core;

import pl.sda.math.calculator.MathCalculator;
import pl.sda.secret_package.TopSecretCalculator;

/**
 * Prosty kalkulator do obsługi funkcji: dodawania, mnożenia, odejmowania, cos, sin, max, min
 * @author Jarosław Skarżyński
 * @version 1.0
 */
public class Calculator {
    private MathCalculator mathCalculator = new MathCalculator();
    private TopSecretCalculator secretCalculator = new TopSecretCalculator();

	public int add(int a, int b) {
		return a + b;
	}

    /**
     * Metoda wykonuję funkcję mnożenia na podanych argumentach.
     * Uwaga! Argumenty muszą być liczbami dodatnimi!
     * @param a argument 1
     * @param b argument 1
     * @return wartość mnożenia
     * @throws IllegalArgumentException w przypadku gdy jeden z argumentów nie jest liczbą dodatnią
     */
	public int multiply(int a, int b) {
	    if(a <= 0 || b <= 0) {
	        throw new IllegalArgumentException("Argumentu nie są liczbami dodatnimi!");
        }

		return a * b;
	}

	public int substraction(int a, int b) {
		return a - b;
	}

    /**
     * Metoda wykonuję funkcję cos na podanym argumencie. Więcej szczegółów {@link MathCalculator}
     * @param a argument do użycia w funkcji cos
     * @return wartość funkcji cos na podanym argumencie
     */
    public double cos(double a) {
        return mathCalculator.cos(a);
    }

    /**
     * Metoda wykonuję funkcję sin na podanym argumencie.
     * @param a argument do użycia w funkcji sin
     * @return wartość funkcji sin na podanym argumencie
     * @deprecated użyj zamiast tej metody {@link Math#sin(double)}
     */
    public double sin(double a) {
        return mathCalculator.sin(a);
    }

    /**
     * Metoda zwraca większość wartość spośród podanych argumentów.
     * @param a argument 1
     * @param b argument 2
     * @return większy z podanych argumentów
     *
     * @see TopSecretCalculator#max(double, double)
     * @see Math#max(double, double)
     *
     */
    public double max(double a, double b) {
        return secretCalculator.max(a, b);
    }

    public double min(double a, double b) {
        return secretCalculator.min(a, b);
    }
}