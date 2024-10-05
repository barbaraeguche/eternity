import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import button.Button;

public class Calculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("INFINITY CALCULATOR");

        //declare all buttons
        Button one = new Button("1", 4, 10);
        Button two = new Button("2", 50, 10);

        //add buttons to arraylist
        ArrayList<Button> buttonsArray = new ArrayList<>(Arrays.asList(
                one, two
        ));

        //add buttons to frame
        Calculator.addButtonsToFrame(frame, buttonsArray);
        //set display attributes
        Calculator.setFrameAttributes(frame);
    }

    //adding buttons to frame
    public static void addButtonsToFrame(JFrame mainFrame, ArrayList<Button> allButtons) {
        for(Button button : allButtons) {
            mainFrame.add(button.getButton());
        }
    }

    //set display attributes for the frame
    public static void setFrameAttributes(JFrame mainFrame) {
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
    }
}