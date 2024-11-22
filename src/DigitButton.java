import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * this is the DigitButton class.
 */
class DigitButton extends JButton implements ActionListener {
    Calculator calculator;

    /**
     * parameterized constructor for DigitButton.
     * @param x the x-coordinate of the button's position on the screen.
     * @param y the y-coordinate of the button's position on the screen.
     * @param width the width of the button.
     * @param height the height of the button.
     * @param label the label text displayed on the button.
     * @param calc the Calculator instance with which this button will be associated.
     */
    DigitButton(int x, int y, int width, int height, String label, Calculator calc) {
        super(label);
        setBounds(x, y, width, height);
        setMargin(new Insets(0, 0, 0, 0));
        this.calculator = calc;
        this.calculator.add(this);
        addActionListener(this);
    }
    /**
     * this method checks if the given string contains a decimal point.
     * @param string the string to be checked for the presence of a period.
     * @return true if the string contains a period; false otherwise.
     */
    static boolean isInString(String string) { 
    	if(string.contains(",")){
    		String lastNum = string.substring(string.lastIndexOf(","));
    		return lastNum.contains(".") || lastNum.length()==1;
    	} else {
    		return string.contains(".");
    	}
    }

    /**
     * this method processes an action event triggered by a user interface component.
     * @param e the event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //get the label of button and append it to the displayLabel of the Calculator.
        String digitText = ((DigitButton) e.getSource()).getText(); int index;
        if(calculator.displayLabel.getText().contains("Error")) {
            calculator.setClear = true;
        }

        switch(digitText) {
            case "." -> {
                if(calculator.setClear) {
                    calculator.displayLabel.setText("0.");
                    calculator.setClear = false;
                } else if (!isInString(calculator.displayLabel.getText())) {
                	calculator.displayLabel.setText(calculator.displayLabel.getText() + ".");
                }
                else if(calculator.displayLabel.getText().lastIndexOf(",") == calculator.displayLabel.getText().length() - 1) {
                	calculator.displayLabel.setText(calculator.displayLabel.getText() + "0.");
                }
                return;
            }
            case "+/-" -> {
                String currentText = calculator.displayLabel.getText();

                if(currentText.contains(",")) {
                    StringBuilder result = updateSignList(currentText);
                    calculator.displayLabel.setText(String.valueOf(result));
                } else {
                    calculator.displayLabel.setText(Double.toString(Double.parseDouble(calculator.displayLabel.getText()) * -1));
                }
            }
            case "," -> calculator.displayLabel.setText(calculator.displayLabel.getText() + ",");
        }

        try {
            index = Integer.parseInt(digitText);
        } catch(NumberFormatException e1) { return; }
        if(index == 0 && calculator.displayLabel.getText().equals("0")) return;

        if(calculator.setClear) {
            calculator.displayLabel.setText("" + index);
            calculator.setClear = false;
        } else {
            calculator.displayLabel.setText(calculator.displayLabel.getText() + index);
        }
    }

    /**
     * this function updates the sign list based on the current text.
     * @param currentText the input string to process.
     * @return a StringBuilder containing the updated sign list.
     */
    private static StringBuilder updateSignList(String currentText) {
        StringBuilder holder = new StringBuilder(), result = new StringBuilder();
        int signCount = 0;

        for(int i = currentText.length() - 1; i >= 0; i--) {
            char currentChar = currentText.charAt(i);

            if(currentChar == '-') {
                signCount++;
                break;
            }
            if(currentChar == ',') {
                holder.insert(0,'-');
                break;
            }

            holder.insert(0, currentChar);
            signCount++;
        }

        for(int i = 0; i < currentText.length() - signCount; i++) {
            result.append(currentText.charAt(i));
        }
        result.append(holder);

        return result;
    }
}
