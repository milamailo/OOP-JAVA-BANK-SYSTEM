package oop.bank.system;

import oop.bank.system.classes.BankManager;
import oop.bank.system.classes.Dashboard;

/**
 * Main entry point for the banking system application.
 * This class initializes the system with path configurations and starts the user interaction
 * through the Dashboard interface.
 */
public class Main {
    public static void main(String[] args) {
        // Path to the text file containing client information.
        String clientListFilePath = "data/client-list.bin";

        // Directory path where individual client data files are stored.
        String clientDataDirectory = "data/clientData";

        // Create a Dashboard instance with a new BankManager configured with paths.
        Dashboard dashboard = new Dashboard(new BankManager(clientListFilePath, clientDataDirectory));

        // Display the main menu to the user to begin interaction with the banking system.
        dashboard.displayMenu();
    }
}
