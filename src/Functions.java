
/**
 * this is the Functions class. it is where we declare all our individually assigned transcendal functions.
 */
public class Functions {
    // no need for default constructor as java already defines it if not included

    /**
     *
     * @return
     */
    public int standardDeviation() {
        return 1;
    }
     /**
     * @param x
     * @param y
     * @return
     */
    public int xPowerY(float x, int y) {
        return 1;
    }

    /**
     *
     * @param a
     * @param b
     * @param x
     * @return
     */
    public int abPowerX(int a, int b, int x) {
        return 1;
    }

    /**
     *
     * @return
     */
    public int meanAbsoluteDeviation() {
        return 1;
    }

    /**
     * this function calculates the arc-cosine of a given input.
     * @param x the input for which we want to calculate the arc-cosine.
     * @return the arc-cosine of the input in radians if it is between -1 and 1. else, an IllegalArgumentException is thrown with a message indicating the error.
     * @throws IllegalArgumentException if the value is not within the required domain of [-1 , 1]
     */
    public double arcCosX(double x) throws IllegalArgumentException {
        if(x < -1.0 || x > 1.0) throw new IllegalArgumentException("Input must be in the range [-1, 1]"); //check that input is within the domain of arccos

        //initialize variables
        double sum = 0; int maxIterations = 20; //higher value means more accuracy but slower computation

        //using taylor series approximation - calculate the term: (2k)! / (4^k * (k!)^2 * (2k + 1)) * x^(2k + 1)
        for(int k = 0; k < maxIterations; k++) {
            sum += factorial(2 * k) / (Math.pow(4, k) * Math.pow(factorial(k), 2) * (2 * k + 1)) * Math.pow(x, 2 * k + 1);
        }
        return Math.PI / 2 - sum;
    }
    
    public double logXBaseB(double x) {
		double result = 0;
			if(x<=0)throw new ArithmeticException("Error: Math Error");
			
		result = ln(x)/ln(10);
    	return result;
    }

    /**
     *
     * @param x
     * @param b
     * @return
     */
    public double logXBaseB(float x, int b) {
    	double result = ln(x)/ln(b);
    	
    	return result;
    }
    
    public static double ln(double x) {
    	double result=0;
    	for(int i=0;i<=80;i++) {
    		result+=(Math.pow(-1, i)*Math.pow(x, i+1))/(i+1);
    	}
    	return result;
    }

    /**
     *
     * @param x
     * @return
     */
    public int sinHX(int x) {
        return 1;
    }


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~ HELPER FUNCTIONS ~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    /**
     * this function calculates the factorial of a given integer using a loop.
     * note: cannot be tried with negative numbers.
     * @param n the integer for which we want to calculate the factorial.
     * @return the factorial of the input integer.
     */
    private static long factorial(int n) {
        long result = 1;

        if(n == 0 || n == 1) return 1;
        for(int i = 2; i <= n; i++) { result *= i; }
        return result;
    }
}