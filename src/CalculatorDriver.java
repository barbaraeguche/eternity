import javax.swing.*;
import button.Button;

public class CalculatorDriver {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");

        Button one = new Button("1", 4, 10);

        frame.add(one.getButton());

        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.setVisible(true);

    }
}