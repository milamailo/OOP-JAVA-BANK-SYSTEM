package oop.bank.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class BankManager {
    private List<Client> clients;
    private List<BankAccount> accounts;
    private Random random;
    private IOHandling ioHandling;

    public BankManager(String clientListFilePath, String clientDataDirectory) {
        this.clients = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.random = new Random();
        this.ioHandling = new IOHandling(clientListFilePath, clientDataDirectory);
        initializeClientsAndAccounts();
    }

    private void initializeClientsAndAccounts() {
        ioHandling.readClientList();
        List<String> clientData = ioHandling.getClientList();
        for (String data : clientData) {
            Client client = Client.fromString(data);
            clients.add(client);
            // Add corresponding account (this assumes a default account type; you can enhance it further)
            BankAccount account = new CheckingAccount(client.getAccountNumber(), client.getFullName(), 200, 100);
            accounts.add(account);
        }
    }

    private long generateUniqueAccountNumber() {
        long accountNumber;
        do {
            accountNumber = (long) (random.nextDouble() * 9999999999L + 1000000000L);
        } while (findAccountByNumber(accountNumber).isPresent());
        return accountNumber;
    }

    public void addClient(Client client, boolean isCheckingAccount) {
        long accountNumber = generateUniqueAccountNumber();
        client.setAccountNumber(accountNumber);

        clients.add(client);
        BankAccount account;
        if (isCheckingAccount) {
            account = new CheckingAccount(client.getAccountNumber(), client.getFullName(), 200, 100);
        } else {
            account = new SavingsAccount(client.getAccountNumber(), client.getFullName(), 0.5, 50);
        }
        accounts.add(account);

        // Save client to file
        ioHandling.writeClientToList(client);

        // Save initial client data to individual file
        String initialData = "Balance: " + account.getBalance();
        ioHandling.writeClientData(client, initialData);
    }

    public boolean removeClient(long accountNumber) {
        Optional<Client> clientOptional = clients.stream()
                .filter(client -> client.getAccountNumber() == accountNumber)
                .findFirst();

        if (clientOptional.isPresent()) {
            clients.remove(clientOptional.get());
            accounts.removeIf(account -> account.getAccountNumber() == accountNumber);
            return true;
        }
        return false;
    }

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
            return true;
        }
        return false;
    }

    public boolean transferFunds(long fromAccountNumber, long toAccountNumber, double amount) {
        Optional<BankAccount> fromAccount = findAccountByNumber(fromAccountNumber);
        Optional<BankAccount> toAccount = findAccountByNumber(toAccountNumber);

        if (fromAccount.isPresent() && toAccount.isPresent()) {
            try {
                if (fromAccount.get().withdraw(amount)) {
                    toAccount.get().deposit(amount);
                    return true;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return false;
    }

    public double getAccountBalance(long accountNumber) {
        Optional<BankAccount> account = findAccountByNumber(accountNumber);
        return account.map(BankAccount::getBalance).orElse(0.0);
    }

    public List<Client> listAllClients() {
        return new ArrayList<>(clients);
    }

    public List<Client> findClientByName(String name) {
        List<Client> foundClients = new ArrayList<>();
        for (Client client : clients) {
            if (client.getFullName().equalsIgnoreCase(name)) {
                foundClients.add(client);
            }
        }
        return foundClients;
    }

    public Optional<BankAccount> findAccountByNumber(long accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return Optional.of(account);
            }
        }
        return Optional.empty();
    }

    public boolean deposit(long accountNumber, double amount) {
        Optional<BankAccount> account = findAccountByNumber(accountNumber);
        return account.map(bankAccount -> bankAccount.deposit(amount)).orElse(false);
    }

    public boolean withdraw(long accountNumber, double amount) {
        Optional<BankAccount> account = findAccountByNumber(accountNumber);
        if (account.isPresent()) {
            try {
                return account.get().withdraw(amount);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return false;
            }
        }
        return false;
    }

    public void printAccountSummaries() {
        for (BankAccount account : accounts) {
            account.accountSummary();
        }
    }
}
