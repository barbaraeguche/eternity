import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * this is the SpecialButton class.
 */
class SpecialButton extends JButton implements ActionListener{
    Calculator calculator;

    /**
     * parameterized constructor for SpecialButton.
     * @param x the x-coordinate of the button's position on the screen.
     * @param y the y-coordinate of the button's position on the screen.
     * @param width the width of the button.
     * @param height the height of the button.
     * @param label the label text displayed on the button.
     * @param calc the Calculator instance with which this button will be associated.
     */
    public SpecialButton(int x, int y, int width, int height, String label, Calculator calc) {
        super(label);
        setBounds(x, y, width, height);
        setMargin(new Insets(0, 0, 0, 0));
        this.calculator = calc;
        this.calculator.add(this);
        addActionListener(this);
    }
    /**
     * this method removes the last character from the given string.
     * @param string the original string from which the last character will be removed.
     * @return a new string without the last character; returns an empty string if the input is empty.
     */
    static String backSpace(String string) { return string.substring(0, string.length() - 1); }

    /**
     * this method processes an action event triggered by a user interface component.
     * @param e the event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String specialText = ((SpecialButton) e.getSource()).getText();

        switch(specialText) {
            case "Back":
                String tempText = backSpace(calculator.displayLabel.getText());

                if(tempText.isEmpty()) { calculator.displayLabel.setText("0"); }
                else { calculator.displayLabel.setText(tempText); }
                return;
            case "C":
                calculator.number = 0.0;
                calculator.op = ' ';
                calculator.memLabel.setText(" ");
                return;
            case "CE":
                calculator.displayLabel.setText("0");
                calculator.setClear = true;
        }
    }
}
