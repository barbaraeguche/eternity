/**
 * this is the TooFewArgumentsException class.
 */
class TooFewArgumentsException extends Exception {

    /**
     * default constructor for TooManyArgumentsException.
     */
    public TooFewArgumentsException() {}

    /**
     * parameterized constructor for TooManyArgumentsException.
     * @param message the error message to be displayed.
     */
    public TooFewArgumentsException(String message) {
        super(message);
    }
}
