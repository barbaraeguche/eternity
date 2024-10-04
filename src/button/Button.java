package button;
import javax.swing.JButton;
import javax.swing.Action;

public class Button {
    private final int defaultSize = 60;
    private final JButton button;

    //parameterized constructor for default buttons
    public Button(String btn, int xAxis, int yAxis) {
        button = new JButton(btn);
        button.setBounds(xAxis, yAxis, defaultSize, defaultSize);
    }
    //parameterized constructor for == or 0 button with custom size
    public Button(String btn, int xAxis, int yAxis, int givenSize) {
        button = new JButton(btn);

        //adjust button based on label
        if(btn.equals("=")) {
            button.setBounds(xAxis, yAxis, defaultSize, givenSize);
        } else {
            button.setBounds(xAxis, yAxis, givenSize, defaultSize);
        }
    }

    //customize button color, and others
    private JButton customSizeButton() {
        return button.
    }

    //method to return each button
    public JButton getButton() {
        return customSizeButton();
    }
}