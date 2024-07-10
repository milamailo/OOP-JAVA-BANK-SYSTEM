package oop.bank.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        double annualFee = 100.0;
        long accountNumber = (long) (Math.random() * 9999999999L + 1000000000L);
        double interestRate = 1.5;
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

        // Create a SavingsAccount object
        SavingsAccount savingsAccount = new SavingsAccount(accountNumber, accountHolder, interestRate, annualFee);

        // Print initial balance and account details
        System.out.println("Initial balance: " + savingsAccount.getBalance());
        System.out.println("Account type: " + savingsAccount.getAccountType());
        savingsAccount.accountSummary();

        // Test deposit method
        boolean depositSuccess = savingsAccount.deposit(200.0);
        System.out.println("Deposit successful: " + depositSuccess);
        System.out.println("Balance after deposit: " + savingsAccount.getBalance());

        // Test withdraw method
        try {
            boolean withdrawSuccess = savingsAccount.withdraw(100.0);
            System.out.println("Withdraw successful: " + withdrawSuccess);
            System.out.println("Balance after withdrawal: " + savingsAccount.getBalance());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Test withdraw method with insufficient funds
        try {
            boolean withdrawSuccess = savingsAccount.withdraw(200.0);
            System.out.println("Withdraw successful: " + withdrawSuccess);
            System.out.println("Balance after withdrawal: " + savingsAccount.getBalance());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Add interest
        savingsAccount.addInterest();
        System.out.println("Balance after adding interest: " + savingsAccount.getBalance());

        // Print account summary
        savingsAccount.accountSummary();
    }
}