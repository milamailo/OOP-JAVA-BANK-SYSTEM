package oop.bank.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        double annualFee = 100.0;
        long accountNumber = (long) (Math.random() * 9999999999L + 1000000000L);
        double interestRate = 1.5;
        double overdraftLimit = 500.0;

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

        // Create a CheckingAccount object
        CheckingAccount checkingAccount = new CheckingAccount(accountNumber, accountHolder, overdraftLimit, annualFee);

        // Print initial balance and account details
        System.out.println("Initial balance: " + checkingAccount.getBalance());
        checkingAccount.accountSummary();

        // Test deposit method
        boolean depositSuccess = checkingAccount.deposit(200.0);
        System.out.println("Deposit successful: " + depositSuccess);
        System.out.println("Balance after deposit: " + checkingAccount.getBalance());

        // Test withdraw method within overdraft limit
        try {
            boolean withdrawSuccess = checkingAccount.withdraw(600.0); // Test overdraft
            System.out.println("Withdraw successful: " + withdrawSuccess);
            System.out.println("Balance after withdrawal: " + checkingAccount.getBalance());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Test withdraw method with insufficient funds
        try {
            boolean withdrawSuccess = checkingAccount.withdraw(1000.0); // Exceeds overdraft limit
            System.out.println("Withdraw successful: " + withdrawSuccess);
            System.out.println("Balance after withdrawal: " + checkingAccount.getBalance());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Print account summary
        checkingAccount.accountSummary();
    }
}