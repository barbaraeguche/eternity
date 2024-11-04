/**
 * this is the TooManyArgumentsException class.
 */
class TooManyArgumentsException extends Exception {

    /**
     * default constructor for TooManyArgumentsException.
     */
    public TooManyArgumentsException() {}

    /**
     * parameterized constructor for TooManyArgumentsException.
     * @param message the error message to be displayed.
     */
    public TooManyArgumentsException(String message) {
        super(message);
    }
}