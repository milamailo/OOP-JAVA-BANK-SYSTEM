package oop.bank.system.classes;

/**
 * Abstract base class for different types of bank accounts.
 * Provides a framework for account operations and properties that are common across all types of accounts.
 */
public abstract class BankAccount {
    // Unique identifier for the bank account
    private long accountNumber;

    // Name of the account holder
    private String accountHolder;

    // Type of the bank account (e.g., "Savings", "Checking")
    private String accountType;

    // Current balance of the account
    protected double balance;

    // Annual fees applicable to the account
    protected double annualFees;

    /**
     * Constructor for bank account with initial balance.
     *
     * @param accountNumber the unique identifier for the account
     * @param accountHolder the name of the individual or entity that holds the account
     * @param accountType the type of account
     * @param annualFees the annual maintenance fees for the account
     * @param balance the initial balance of the account
     */
    public BankAccount(long accountNumber, String accountHolder, String accountType, double annualFees, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.annualFees = annualFees;
        this.balance = balance;
    }

    /**
     * Constructor for bank account without initial balance.
     *
     * @param accountNumber the unique identifier for the account
     * @param accountHolder the name of the individual or entity that holds the account
     * @param accountType the type of account
     * @param annualFees the annual maintenance fees for the account
     */
    public BankAccount(long accountNumber, String accountHolder, String accountType, double annualFees) {
        this(accountNumber, accountHolder, accountType, annualFees, 0.0); // Call the main constructor with a zero balance
    }

    /**
     * Provides an account summary. Implementations will differ based on account type.
     */
    public abstract void accountSummary();

    /**
     * Notifies the account holder of account activity.
     *
     * @param message the notification message to be sent to the account holder
     */
    public abstract void notifyAccountActivity(String message);

    /**
     * Calculates and returns the annual fees for the account.
     *
     * @return the annual fees
     */
    public abstract double calculateAnnualFees();

    /**
     * Determines the minimum balance required for the account.
     *
     * @return the minimum balance required
     */
    public abstract double minimumBalanceRequired();

    /**
     * Deposits a specified amount into the account.
     *
     * @param amount the amount to deposit
     * @return true if the deposit was successful, false otherwise (e.g., negative amounts)
     */
    public boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            notifyAccountActivity("Deposited: " + amount + "; New Balance: " + this.balance);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Withdraws a specified amount from the account.
     *
     * @param amount the amount to withdraw
     * @return true if the withdrawal was successful, false if it fails due to insufficient balance or invalid amount
     * @throws BankSystemException if the withdrawal fails due to an invalid condition
     */
    public boolean withdraw(double amount) throws BankSystemException, Exception {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            notifyAccountActivity("Withdrawn: " + amount + "; New Balance: " + this.balance);
            return true;
        } else {
            throw new BankSystemException("Insufficient balance or invalid amount.");
        }
    }

    // Getter methods for account properties

    public double getBalance() {
        return this.balance;
    }

    public long getAccountNumber() {
        return this.accountNumber;
    }

    public String getAccountHolder() {
        return this.accountHolder;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public double getAnnualFees() {
        return this.annualFees;
    }
}
