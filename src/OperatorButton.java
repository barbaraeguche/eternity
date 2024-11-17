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
        String displayText = calculator.displayLabel.getText();

        try {
            if (displayText.contains(","))
                throw new TooManyArguments("Error: Too Many Arguments");
        } catch (TooManyArguments exc) {
            calculator.displayLabel.setText("Error: Too Many Parameters");
            return;
        }

        double temp = Double.parseDouble(displayText);
        calculator.setClear = true;

        try {
            switch (operationText) {
                case "1/x":
                    calculator.displayLabel.setText(Calculator.getFormattedText(1 / temp));
                    return;
                case "sqrt":
                    calculator.displayLabel.setText(Calculator.getFormattedText(Math.sqrt(temp)));
                    return;
                case "=":
                    if (calculator.op == '\0')
                        return; // No operation to perform
                    temp = calculateIntermediateResult(temp);
                    calculator.displayLabel.setText(Calculator.getFormattedText(temp));
                    calculator.number = temp; // Update number to allow chain calculations
                    calculator.op = '\0'; // Reset operator
                    return;
                default:
                    // If there's a previous operation, calculate the intermediate result
                    if (calculator.op != '\0') {
                        temp = calculateIntermediateResult(temp);
                        calculator.displayLabel.setText(Calculator.getFormattedText(temp));
                    }
                    // Store the current value and operator for the next operation
                    calculator.number = temp;
                    calculator.op = operationText.charAt(0);
                    return;
            }
        } catch (ArithmeticException exc) {
            calculator.displayLabel.setText("Error: Math Error");
        }
    }

    /**
     * Helper method to calculate the intermediate result based on the stored
     * operator and number.
     * 
     * @param temp The current value entered by the user.
     * @return The calculated result.
     */
    private double calculateIntermediateResult(double temp) {
        try {
            return switch (calculator.op) {
                case '+' -> calculator.number + temp;
                case '-' -> calculator.number - temp;
                case 'x' -> calculator.number * temp;
                case '%' -> calculator.number % temp;
                case '/' -> calculator.number / temp;
                default -> temp;
            };
        } catch (ArithmeticException err) {
            String errorMsg = calculator.op == '%'
                    ? "Error: Modulus by zero"
                    : "Error: Dividing by Zero";
            calculator.displayLabel.setText(errorMsg);
            return temp; // Return current temp in case of error
        }
    }
}