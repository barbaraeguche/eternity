
/**
 * this is the Functions class. it is where we declare all our individually assigned transcendal functions.
 */
public class Functions {
    // no need for default constructor as java already defines it if not included

    /**
     * this method calculates the standard deviation of a given dataset.
     * @param initialDataSet an array of doubles representing the dataset.
     * @return the standard deviation as a double.
     */
    public double standardDeviation(double[] initialDataSet) {
        double dataSetMean = calculateMean(initialDataSet);
        double sumOfDeviations = 0;

        for(double i: initialDataSet) { sumOfDeviations += Math.pow(i - dataSetMean, 2); }
        return Math.sqrt(sumOfDeviations / ((double)initialDataSet.length - 1));
    }
     /**
     * @param x
     * @param y
     * @return
=======

    /**
     * this method calculates the x^y of a given input.
     * @param initialDataSet an array of doubles representing the dataset.
     * @return the calculated x^y as a double.
>>>>>>> 1d030f8380e7f59b7fe2edf0218a8bdbd41e456f
     */
    public double xPowerY(double[] initialDataSet) {
        double x = initialDataSet[0], y = initialDataSet[1];

        return 1.0;
    }

    /**
     * this method calculates the a(b^x) of a given input.
     * @param initialDataSet an array of doubles representing the dataset.
     * @return the calculated a(b^x) as a double.
     */
    public double abPowerX(double[] initialDataSet) {
        double a = initialDataSet[0], b = initialDataSet[1], x = initialDataSet[2];

        //TODO update Math.pow after x^y is done
        return a * Math.pow(b, x);
    }

    /**
     * this method calculates the mean absolute deviation of a given dataset.
     * @param initialDataSet an array of doubles representing the dataset.
     * @return the mean absolute deviation as a double.
     */
    public double meanAbsoluteDeviation(double[] initialDataSet) {
        double dataSetMean = calculateMean(initialDataSet);
        double sumOfDeviations = 0;

        for(double i: initialDataSet) { sumOfDeviations += Math.abs(i - dataSetMean); }
        return sumOfDeviations / (double)initialDataSet.length;
    }

    /**
     * this method calculates the arc-cosine of a given input.
     * @param initialDataSet the input for which we want to calculate the arc-cosine.
     * @return the arc-cosine of the input in radians if it is between -1 and 1. else, an IllegalArgumentException is thrown with a message indicating the error.
     * @throws IllegalArgumentException if the value is not within the required domain of [-1 , 1]
     */
    public double arcCosX(double[] initialDataSet) throws IllegalArgumentException {
        double x = initialDataSet[0];
        //check that input is within the domain of arccos
        if(x < -1.0 || x > 1.0) throw new IllegalArgumentException("Input must be in the range [-1, 1]");
        //initialize variables
        double sum = 0; int maxIterations = 20; //higher value means more accuracy but slower computation
        //using taylor series approximation - calculate the term: (2k)! / (4^k * (k!)^2 * (2k + 1)) * x^(2k + 1)
        for(int k = 0; k < maxIterations; k++) {
            sum += factorial(2 * k) / (Math.pow(4, k) * Math.pow(factorial(k), 2) * (2 * k + 1)) * Math.pow(x, 2 * k + 1);
        }
        return Math.PI / 2 - sum;
    }
    /**
     * this method calculates the logarithm of a given input with with respect to base 10.
     * @param x is a double representing the argument of log.
     * @return the logarithm of a value with respect to a specified base.
     */
    public double logXBase10(double x) {
		double result = 0;
			if(x<=0)throw new ArithmeticException("Error: Math Error");
			
		result = ln(x)/ln(10);
    	return result;
    }

    /**
     * this method calculates the logarithm of a given input with respect to a given base.
     * @param initialDataSet an array of doubles representing the dataset.
     * @return the logarithm of a value with respect to a specified base.
     */
    public double logXBaseB(double[] initialDataSet) {
    	double result = ln(initialDataSet[0])/ln(initialDataSet[1]);
    	
    	return result;
    }
    /**
     * this method calculates the logarithm of a given input with with respect to base e. This is an approximation based on the Taylor Series on natural logarithm.
     * @param x is a double representing the argument of log.
     * @return the natural logarithm of a value with respect to e as its base.
     */
 // Method to calculate ln(x) using a Taylor series
    public static double ln(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("x must be positive.");
        }
        
        // Transform x to be close to 1 for faster convergence
        int k = 0;
        while (x > 2) {
            x /= 2;
            k++;
        }
        
        x = (x - 1) / (x + 1);
        double result = 0.0;
        double term = x;
        double xSquared = x * x;

        for (int i = 1; i <= 100; i += 2) { // Taylor series sum
            result += term / i;
            term *= xSquared;
        }

        result *= 2; // Multiply by 2 as per Taylor series formula
        result += k * 0.69314718056; // Adjust for factors of 2 (ln(2) ≈ 0.693147)

        return result;
    }

    /**
     * this method calculates the hyperbolic sine of a given input.
     * @param initialDataSet an array of doubles representing the dataset.
     * @return the hyperbolic sine of the input in radians.
     */
    public double sinHX(double[] initialDataSet) {
        double x = initialDataSet[0];

        return 1.0;
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
    /**
     * this function calculates the mean of a given dataset.
     * @param dataSet an array of doubles representing the dataset.
     * @return the mean of the given data set.
     */
    private static double calculateMean(double[] dataSet) {
        double sum = 0;
        for(double i: dataSet) { sum += i; }
        return sum / (double)dataSet.length;
    }
}
