package oop.bank.system;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankManager bankManager = new BankManager();

        List<String> questions = List.of(
                "Please enter your First Name",
                "Please enter your Last Name",
                "Please enter your Email",
                "Please enter your Phone"
        );

        // Create a UserInput object
        UserInput userInput = new UserInput(questions);
        userInput.askQuestions();

        // Get the user inputs
        Map<String, String> userInputs = userInput.getQuestionsAndAnswers();
        String firstName = userInputs.get("Please enter your First Name");
        String lastName = userInputs.get("Please enter your Last Name");
        String email = userInputs.get("Please enter your Email");
        String phone = userInputs.get("Please enter your Phone");

        long accountNumber = (long) (Math.random() * 9999999999L + 1000000000L);

        // Create a Client object
        Client client = new Client(accountNumber, firstName, lastName, email, phone);

        // Add the client to the bank manager
        bankManager.addClient(client, true); // For example, adding a checking account

        // Print all account summaries
        bankManager.printAccountSummaries();

        // Test deposit method
        bankManager.deposit(accountNumber, 200.0);
        System.out.println("Balance after deposit: " + bankManager.getAccountBalance(accountNumber));

        // Test withdraw method
        bankManager.withdraw(accountNumber, 100.0);
        System.out.println("Balance after withdrawal: " + bankManager.getAccountBalance(accountNumber));

        // Test transfer funds method
        long anotherAccountNumber = (long) (Math.random() * 9999999999L + 1000000000L);
        Client anotherClient = new Client(anotherAccountNumber, "Alice", "Wonderland", "alice@example.com", "555-9876");
        bankManager.addClient(anotherClient, false); // Adding a savings account
        bankManager.transferFunds(accountNumber, anotherAccountNumber, 50.0);
        System.out.println("Balance after transfer for account " + accountNumber + ": " + bankManager.getAccountBalance(accountNumber));
        System.out.println("Balance after transfer for account " + anotherAccountNumber + ": " + bankManager.getAccountBalance(anotherAccountNumber));

        // Print updated account summaries
        bankManager.printAccountSummaries();

        // Test remove client method
        bankManager.removeClient(accountNumber);
        bankManager.printAccountSummaries();

        // Test update client method
        bankManager.updateClient(anotherAccountNumber, "Alice", "Updated", "alice.updated@example.com", "555-0000");
        List<Client> allClients = bankManager.listAllClients();
        System.out.println("Updated client list:");
        for (Client c : allClients) {
            System.out.println("Client Name: " + c.getFullName() + ", Email: " + c.getEmail() + ", Phone: " + c.getPhone());
        }

        // Test find client by name
        List<Client> foundClients = bankManager.findClientByName("Alice Updated");
        System.out.println("Found client(s):");
        for (Client c : foundClients) {
            System.out.println("Client Name: " + c.getFullName() + ", Email: " + c.getEmail() + ", Phone: " + c.getPhone());
        }
    }
}
