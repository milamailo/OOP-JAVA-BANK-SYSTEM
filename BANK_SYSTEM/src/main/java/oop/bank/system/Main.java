package oop.bank.system;

import oop.bank.system.classes.BankManager;
import oop.bank.system.classes.Dashboard;

/**
 * Main class to test the banking system.
 */
public class Main {
    public static void main(String[] args) {
        String clientListFilePath = "data/client-list.txt"; // Path to your client list file
        String clientDataDirectory = "data/clientData"; // Directory for individual client data files

        Dashboard dashboard = new Dashboard(new BankManager(clientListFilePath, clientDataDirectory));
        dashboard.displayMenu();
//        bankManager.withdraw(7812700066L, 10000.0);
//        bankManager.printAccountSummaries();
    }
}
