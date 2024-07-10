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
        questions.add("Please enter your Email");
        questions.add("Please enter your Phone");

        // Create a UserInput object
        UserInput userInput = new UserInput(questions);
        userInput.askQuestions();

        // Get the user inputs
        Map<String, String> userInputs = userInput.getQuestionsAndAnswers();
        String firstName = userInputs.get("Please enter your First Name");
        String lastName = userInputs.get("Please enter your Last Name");
        String email = userInputs.get("Please enter your Email");
        String phone = userInputs.get("Please enter your Phone");

        // Create a Client object
        Client client = new Client(accountNumber, firstName, lastName, email, phone);

        // Create a CheckingAccount object
        CheckingAccount checkingAccount = new CheckingAccount(accountNumber, client.getFullName(), overdraftLimit, annualFee);

        // Print client details
        System.out.println("Client Details:");
        System.out.println("Full Name: " + client.getFullName());
        System.out.println("Email: " + client.getEmail());
        System.out.println("Phone: " + client.getPhone());

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
        } catch (BankSystemException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }

        // Test withdraw method with insufficient funds
        try {
            boolean withdrawSuccess = checkingAccount.withdraw(1000.0); // Exceeds overdraft limit
            System.out.println("Withdraw successful: " + withdrawSuccess);
            System.out.println("Balance after withdrawal: " + checkingAccount.getBalance());
        } catch (BankSystemException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }

        // Print account summary
        checkingAccount.accountSummary();
    }
}