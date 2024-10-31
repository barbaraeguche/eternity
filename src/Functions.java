
public class Functions {

    // no need for default constructor as java already defines it if not included

    public int StdDeviation() {
        return 1;
    }

    public int XPowerY(float x, int y) {
        return 1;
    }

    public int ABPowerX(int a, int b, int x) {
        return 1;
    }

    public int MeanAbsDeviation() {
        return 1;
    }

    /**
     * The function calculates the factorial of a given integer using a loop.
     * 
     * @param n The `factorial` method you provided calculates the factorial of a given integer `n`.
     * The factorial of a non-negative integer `n`, denoted as `n!`, is the product of all positive
     * integers less than or equal to `n`. 
     * 
     * !!! Do not try it with negative numbers.
     * 
     * @return The factorial of the input integer `n` is being returned by the `factorial` method.
     */
    private static long factorial(int n) {
        if (n == 0 || n == 1)
            return 1;
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * The function `ArcCosX` calculates the arccosine of a given input within the
     * range of -1 to 1 in Java.
     * 
     * @param x The `ArcCosX` method calculates the arccosine of a given input `x`
     *          in radians, but it
     *          requires `x` to be between -1 and 1. If `x` is not within this
     *          range, it will throw an
     *          `IllegalArgumentException`.
     * 
     * @return The method `ArcCosX` returns the arccosine of the input `x` in
     *         radians if `x` is between
     *         -1 and 1. If the input `x` is not within this range, an
     *         `IllegalArgumentException` is thrown
     *         with the message "Input for arccos must be between -1 and 1".
     * 
     * @throws IllegalArgumentException if the value is not in range [-1 , 1]
     */
    public double ArcCosX(double x) throws IllegalArgumentException {
        // Check that input is within the domain of arccos
        if (x < -1.0 || x > 1.0) {
            throw new IllegalArgumentException("Input must be in the range [-1, 1]");
        }

        // Initialize variables
        double sum = 0;
        double term = x; // Current term in the series
        int maxIterations = 20; // Higher value means more accuracy but slower computation

        // Taylor series approximation
        for (int k = 0; k < maxIterations; k++) {
            // Calculate the term: (2k)! / (4^k * (k!)^2 * (2k + 1)) * x^(2k + 1)
            term = factorial(2 * k) / (Math.pow(4, k) * Math.pow(factorial(k), 2) * (2 * k + 1))
                    * Math.pow(x, 2 * k + 1);
            sum += term;
        }

        return Math.PI / 2 - sum;
    }

    public int LogXBaseB(float x, int b) {
        return 1;
    }

    public int HypSineX(int x) {
        return 1;
    }
}