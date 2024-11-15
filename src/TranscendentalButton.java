import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.DecimalFormat;
import java.util.stream.Stream;

/**
 * this is the TranscendentalButton class.
 */
class TranscendentalButton extends JButton implements ActionListener {
    Calculator calculator;

    /**
     * parameterized constructor for TranscendentalButton.
     * @param x the x-coordinate of the button's position on the screen.
     * @param y the y-coordinate of the button's position on the screen.
     * @param width the width of the button.
     * @param height the height of the button.
     * @param label the label text displayed on the button.
     * @param calc the Calculator instance with which this button will be associated.
     */
    public TranscendentalButton(int x, int y, int width, int height, String label, Calculator calc) {
        super(label);
        setBounds(x, y, width, height);
        setMargin(new Insets(0, 0, 0, 0));
        this.calculator = calc;
        this.calculator.add(this);
        addActionListener(this);
    }

    //put all transcendental function implementation here **PLEASE SHOW YOUR IMPLEMENTATION WITH YOUR REVIEWER BEFORE MAKING A PULL REQUEST**
    /**
     * this method processes an action event triggered by a user interface component.
     * @param e the event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String transcendentalText = ((TranscendentalButton) e.getSource()).getText();
        String displayText = calculator.displayLabel.getText();
        DecimalFormat df1 = new DecimalFormat("#.00"), df2 = new DecimalFormat("#.00000");
        double result;

        //transform string to list of parameters
        double[] parameterList = Stream.of(displayText.split(",")).mapToDouble(Double::parseDouble).toArray();

        try {
            switch(transcendentalText) {
                case "st.dev":
                    result = calculator.functions.standardDeviation(parameterList);
                    calculator.displayLabel.setText(Calculator.getFormattedText(Double.parseDouble(df1.format(result))));
                    break;
                case "x^y":
                    if(parameterList.length < 2) throw new TooFewArguments("Error: Too Few Arguments");
                    if(parameterList.length > 2) throw new TooManyArguments("Error: Too Many Arguments");

                    result = calculator.functions.xPowerY(parameterList);
                    calculator.displayLabel.setText(Calculator.getFormattedText(result));
                    break;
                case "a(b^x)":
                    if(parameterList.length < 3) throw new TooFewArguments("Error: Too Few Arguments");
                    if(parameterList.length > 3) throw new TooManyArguments("Error: Too Many Arguments");

                    result = calculator.functions.abPowerX(parameterList);
                    calculator.displayLabel.setText(Calculator.getFormattedText(result));
                    break;
                case "mad":
                    result = calculator.functions.meanAbsoluteDeviation(parameterList);
                    calculator.displayLabel.setText(Calculator.getFormattedText(Double.parseDouble(df1.format(result))));
                    break;
                case "arccos(x)":
                    if(parameterList.length > 1) throw new TooManyArguments("Error: Too Many Arguments");

                    result = calculator.functions.arcCosX(parameterList, calculator.getIsDegreeMode());
                    calculator.displayLabel.setText(String.format("%.4f" , result));
                    break;
                case "log_b(x)":
                    if(parameterList.length == 1) result = calculator.functions.logXBase10(parameterList[0]);
                    else if(parameterList.length == 2) result = calculator.functions.logXBaseB(parameterList);
                    else throw new TooManyArguments("Error: Too Many Arguments");

                    calculator.displayLabel.setText(Calculator.getFormattedText(Double.parseDouble(df2.format(result))));
                    break;
                case "ln(x)":
                    if(parameterList.length > 1) throw new TooManyArguments("Error: Too Many Arguments");

                    result = calculator.functions.ln(parameterList[0]);
                    calculator.displayLabel.setText(Calculator.getFormattedText(Double.parseDouble(df2.format(result))));
                	break;
                case "sinh(x)":
                    if(parameterList.length > 1) throw new TooManyArguments("Error: Too Many Arguments");

                    result = calculator.functions.sinHX(parameterList);
                    calculator.displayLabel.setText(Calculator.getFormattedText(result));
                    break;
            }
        } catch(IllegalArgumentException | TooFewArguments | TooManyArguments err) { calculator.displayLabel.setText(err.getMessage());
        } catch(Exception err) { calculator.displayLabel.setText("An error occurred: " + err.getMessage());
        }
    }
}
