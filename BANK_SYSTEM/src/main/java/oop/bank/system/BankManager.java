package oop.bank.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BankManager {
    private List<Client> clients;
    private List<BankAccount> accounts;

    public BankManager() {
        this.clients = new ArrayList<>();
        this.accounts = new ArrayList<>();
        initializeClientsAndAccounts();
    }

    private void initializeClientsAndAccounts() {
        // Add some default data just for testing
        Client client1 = new Client(1000000001L, "John", "Doe", "john.doe@example.com", "555-1234");
        Client client2 = new Client(1000000002L, "Jane", "Smith", "jane.smith@example.com", "555-5678");
        addClient(client1, true);
        addClient(client2, false);
    }

    public void addClient(Client client, boolean isCheckingAccount) {
        clients.add(client);
        BankAccount account;
        if (isCheckingAccount) {
            account = new CheckingAccount(client.getAccountNumber(), client.getFullName(), 200, 100);
        } else {
            account = new SavingsAccount(client.getAccountNumber(), client.getFullName(), 0.5, 50);
        }
        accounts.add(account);
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
        return clients.stream()
                .filter(client -> client.getFullName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public Optional<BankAccount> findAccountByNumber(long accountNumber) {
        return accounts.stream()
                .filter(account -> account.getAccountNumber() == accountNumber)
                .findFirst();
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
