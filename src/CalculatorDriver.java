import javax.swing.*;

/**
 * this is the driver class for the calculator application.
 */
public class CalculatorDriver extends JFrame {
    public static void main(String[] args) {
        Calculator eternity = new Calculator("Eternity Calculator");
        eternity.setVisible(true);
    }
}