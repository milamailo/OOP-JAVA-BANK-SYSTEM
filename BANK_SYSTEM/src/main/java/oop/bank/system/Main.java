package oop.bank.system;

import java.util.List;
import java.util.Map;

/**
 * Main class to test the banking system.
 */
public class Main {
    public static void main(String[] args) {
        String clientListFilePath = "data/client-list.txt"; // Path to your client list file
        String clientDataDirectory = "data/clientData"; // Directory for individual client data files

        BankManager bankManager = new BankManager(clientListFilePath, clientDataDirectory);



        bankManager.withdraw(7812700066L, 10000.0);
        bankManager.printAccountSummaries();
    }
}
