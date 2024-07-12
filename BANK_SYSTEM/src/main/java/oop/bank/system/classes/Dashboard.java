package oop.bank.system.classes;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Dashboard {
    private BankManager bankManager;
    private Scanner scanner;

    public Dashboard(BankManager bankManager) {
        this.bankManager = bankManager;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        String choice;
        do {
            System.out.println("\n--- Bank System Menu ---");
            System.out.println("1. List All Clients");
            System.out.println("2. Add New Client");
            System.out.println("3. Remove Client");
            System.out.println("4. Update Client Details");
            System.out.println("5. Print Account Summaries");
            System.out.println("6. Deposit Funds");
            System.out.println("7. Withdraw Funds");
            System.out.println("8. Transfer Funds");
            System.out.println("9. Export Data to CSV (Not Implemented)");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    listAllClients();
                    break;
                case "2":
                    addNewClient();
                    break;
                case "3":
                    removeClient();
                    break;
                case "4":
                    updateClientDetails();
                    break;
                case "5":
                    printAccountSummaries();
                    break;
                case "6":
                    depositFunds();
                    break;
                case "7":
                    withdrawFunds();
                    break;
                case "8":
                    transferFunds();
                    break;
                case "9":
                    exportToCSV();
                    break;
                case "0":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (!choice.equals("0"));
    }

    private void addNewClient() {
        UserInput userInput = new UserInput(Arrays.asList("First Name", "Last Name", "Email", "Phone", "Account Type (1 for Checking, 2 for Savings)"));
        userInput.askQuestions();
        Map<String, String> answers = userInput.getQuestionsAndAnswers();
        Client client = new Client(answers.get("First Name"), answers.get("Last Name"), answers.get("Email"), answers.get("Phone"));
        boolean isCheckingAccount = "1".equals(answers.get("Account Type (1 for Checking, 2 for Savings)"));
        bankManager.addClient(client, isCheckingAccount);
        System.out.println("New client added successfully.");
    }

    private void removeClient() {
        UserInput userInput = new UserInput(Arrays.asList("Account Number"));
        userInput.askQuestions();
        long accountNumber = Long.parseLong(userInput.getQuestionsAndAnswers().get("Account Number"));
        if (bankManager.removeClient(accountNumber)) {
            System.out.println("Client removed successfully.");
        } else {
            System.out.println("Failed to remove client.");
        }
    }

    private void updateClientDetails() {
        UserInput userInput = new UserInput(Arrays.asList("Account Number", "Updated First Name", "Updated Last Name", "Updated Email", "Updated Phone"));
        userInput.askQuestions();
        Map<String, String> answers = userInput.getQuestionsAndAnswers();
        long accountNumber = Long.parseLong(answers.get("Account Number"));
        boolean updated = bankManager.updateClient(
                accountNumber,
                answers.get("Updated First Name"),
                answers.get("Updated Last Name"),
                answers.get("Updated Email"),
                answers.get("Updated Phone")
        );
        if (updated) {
            System.out.println("Client updated successfully.");
        } else {
            System.out.println("Failed to update client.");
        }
    }

    private void depositFunds() {
        UserInput userInput = new UserInput(Arrays.asList("Account Number", "Amount to Deposit"));
        userInput.askQuestions();
        Map<String, String> answers = userInput.getQuestionsAndAnswers();
        long accountNumber = Long.parseLong(answers.get("Account Number"));
        double amount = Double.parseDouble(answers.get("Amount to Deposit"));
        if (bankManager.deposit(accountNumber, amount)) {
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Deposit failed.");
        }
    }

    private void withdrawFunds() {
        UserInput userInput = new UserInput(Arrays.asList("Account Number", "Amount to Withdraw"));
        userInput.askQuestions();
        Map<String, String> answers = userInput.getQuestionsAndAnswers();
        long accountNumber = Long.parseLong(answers.get("Account Number"));
        double amount = Double.parseDouble(answers.get("Amount to Withdraw"));
        try {
            if (bankManager.withdraw(accountNumber, amount)) {
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Withdrawal failed.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void transferFunds() {
        UserInput userInput = new UserInput(Arrays.asList("Source Account Number", "Destination Account Number", "Amount to Transfer"));
        userInput.askQuestions();
        Map<String, String> answers = userInput.getQuestionsAndAnswers();
        long fromAccountNumber = Long.parseLong(answers.get("Source Account Number"));
        long toAccountNumber = Long.parseLong(answers.get("Destination Account Number"));
        double amount = Double.parseDouble(answers.get("Amount to Transfer"));
        if (bankManager.transferFunds(fromAccountNumber, toAccountNumber, amount)) {
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Transfer failed.");
        }
    }

    private void exportToCSV() {
        System.out.println("Export to CSV feature not implemented yet.");
    }

    private void listAllClients() {
        System.out.println("\nListing All Clients:");
        bankManager.listAllClients().forEach(client -> System.out.println(client));
    }

    private void printAccountSummaries() {
        bankManager.printAccountSummaries();
    }
}