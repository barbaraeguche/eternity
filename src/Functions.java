
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

        for(double i: initialDataSet) {
            double[] power = { i - dataSetMean, 2 };
            sumOfDeviations += xPowerY(power);
        }
        return Math.sqrt(sumOfDeviations / ((double)initialDataSet.length - 1));
    }
    
    /**
     * this method calculates the x^y of a given input.
     * @param initialDataSet an array of doubles representing the dataset.
     * @return the calculated x^y as a double.
     */
    public double xPowerY(double[] initialDataSet) {
        double x = initialDataSet[0], y = initialDataSet[1];
        boolean isNegativeExponent = y < 0;

        if(x == 0) {
            if(y > 0) return 0;
            else throw new ArithmeticException("Error: undefined.");
        }
        if(y % 1 == 0) return integerPower(x, (int)y);

        if(isNegativeExponent) y = -y;
        if(x < 0 && y % 1 != 0) throw new ArithmeticException("Error: cannot take the (x)root of a negative number.");
        
        double result = exponential(y * ln(x));
        return isNegativeExponent ? 1.0 / result : result;
    }
    
    /**
     * this method calculates the a(b^x) of a given input.
     * @param initialDataSet an array of doubles representing the dataset.
     * @return the calculated a(b^x) as a double.
     */
    public double abPowerX(double[] initialDataSet) {
        double a = initialDataSet[0], b = initialDataSet[1], x = initialDataSet[2];
        double[] power = { b, x };
        
        return a * xPowerY(power);
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
     * @param isDegreeMode booleans indicating whether the result should be in degrees(true) or radians(false).
     * @return the arc-cosine of the input in radians or degrees if it is between -1 and 1.
     * @throws IllegalArgumentException if the value is not within the required domain of [-1 , 1]
     */
    public double arcCosX(double[] initialDataSet, boolean isDegreeMode) throws IllegalArgumentException {
        double x = initialDataSet[0];
        //check that input is within the domain of arccos
        if(x < -1.0 || x > 1.0) throw new IllegalArgumentException("Input must be in the range [-1, 1]");

        //initialize variables
        double sum = 0; int maxIterations = 20; //higher value means more accuracy but slower computation
        //using taylor series approximation - calculate the term: (2k)! / (4^k * (k!)^2 * (2k + 1)) * x^(2k + 1)
        for(int k = 0; k < maxIterations; k++) {
            double[] power1 = { 4, k }, power2 = { factorial(k), 2 }, power3 = { x, 2 * k + 1 };
            sum += factorial(2 * k) / (xPowerY(power1) * xPowerY(power2) * (2 * k + 1)) * xPowerY(power3);
        }

        double answer = Math.PI / 2 - sum;
        if(isDegreeMode) { answer = (answer * 180) / Math.PI; }

        return answer;
    }

    /**
     * this method calculates the logarithm of a given input with respect to base 10.
     * @param x is a double representing the argument of log.
     * @return the logarithm of a value with respect to a specified base.
     */
    public double logXBase10(double x) {
        if(x <= 0) throw new ArithmeticException("Error: Number is lesser than 1");
        else if(x == 1) return 0;

        return ln(x) / ln(10);
    }

    /**
     * this method calculates the logarithm of a given input with respect to a given base.
     * @param initialDataSet an array of doubles representing the dataset.
     * @return the logarithm of a value with respect to a specified base.
     */
    public double logXBaseB(double[] initialDataSet) {
        if(initialDataSet[0] <= 0) throw new ArithmeticException("Error: Number is lesser than 1");
        return ln(initialDataSet[0]) / ln(initialDataSet[1]);
    }

    /**
     * this method calculates the hyperbolic sine (sinh) of a given input using the taylor series approximation.
     * @param initialDataSet an array of doubles representing the dataset.
     * @return the hyperbolic sine of the input in radians.
     */
    public double sinHX(double[] initialDataSet) {
        double x = initialDataSet[0], result = 0.0;
        double xPower = x; //initialize xPower to passed argument for the first term (numerators of fraction summation)

        //taylor series loop to go through each term and add it to the total
        for(int i = 1; i <= 15; i += 2) {
            result += xPower / factorial(i); //calculate the current term: (x^(2i+1)) / i!
            xPower *= x * x; //update xPower to x^(i+2) for the next numerator value in the series
        }
        return result;
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
    /**
     * this method calculates the logarithm of a given input with respect to base e. this is an approximation based on the Taylor Series on natural logarithm.
     * @param x is a double representing the argument of log.
     * @return the natural logarithm of a value with respect to e as its base.
     */
    public double ln(double x) {
        if(x <= 0) throw new IllegalArgumentException("Error: Number is lesser than 1");

        //transform x to be close to 1 for faster convergence
        int k = 0;
        while(x > 2) {
            x /= 2;
            k++;
        }

        x = (x - 1) / (x + 1);
        double result = 0.0, term = x, xSquared = x * x;

        //taylor series sum
        for(int i = 1; i <= 100; i += 2) {
            result += term / i;
            term *= xSquared;
        }

        //multiply by 2 as per taylor series formula
        result *= 2;
        //adjust for factors of 2 (ln(2) by adding the multiplication of k (used in faster convergence above by 0.69314718056)
        result += k * 0.69314718056;
        
        return result;
    }
    /**
     * this function calculates the power of a base raised to an exponent.
     * @param base the base value.
     * @param exponent the exponent value, can be negative.
     * @return the result of base raised to the exponent.
     */
    private static double integerPower(double base, double exponent) { // The function to calculate integer powers by multiplying 'base' repeatedly
        double result = 1.0;
        boolean isNegativeExponent = exponent < 0;
        
        if(isNegativeExponent) exponent = -exponent;
            
        for(double i = 0; i < exponent; i++) {
            result *= base;
        }
        return isNegativeExponent? (1.0 / result) : result;
    }
    /**
     * this function approximates the exponential function e^x using the taylor series expansion.
     * @param x the exponent to which e is raised.
     * @return the approximate value of e^x.
     */
    private static double exponential(double x) {
        double result = 1, term = 1;
            
        for(double i = 1; i <= 20; i++) {
            term *= x / i;
            result += term;
        }
        return result;
    }
}
