package oop.bank.system;

public abstract class BankAccount {
    private long accountNumber;
    private String accountHolder;
    private String accountType;  // New property
    protected double balance;
    protected double annualFees;

    public BankAccount(long accountNumber, String accountHolder, String accountType, double annualFees) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.annualFees = annualFees;
    }

    public abstract void accountSummary();  // Abstract method for account summary

    public abstract void notifyAccountActivity(String message);

    public abstract double calculateAnnualFees();

    public abstract double minimumBalanceRequired();

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            notifyAccountActivity("Deposited: " + amount + "; New Balance: " + balance);
            return true;
        } else {
            return false;
        }
    }

    public boolean withdraw(double amount) throws Exception {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            notifyAccountActivity("Withdrawn: " + amount + "; New Balance: " + balance);
            return true;
        } else {
            throw new BankSystemException("Insufficient balance or invalid amount.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getAnnualFees() {
        return annualFees;
    }
}
