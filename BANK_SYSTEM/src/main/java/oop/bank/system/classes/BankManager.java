package oop.bank.system.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Manages the operations for a bank, handling clients and their accounts.
 */
public class BankManager {
    private List<Client> clients;
    private List<BankAccount> accounts;
    private Random random;
    private IOHandling ioHandling;

    /**
     * Initializes the manager with paths for data storage.
     * @param clientListFilePath Path to the file containing client list.
     * @param clientDataDirectory Directory for client data files.
     */
    public BankManager(String clientListFilePath, String clientDataDirectory) {
        this.clients = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.random = new Random();
        this.ioHandling = new IOHandling(clientListFilePath, clientDataDirectory);
        initializeClientsAndAccounts();
    }

    /**
     * Loads clients and their accounts from storage, or initializes new accounts if none exist.
     */
    private void initializeClientsAndAccounts() {
        ioHandling.readClientList();
        List<String> clientData = ioHandling.getClientList();
        for (String data : clientData) {
            Client client = Client.fromString(data);
            clients.add(client);
            BankAccount account = ioHandling.readAccountData(client);
            if (account == null) {
                account = new CheckingAccount(client.getAccountNumber(), client.getFullName(), 200, 100);
            }
            accounts.add(account);
        }
    }

    /**
     * Generates a unique account number.
     * @return A unique long account number.
     */
    private long generateUniqueAccountNumber() {
        long accountNumber;
        do {
            accountNumber = (long) (random.nextDouble() * 9999999999L + 1000000000L);
        } while (findAccountByNumber(accountNumber).isPresent());
        return accountNumber;
    }

    /**
     * Adds a new client and creates their account, storing information persistently.
     * @param client Client information.
     * @param isCheckingAccount True if the account is a checking account.
     */
    public void addClient(Client client, boolean isCheckingAccount) {
        long accountNumber = generateUniqueAccountNumber();
        client.setAccountNumber(accountNumber);
        clients.add(client);

        BankAccount account = isCheckingAccount
                ? new CheckingAccount(client.getAccountNumber(), client.getFullName(), 200, 100)
                : new SavingsAccount(client.getAccountNumber(), client.getFullName(), 1.5, 50);
        accounts.add(account);

        ioHandling.writeClientToList(client);
        ioHandling.writeClientData(client, account.getBalance());
    }

    /**
     * Removes a client and their account, cleaning up any stored data.
     * @param accountNumber Account number to remove.
     * @return True if successful, false otherwise.
     */
    public boolean removeClient(long accountNumber) {
        Optional<Client> clientOptional = clients.stream()
                .filter(client -> client.getAccountNumber() == accountNumber)
                .findFirst();

        if (clientOptional.isPresent()) {
            clients.remove(clientOptional.get());
            accounts.removeIf(account -> account.getAccountNumber() == accountNumber);
            ioHandling.removeClientData(accountNumber);
            return true;
        }
        return false;
    }

    /**
     * Updates details for an existing client.
     * @param accountNumber Client's account number.
     * @param firstName New first name.
     * @param lastName New last name.
     * @param email New email.
     * @param phone New phone number.
     * @return True if the update was successful, false otherwise.
     */
    public boolean updateClient(long accountNumber, String firstName, String lastName, String email, String phone) {
        Optional<Client> clientOptional = clients.stream()
                .filter(client -> client.getAccountNumber() == accountNumber)
                .findFirst();

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.setFirstName(firstName);
            client.setLastName(lastName);
            client.setEmail(email);
            client.setPhone(phone);
            ioHandling.writeClientData(client, findAccountByNumber(accountNumber).get().getBalance());
            return true;
        }
        return false;
    }

    /**
     * Transfers funds between two accounts.
     * @param fromAccountNumber Source account number.
     * @param toAccountNumber Destination account number.
     * @param amount Amount to transfer.
     * @return True if the transfer was successful, false otherwise.
     */
    public boolean transferFunds(long fromAccountNumber, long toAccountNumber, double amount) {
        Optional<BankAccount> fromAccount = findAccountByNumber(fromAccountNumber);
        Optional<BankAccount> toAccount = findAccountByNumber(toAccountNumber);

        if (fromAccount.isPresent() && toAccount.isPresent()) {
            try {
                if (fromAccount.get().withdraw(amount)) {
                    toAccount.get().deposit(amount);
                    ioHandling.writeClientData(findClientByAccountNumber(fromAccountNumber).get(), fromAccount.get().getBalance());
                    ioHandling.writeClientData(findClientByAccountNumber(toAccountNumber).get(), toAccount.get().getBalance());
                    return true;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return false;
    }

    /**
     * Retrieves the balance of a specific account.
     * @param accountNumber Account number to check balance.
     * @return Balance of the account, or 0.0 if account does not exist.
     */
    public double getAccountBalance(long accountNumber) {
        Optional<BankAccount> account = findAccountByNumber(accountNumber);
        return account.map(BankAccount::getBalance).orElse(0.0);
    }

    /**
     * Lists all clients currently managed by the bank.
     * @return A list of all clients.
     */
    public List<Client> listAllClients() {
        return new ArrayList<>(clients);
    }

    /**
     * Finds clients by their name.
     * @param name Name to search for.
     * @return A list of clients whose names match the given name.
     */
    public List<Client> findClientByName(String name) {
        List<Client> foundClients = new ArrayList<>();
        for (Client client : clients) {
            if (client.getFullName().equalsIgnoreCase(name)) {
                foundClients.add(client);
            }
        }
        return foundClients;
    }

    /**
     * Finds a bank account by account number.
     * @param accountNumber The account number to search for.
     * @return An Optional containing the found BankAccount or an empty Optional if not found.
     */
    public Optional<BankAccount> findAccountByNumber(long accountNumber) {
        return accounts.stream()
                .filter(account -> account.getAccountNumber() == accountNumber)
                .findFirst();
    }

    /**
     * Finds a client by their account number.
     * @param accountNumber The account number associated with the client.
     * @return An Optional containing the found Client or an empty Optional if not found.
     */
    public Optional<Client> findClientByAccountNumber(long accountNumber) {
        return clients.stream()
                .filter(client -> client.getAccountNumber() == accountNumber)
                .findFirst();
    }

    /**
     * Deposits a specified amount into a specified account.
     * @param accountNumber The account number to deposit into.
     * @param amount The amount to deposit.
     * @return True if the deposit was successful, false otherwise.
     */
    public boolean deposit(long accountNumber, double amount) {
        Optional<BankAccount> account = findAccountByNumber(accountNumber);
        if (account.isPresent()) {
            boolean success = account.get().deposit(amount);
            if (success) {
                ioHandling.writeClientData(findClientByAccountNumber(accountNumber).get(), account.get().getBalance());
                return true;
            }
        }
        return false;
    }

    /**
     * Withdraws a specified amount from a specified account.
     * @param accountNumber The account number to withdraw from.
     * @param amount The amount to withdraw.
     * @return True if the withdrawal was successful, false otherwise.
     */
    public boolean withdraw(long accountNumber, double amount) {
        Optional<BankAccount> account = findAccountByNumber(accountNumber);
        if (account.isPresent()) {
            try {
                boolean success = account.get().withdraw(amount);
                if (success) {
                    ioHandling.writeClientData(findClientByAccountNumber(accountNumber).get(), account.get().getBalance());
                    return true;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return false;
    }

    /**
     * Prints summaries for all accounts.
     */
    public void printAccountSummaries() {
        accounts.forEach(BankAccount::accountSummary);
    }
}
