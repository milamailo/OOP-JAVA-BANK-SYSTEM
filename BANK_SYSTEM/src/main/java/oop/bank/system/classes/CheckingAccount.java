package oop.bank.system.classes;

/**
 * Represents a checking account type within the bank system.
 * This account type supports overdrafts and provides basic transaction notifications.
 */
public class CheckingAccount extends BankAccount implements IArchive {
    private double overdraftLimit;  // The limit to which the account can be overdrawn

    /**
     * Constructs a CheckingAccount with an overdraft limit and an initial balance.
     * @param accountNumber The unique identifier for the account.
     * @param accountHolder The name of the account holder.
     * @param overdraftLimit The maximum amount the account can be overdrawn.
     * @param annualFee The annual fee for the account.
     * @param balance The starting balance of the account.
     */
    public CheckingAccount(long accountNumber, String accountHolder, double overdraftLimit, double annualFee, double balance) {
        super(accountNumber, accountHolder, "Checking", annualFee, balance);
        this.overdraftLimit = overdraftLimit;
    }

    /**
     * Constructs a CheckingAccount with an overdraft limit without an initial balance.
     * @param accountNumber The unique identifier for the account.
     * @param accountHolder The name of the account holder.
     * @param overdraftLimit The maximum amount the account can be overdrawn.
     * @param annualFee The annual fee for the account.
     */
    public CheckingAccount(long accountNumber, String accountHolder, double overdraftLimit, double annualFee) {
        this(accountNumber, accountHolder, overdraftLimit, annualFee, 0);
    }

    /**
     * Withdraws a specified amount from the account. Accounts can go into overdraft up to the overdraft limit.
     * @param amount The amount to withdraw.
     * @return True if the transaction was successful, false otherwise.
     * @throws BankSystemException If the withdrawal amount exceeds the balance and overdraft limit.
     */
    @Override
    public boolean withdraw(double amount) throws Exception {
        if (amount > 0 && balance + overdraftLimit >= amount) {
            balance -= amount;
            notifyAccountActivity("Withdrawal of " + amount + " made. New balance is " + balance);
            archiveTransaction("Withdrawal: " + amount);
            return true;
        } else {
            throw new BankSystemException("Insufficient balance or invalid amount.");
        }
    }

    /**
     * Provides a summary of the account's details.
     */
    @Override
    public void accountSummary() {
        System.out.println("Account Type: Checking\nAccount Number: " + getAccountNumber() +
                "\nAccount Holder: " + getAccountHolder() +
                "\nBalance: " + getBalance() + "\nOverdraft Limit: " + overdraftLimit);
    }

    /**
     * Notifies the account holder of activity via SMS.
     * @param message The notification message.
     */
    @Override
    public void notifyAccountActivity(String message) {
        System.out.println("SMS Notification: " + message);
    }

    /**
     * Calculates the annual fees for the account.
     * @return The annual fees associated with the account.
     */
    @Override
    public double calculateAnnualFees() {
        return getAnnualFees();
    }

    /**
     * Specifies that no minimum balance is required for checking accounts.
     * @return 0, indicating no minimum balance requirement.
     */
    @Override
    public double minimumBalanceRequired() {
        return 0;
    }

    /**
     * Archives a transaction detail to the system log or console.
     * @param transactionDetails Details of the transaction to be archived.
     */
    @Override
    public void archiveTransaction(String transactionDetails) {
        System.out.println("Last transaction: " + transactionDetails);
    }
}
