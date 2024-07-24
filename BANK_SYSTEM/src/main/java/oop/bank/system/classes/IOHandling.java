package oop.bank.system.classes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages input and output operations related to client data, handling file creation, reading, and writing.
 */
public class IOHandling {
    private String clientListFilePath; // Path to the file containing the list of clients.
    private String clientDataDirectory; // Directory where individual client data files are stored.
    private List<String> clientList; // List storing loaded client data.

    /**
     * Initializes paths for client data and creates necessary directories and files.
     * @param clientListFilePath Path to the file listing all clients.
     * @param clientDataDirectory Path to the directory where individual client data files are stored.
     */
    public IOHandling(String clientListFilePath, String clientDataDirectory) {
        this.clientListFilePath = clientListFilePath;
        this.clientDataDirectory = clientDataDirectory;
        this.clientList = new ArrayList<>();
        createClientDataDirectory();
        createClientListFile();
    }

    /**
     * Creates the directory to store individual client data files if it does not exist.
     */
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

    /**
     * Creates the file that lists all clients if it does not exist.
     */
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

    /**
     * Reads client data from the list file into memory.
     */
    public void readClientList() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(clientListFilePath))) {
            clientList = (List<String>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Client list file not found. Initializing empty list.");
            clientList = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while reading the client list.");
            e.printStackTrace();
        }
    }

    /**
     * Writes a client's data to the client list file.
     * @param client Client whose data is to be written.
     */
    public void writeClientToList(Client client) {
        clientList.add(String.valueOf(client).toString());
        writeClientList();
    }
    public void writeClientList() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(clientListFilePath))) {
            oos.writeObject(clientList);
        } catch (IOException e) {
            System.out.println("An error occurred while writing the client list file.");
            e.printStackTrace();
        }
    }
    /**
     * Writes or updates client-specific data in their individual data file.
     * @param client Client whose data is to be written.
     * @param balance Current balance of the client to be recorded.
     */
    public void writeClientData(Client client, double balance) {
        String clientFolderPath = clientDataDirectory + "/" + client.getAccountNumber();
        String clientFilePath = clientFolderPath + "/client-info.txt";

        try {
            Path path = Paths.get(clientFolderPath);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
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
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing the client data to file.");
            e.printStackTrace();
        }
    }

    /**
     * Reads account data for a given client from their data file.
     * @param client Client whose account data is to be read.
     * @return BankAccount object containing the account details or null if no data is found.
     */
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

    /**
     * Deletes all data files associated with a client.
     * @param accountNumber Account number of the client whose data is to be deleted.
     */
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

    // Getter and setter methods for file paths

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
