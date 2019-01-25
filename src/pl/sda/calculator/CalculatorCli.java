package pl.sda.calculator;

import pl.sda.calculator.core.Calculator;

public class CalculatorCli {

    public static void main(String[] args) {
        pl.sda.math.calculator.Utils.printLogo();
        Calculator calculator = new Calculator();
        System.out.println("4 + 7 = " + calculator.add(4, 7));
        System.out.println("4 * 7 = " + calculator.multiply(4, 7));
        System.out.println("4 - 7 = " + calculator.substraction(4, 7));
        System.out.println("sin(1.65) = " + calculator.sin(1.65));
        System.out.println("cos(5.5) = " + calculator.cos(5.5));
        System.out.println("max(-1, 10) = " + calculator.max(-1, 10));
        System.out.println("min(-1, 10) = " + calculator.min(-1, 10));
    }
}