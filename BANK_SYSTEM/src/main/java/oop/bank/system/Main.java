package oop.bank.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        double annualFee = 100.0;
        long accountNumber = (long) (Math.random() * 9999999999L + 1000000000L);
        List<String> questions = new ArrayList<>();
        questions.add("Please enter your First Name");
        questions.add("Please enter your Last Name");

        // Create a UserInput object
        UserInput userInput = new UserInput(questions);
        userInput.askQuestions();

        // Construct the accountHolder string using StringBuilder
        StringBuilder accountHolderBuilder = new StringBuilder();
        boolean first = true;
        for (String question : questions) {
            if (!first) {
                accountHolderBuilder.append(" ");
            }
            accountHolderBuilder.append(userInput.getQuestionsAndAnswers().get(question));
            first = false;
        }
        String accountHolder = accountHolderBuilder.toString();

        // Create a BankAccount object
        BankAccount account = new BankAccount(accountNumber, accountHolder, annualFee);

        // Print initial balance
        System.out.println("Initial balance: " + account.getBalance());

        // Test deposit method
        boolean depositSuccess = account.deposit(200.0);
        System.out.println("Deposit successful: " + depositSuccess);
        System.out.println("Balance after deposit: " + account.getBalance());

        // Test withdraw method
        try {
            boolean withdrawSuccess = account.withdraw(100.0);
            System.out.println("Withdraw successful: " + withdrawSuccess);
            System.out.println("Balance after withdrawal: " + account.getBalance());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Test withdraw method with insufficient funds
        try {
            boolean withdrawSuccess = account.withdraw(200.0);
            System.out.println("Withdraw successful: " + withdrawSuccess);
            System.out.println("Balance after withdrawal: " + account.getBalance());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Print account details
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Account Holder: " + account.getAccountHolder());
        System.out.println("Annual Fees: " + account.getAnnualFees());
    }
}
