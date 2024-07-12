package oop.bank.system.classes;

public class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(long accountNumber, String accountHolder, double interestRate, double annualFee) {
        super(accountNumber, accountHolder, "Savings", annualFee);
        this.interestRate = interestRate;
    }

    public void addInterest() {
        double interest = getBalance() * (interestRate / 100);
        deposit(interest);
        notifyAccountActivity("Interest added: " + interest);
    }

    @Override
    public void accountSummary() {
        System.out.println("Account Type: " + getAccountType() +
                "\nAccount Number: " + getAccountNumber() +
                "\nAccount Holder: " + getAccountHolder() +
                "\nBalance: " + getBalance() +
                "\nInterest Rate: " + interestRate + "%");
    }

    @Override
    public void notifyAccountActivity(String message) {
        System.out.println("Email Notification: " + message);
    }

    @Override
    public double calculateAnnualFees() {
        return getAnnualFees();
    }

    @Override
    public double minimumBalanceRequired() {
        return 500; // Minimum balance required for savings accounts
    }
}
