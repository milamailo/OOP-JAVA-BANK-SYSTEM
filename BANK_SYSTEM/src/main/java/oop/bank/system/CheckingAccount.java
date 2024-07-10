package oop.bank.system;

public class CheckingAccount extends BankAccount implements IArchive {
    private double overdraftLimit;

    public CheckingAccount(long accountNumber, String accountHolder, double overdraftLimit, double annualFee) {
        super(accountNumber, accountHolder, "Checking", annualFee); // Pass "Checking" as the account type
        this.overdraftLimit = overdraftLimit;
    }

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

    @Override
    public void accountSummary() {
        System.out.println("Account Type: Checking\nAccount Number: " + getAccountNumber() +
                "\nAccount Holder: " + getAccountHolder() +
                "\nBalance: " + getBalance() + "\nOverdraft Limit: " + overdraftLimit);
    }

    @Override
    public void notifyAccountActivity(String message) {
        System.out.println("SMS Notification: " + message);
    }

    @Override
    public double calculateAnnualFees() {
        return getAnnualFees();
    }

    @Override
    public double minimumBalanceRequired() {
        return 0;
    }

    @Override
    public void archiveTransaction(String transactionDetails) {
        System.out.println("Last transaction: " + transactionDetails);
    }
}
