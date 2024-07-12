package oop.bank.system.classes;

/**
 * Represents a savings account in the banking system.
 * This account type accrues interest and has specific annual fees and minimum balance requirements.
 */
public class SavingsAccount extends BankAccount {
    private double interestRate; // Interest rate for the savings account

    /**
     * Constructs a SavingsAccount with specified parameters.
     * @param accountNumber Unique identifier for the account.
     * @param accountHolder Name of the account holder.
     * @param interestRate Annual interest rate percentage.
     * @param annualFee Annual maintenance fee for the account.
     */
    public SavingsAccount(long accountNumber, String accountHolder, double interestRate, double annualFee) {
        super(accountNumber, accountHolder, "Savings", annualFee);
        this.interestRate = interestRate;
    }

    /**
     * Applies interest to the balance based on the current interest rate.
     * Interest is added to the balance and a notification is sent.
     */
    public void addInterest() {
        double interest = getBalance() * (interestRate / 100);
        deposit(interest);
        notifyAccountActivity("Interest added: " + interest);
    }

    /**
     * Provides a summary of the account's details.
     */
    @Override
    public void accountSummary() {
        System.out.println("Account Type: " + getAccountType() +
                "\nAccount Number: " + getAccountNumber() +
                "\nAccount Holder: " + getAccountHolder() +
                "\nBalance: " + getBalance() +
                "\nInterest Rate: " + interestRate + "%");
    }

    /**
     * Notifies the account holder of any account activity via email.
     * @param message The message detailing the account activity.
     */
    @Override
    public void notifyAccountActivity(String message) {
        System.out.println("Email Notification: " + message);
    }

    /**
     * Calculates and returns the annual fees for the savings account.
     * @return Annual fees as defined in the superclass.
     */
    @Override
    public double calculateAnnualFees() {
        return getAnnualFees();
    }

    /**
     * Defines the minimum balance required to maintain the savings account.
     * @return The required minimum balance.
     */
    @Override
    public double minimumBalanceRequired() {
        return 500; // Specific to savings accounts
    }
}
