package oop.bank.system;

public class CheckingAccount extends BankAccount implements IArchive {
    private double overdraftLimit;

    public CheckingAccount(long accountNumber, String accountHolder, double overdraftLimit, double annualFee) {
        super(accountNumber, accountHolder, "Checking", annualFee); // Pass "Checking" as the account type
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount) throws Exception {
        boolean isWithdraw = false;
        if (amount > 0 && balance + overdraftLimit >= amount) {
            balance -= amount;
            System.out.println("Withdrawal of " + amount + " made. New balance is " + balance);
            notifyAccountActivity("Withdrawal of " + amount + " made. New balance is " + balance);
            archiveTransaction("Withdrawal: " + amount);
            isWithdraw = true;
        } else {
            throw new BankSystemException("Withdrawal amount must be positive or within the overdraft limit!!!!");
        }
        return isWithdraw;
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
        return getAnnualFees(); // Higher annual fee due to overdraft facility
    }

    @Override
    public double minimumBalanceRequired() {
        return 0; // No minimum balance required
    }

    @Override
    public void archiveTransaction(String transactionDetails) {
        System.out.println("Last transaction: " + transactionDetails);
    }
}
