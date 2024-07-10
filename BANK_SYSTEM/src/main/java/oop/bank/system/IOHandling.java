package oop.bank.system;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IOHandling {
    private String clientListFilePath;
    private String clientDataDirectory;
    private List<String> clientList;

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

    public void writeClientData(Client client, String initialData) {
        String clientFilePath = clientDataDirectory + "/" + client.getAccountNumber() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(clientFilePath))) {
            writer.write("Account Number: " + client.getAccountNumber());
            writer.newLine();
            writer.write("Account Holder: " + client.getFullName());
            writer.newLine();
            writer.write("Email: " + client.getEmail());
            writer.newLine();
            writer.write("Phone: " + client.getPhone());
            writer.newLine();
            writer.write("Initial Data: " + initialData);
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
