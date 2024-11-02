/*Things to fix:
 * When subtracting and it leads to a negative, it will show and keep a positive.*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame {
	public boolean setClear = true;
	//This is the basic calculator Texts. Which will be implemented at Runtime and be used to compare string values. 
	private String digitButtonText[] = { "7", "8", "9", "4", "5", "6", "1", "2", "3", "0", "+/-", "." };
	private String operatorButtonText[] = { "/", "sqrt", "X", "-","1/X", "+", "=" };
	private String specialButtonText[] = { "Back", "C", "CE" };
	private String transButtonText[] = {"log_a(X)", "ab^x"};//Put your transcendental function here.
	Functions functions = new Functions();

	//Calculators Width and Height in units on the screen.
	final int FRAME_WIDTH = 325, FRAME_HEIGHT = 450; //frameheight_original val is 325
	//Button measurements on the screen. **Trans functions will need different measurements**
	final int HEIGHT = 30, WIDTH = 30, H_SPACE = 10, V_SPACE = 10;
	final int TOPX = 30, TOPY = 50;

	Label displayLabel = new Label("0", Label.RIGHT);
	Label memLabel = new Label(" ", Label.RIGHT);

	DigitButton digitButtons[] = new DigitButton[digitButtonText.length];
	OperatorButton operatorButtons[] = new OperatorButton[operatorButtonText.length];
	SpecialButton specialButtons[] = new SpecialButton[specialButtonText.length];
	TransButton transButtons[] = new TransButton[transButtonText.length];

	public double number;

	public char op;

	public Calculator(String frameText) {
		super(frameText);
		int tempX = TOPX, tempY = TOPY;
		displayLabel.setBounds(tempX, tempY, 240, HEIGHT);
		displayLabel.setBackground(Color.BLACK);
		displayLabel.setForeground(Color.WHITE);
		add(displayLabel);

		// set Co-ordinates for Digit Buttons
		int digitX = TOPX + WIDTH + H_SPACE;
		int digitY = TOPY + 2 * (HEIGHT + V_SPACE);
		tempX = digitX;
		tempY = digitY;
		for (int i = 0; i < digitButtons.length; i++) {
			digitButtons[i] = new DigitButton(tempX, tempY, WIDTH, HEIGHT, digitButtonText[i], this);
			digitButtons[i].setForeground(Color.BLACK);
			tempX += WIDTH + H_SPACE;
			if ((i + 1) % 3 == 0) {
				tempX = digitX;
				tempY += HEIGHT + V_SPACE;  //This goes from +2*(h+v) to +5*(h+v) therefore special buttons need to be +6*(h+v) onwards. Comment by Dennis.
			}
		}

		// set Co-ordinates for basic Operator buttons
		int opsX = digitX + 2 * (WIDTH + H_SPACE) + H_SPACE;
		int opsY = digitY;
		tempX = opsX;
		tempY = opsY;
		for (int i = 0; i < operatorButtonText.length; i++) {
			tempX += WIDTH + H_SPACE;
			operatorButtons[i] = new OperatorButton(tempX, tempY, WIDTH, HEIGHT, operatorButtonText[i], this);
			operatorButtons[i].setForeground(Color.BLACK);
			;
			if ((i + 1) % 2 == 0) {
				tempX = opsX;
				tempY += HEIGHT + V_SPACE;
			}
		}
		
		//set Co-ordinates for Special Buttons  
		tempX=TOPX+1*(WIDTH+H_SPACE); tempY=TOPY+1*(HEIGHT+V_SPACE);  
		for(int i=0;i<specialButtonText.length;i++)  
		{  
		specialButtons[i]=new SpecialButton(tempX,tempY,WIDTH*2,HEIGHT,specialButtonText[i], this);  
		specialButtons[i].setForeground(Color.BLACK);  
		tempX=tempX+2*WIDTH+H_SPACE;  
		}


		//set Co-ordinates for Dennis' version of special buttons
		tempX=TOPX +1*(WIDTH+H_SPACE); // +1*(w+h) is the indentation from the left side??? not sure
		tempY=TOPY+6*(HEIGHT+V_SPACE); //Since the last set of buttons are at Y-coor  +5*(h+v) this must start at 6*(h+v)
		for(int i=0;i< transButtonText.length;i++)
		{
			transButtons[i]=new TransButton(tempX,tempY,WIDTH*2,HEIGHT,transButtonText[i], this);
			transButtons[i].setForeground(Color.BLACK);
			tempX=tempX+2*WIDTH+H_SPACE; //This is where you adjsut the width of the button and the hspace i think...
			//Need to check if more than 3 buttons already if yes do this adjust tempY
			if ((i + 1) % 3 == 0) {
				tempX = TOPX +1*(WIDTH+H_SPACE); // make sure first button of next row is also indented.
				tempY += HEIGHT + V_SPACE; // make sure you mov eto next row
			}
		}

		setLayout(null);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setVisible(true);
	}

	public static String getFormattedText(double temp) {
		// TODO Auto-generated method stub
		String resText = "" + temp;
		if (resText.lastIndexOf(".0") > 0) {
			resText = resText.substring(0, resText.length() - 2);
		}
		return resText;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator Eternity = new Calculator("ETERNITY CALCULATOR");
		Eternity.setVisible(true);
	}

}

class DigitButton extends JButton implements ActionListener {
	Calculator cl;

	DigitButton(int x, int y, int width, int height, String cap, Calculator cl) {
		super(cap);
		setBounds(x, y, width, height);
		setMargin(new Insets(0, 0, 0, 0));
		this.cl = cl;
		this.cl.add(this);
		addActionListener(this);
	}

	static boolean isInString(String s, char ch) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ch)
				return true;
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Get Label of button and append it to the displayLabel of the Calculator.
		String tempText = ((DigitButton) e.getSource()).getText();

		if (tempText.equals(".")) {
			if (cl.setClear) {
				cl.displayLabel.setText("0.");
				cl.setClear = false;
			} else if (!isInString(cl.displayLabel.getText(), '.')) {
				cl.displayLabel.setText(cl.displayLabel.getText() + ".");
			}
			return;
		}
		else if(tempText.equals("+/-")) {
			cl.displayLabel.setText(Double.toString((Double.parseDouble(cl.displayLabel.getText())*-1)));
		}
		int index = 0;
		try {
			index = Integer.parseInt(tempText);
		} catch (NumberFormatException e1) {
			return;
		}

		if (index == 0 && cl.displayLabel.getText().equals("0"))
			return;

		if (cl.setClear) {
			cl.displayLabel.setText("" + index);
			cl.setClear = false;
		} else {
			cl.displayLabel.setText(cl.displayLabel.getText() + index);
		}
	}
}

class OperatorButton extends JButton implements ActionListener {

	Calculator cl;

	public OperatorButton(int x, int y, int width, int height, String label, Calculator cl) {
		super(label);
		setBounds(x, y, width, height);
		setMargin(new Insets(0, 0, 0, 0));
		this.cl = cl;
		this.cl.add(this);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String opText = ((OperatorButton) e.getSource()).getText();
		cl.setClear = true;
		double temp = Double.parseDouble(cl.displayLabel.getText());
		if (opText.equals("1/X")) {
			try {
				double tempd = 1 / (double) temp;
				cl.displayLabel.setText(Calculator.getFormattedText(tempd));
			} catch (ArithmeticException exc) {
				cl.displayLabel.setText("Error: Dividing by Zero");
			}
			return;
		}
		if (opText.equals("sqrt")) {
			try {
				double tempd = Math.sqrt(temp);
				cl.displayLabel.setText(Calculator.getFormattedText(tempd));
			} catch (ArithmeticException exc) {
				cl.displayLabel.setText("Error: Dividing by Zero");
			}
			return;
		}
		if (!opText.equals("=")) {
			cl.number = temp;
			cl.op = opText.charAt(0);
			return;
		}
		switch (cl.op) {
		case '+':
			temp = cl.number + temp;
			System.out.println(temp);
			break;
		case '-':
			System.out.println(temp+"-"+-cl.number);
			temp = cl.number - temp;
			System.out.println(temp);
			break;
		case 'X':
			temp *= cl.number;
			System.out.println(temp);
			break;
		case '%':
			try {
				temp = cl.number % temp;
			} catch (ArithmeticException exc) {
				cl.displayLabel.setText("MATH ERROR");
				return;
			}
			break;
		case '/':
			try {
				temp = cl.number / temp;
				System.out.println(temp);
			} catch (ArithmeticException exc) {
				cl.displayLabel.setText("ERROR: DIVIDE BY ZERO");
				return;
			}
			break;
		}
		System.out.println(temp);
		cl.displayLabel.setText(Calculator.getFormattedText(temp));
		cl.number = temp;
	}

}

class SpecialButton extends JButton implements ActionListener{
	Calculator cl;
	public SpecialButton(int x,int y,int width,int height,String label,Calculator cl) {
		super(label);
		setBounds(x,y,width,height);
		setMargin(new Insets(0, 0, 0, 0));
		this.cl=cl;
		this.cl.add(this);
		addActionListener(this);
	}
	
	static String backSpace(String s) {
		String Res="";
		for(int i=0;i<s.length()-1;i++)Res+=s.charAt(i);
		return Res;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String opText = ((SpecialButton)e.getSource()).getText();
		if(opText.equals("Back")) {
			String tempText=backSpace(cl.displayLabel.getText());
			if(tempText.equals("")) {
				cl.displayLabel.setText("0");	
			}
			else {
				cl.displayLabel.setText(tempText);
			}
			return;
		}
		if(opText.equals("C")) {
			cl.number=0.0;cl.op=' ';
			cl.memLabel.setText(" ");
			return;
		}
		cl.displayLabel.setText("0");cl.setClear=true;
	}
}

class TransButton extends JButton implements ActionListener{
	Calculator cl;
	public TransButton(int x,int y,int width,int height,String label,Calculator cl) {
		super(label);
		setBounds(x,y,width,height);
		setMargin(new Insets(0,0,0,0));
		this.cl=cl;
		this.cl.add(this);
		addActionListener(this);
	}
	//Put all trancendental function implementation here **PLEASE SHOW YOUR IMPLEMENTATION WITH YOUR REVIEWER BEFORE MAKING A PULL REQUEST**:
	@Override
	public void actionPerformed(ActionEvent e) {
		String transText = ((TransButton) e.getSource()).getText();
		double temp = Double.parseDouble(cl.displayLabel.getText());
		if (transText.equals("arccos")) { // ONLY FOR ARCCOS FUNCTION.
			try {
				double result = cl.functions.ArcCosX(temp);
				cl.displayLabel.setText(Calculator.getFormattedText(result));
			} catch (IllegalArgumentException exc) {
				cl.displayLabel.setText(exc.getMessage());
			}
		}
		
		/** YOU CAN ADD YOUR TRANSECTIONAL FUNCTION HERE IN AN ELSE IF BLOCK. */

	}
}