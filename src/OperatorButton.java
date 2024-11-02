import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * this is the OperatorButton class.
 */
class OperatorButton extends JButton implements ActionListener {
    Calculator calculator;

    /**
     * parameterized constructor for OperatorButton.
     * @param x the x-coordinate of the button's position on the screen.
     * @param y the y-coordinate of the button's position on the screen.
     * @param width the width of the button.
     * @param height the height of the button.
     * @param label the label text displayed on the button.
     * @param calc the Calculator instance with which this button will be associated.
     */
    public OperatorButton(int x, int y, int width, int height, String label, Calculator calc) {
        super(label);
        setBounds(x, y, width, height);
        setMargin(new Insets(0, 0, 0, 0));
        this.calculator = calc;
        this.calculator.add(this);
        addActionListener(this);
    }

    /**
     * this method processes an action event triggered by a user interface component.
     * @param e the event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String operationText = ((OperatorButton) e.getSource()).getText();
        double temp = Double.parseDouble(calculator.displayLabel.getText());
        calculator.setClear = true;

        try {
            switch(operationText) {
                case "1/x":
                    calculator.displayLabel.setText(Calculator.getFormattedText(1 / (double)temp));
                    return;
                case "sqrt":
                    calculator.displayLabel.setText(Calculator.getFormattedText(Math.sqrt(temp)));
                    return;
                case "/": case "x": case "-": case "+": case "%":
                    calculator.number = temp;
                    calculator.op = operationText.charAt(0);
                    return;
            }
        } catch (ArithmeticException exc) { calculator.displayLabel.setText("Error: Dividing by Zero"); return; }

        try {
            temp = switch(calculator.op) {
                case '+' -> calculator.number + temp;
                case '-' -> calculator.number - temp;
                case 'x' -> calculator.number * temp;
                case '%' -> calculator.number % temp;
                case '/' -> calculator.number / temp;
                default -> temp;
            };
        } catch (ArithmeticException err) {
            String errorMsg = calculator.op == '%'? "Error: Modulus by zero" : "Error: Dividing by Zero";
            calculator.displayLabel.setText(errorMsg); return;
        }

        calculator.displayLabel.setText(Calculator.getFormattedText(temp));
        calculator.number = temp;
    }
}