package oop.bank.system;

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
                System.out.println(line);
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
            System.out.println("Client written to client list file: " + client);
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
                System.out.println("Client folder created: " + clientFolderPath);
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
            System.out.println("Client data written to file: " + clientFilePath);
        } catch (IOException e) {
            System.out.println("An error occurred while writing the client data to file.");
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
