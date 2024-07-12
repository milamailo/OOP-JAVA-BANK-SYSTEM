package oop.bank.system.classes;

/**
 * Custom exception class for handling specific errors within the banking system.
 * This class extends the standard Java Exception class, allowing for more specific error handling.
 */
public class BankSystemException extends Exception {
    /**
     * Constructor for creating a new BankSystemException with a message.
     * @param msg The error message describing what caused the exception.
     */
    public BankSystemException(String msg) {
        super(msg);
    }
}
