package oop.bank.system.classes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle input and output operations for client data.
 */
public class IOHandling {
    private String clientListFilePath; // Path to the client list file
    private String clientDataDirectory; // Directory for individual client data files
    private List<String> clientList; // List to store client data

    /**
     * Constructor for IOHandling.
     * @param clientListFilePath Path to the client list file
     * @param clientDataDirectory Directory for individual client data files
     */
    public IOHandling(String clientListFilePath, String clientDataDirectory) {
        this.clientListFilePath = clientListFilePath;
        this.clientDataDirectory = clientDataDirectory;
        this.clientList = new ArrayList<>();
        createClientDataDirectory();
        createClientListFile();
    }

    private void createClientDataDirectory() {
        Path path = Paths.get(clientDataDirectory);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                System.out.println("Client data directory created: " + clientDataDirectory);
            } catch (IOException e) {
                System.out.println("Failed to create client data directory.");
                e.printStackTrace();
            }
        }
    }

    private void createClientListFile() {
        Path path = Paths.get(clientListFilePath);
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
                System.out.println("Client list file created: " + clientListFilePath);
            } catch (IOException e) {
                System.out.println("Failed to create client list file.");
                e.printStackTrace();
            }
        }
    }

    public void readClientList() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.clientListFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                clientList.add(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the client list.");
            e.printStackTrace();
        }
    }

    public void writeClientToList(Client client) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.clientListFilePath, true))) {
            writer.write(client.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while writing the client to the client list file.");
            e.printStackTrace();
        }
    }

    public void writeClientData(Client client, double balance) {
        String clientFolderPath = clientDataDirectory + "/" + client.getAccountNumber();
        String clientFilePath = clientFolderPath + "/client-info.txt";

        // Create client folder
        Path path = Paths.get(clientFolderPath);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.out.println("Failed to create client folder.");
                e.printStackTrace();
            }
        }

        // Write client data to client-info.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(clientFilePath))) {
            writer.write("Account Number: " + client.getAccountNumber());
            writer.newLine();
            writer.write("Account Holder: " + client.getFullName());
            writer.newLine();
            writer.write("Email: " + client.getEmail());
            writer.newLine();
            writer.write("Phone: " + client.getPhone());
            writer.newLine();
            writer.write("Balance: " + balance);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while writing the client data to file.");
            e.printStackTrace();
        }
    }

    public BankAccount readAccountData(Client client) {
        String clientFolderPath = clientDataDirectory + "/" + client.getAccountNumber();
        String clientFilePath = clientFolderPath + "/client-info.txt";

        if (!Files.exists(Paths.get(clientFilePath))) {
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(clientFilePath))) {
            String line;
            double balance = 0.0;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Balance: ")) {
                    balance = Double.parseDouble(line.split(": ")[1]);
                }
            }
            return new CheckingAccount(client.getAccountNumber(), client.getFullName(), 200, 100, balance);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the account data.");
            e.printStackTrace();
            return null;
        }
    }

    public void removeClientData(long accountNumber) {
        String clientFolderPath = clientDataDirectory + "/" + accountNumber;
        try {
            Files.walk(Paths.get(clientFolderPath))
                    .sorted((path1, path2) -> path2.compareTo(path1))
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            System.out.println("Failed to delete client data.");
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            System.out.println("An error occurred while deleting client data.");
            e.printStackTrace();
        }
    }

    public List<String> getClientList() {
        return clientList;
    }

    public String getClientListFilePath() {
        return clientListFilePath;
    }

    public void setClientListFilePath(String clientListFilePath) {
        this.clientListFilePath = clientListFilePath;
    }

    public String getClientDataDirectory() {
        return clientDataDirectory;
    }

    public void setClientDataDirectory(String clientDataDirectory) {
        this.clientDataDirectory = clientDataDirectory;
    }
}
