package oop.bank.system.classes;

/**
 * Represents a client in the banking system.
 * Holds personal and account information for a bank client.
 */
public class Client {
    private long accountNumber; // Unique account number
    private String firstName;   // Client's first name
    private String lastName;    // Client's last name
    private String email;       // Client's email address
    private String phone;       // Client's phone number

    /**
     * Full constructor initializing all fields of the client.
     * @param accountNumber Account number assigned to the client.
     * @param firstName Client's first name.
     * @param lastName Client's last name.
     * @param email Client's email address.
     * @param phone Client's phone number.
     */
    public Client(long accountNumber, String firstName, String lastName, String email, String phone) {
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Constructor for creating a new client where the account number is not yet assigned.
     * Assumes the account number is 0, which indicates it's not set.
     * @param firstName Client's first name.
     * @param lastName Client's last name.
     * @param email Client's email address.
     * @param phone Client's phone number.
     */
    public Client(String firstName, String lastName, String email, String phone) {
        this(0, firstName, lastName, email, phone);
    }

    /**
     * Returns the full name of the client by concatenating first and last names.
     * @return The full name of the client.
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    // Standard getters and setters for each field

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Provides a string representation of the client, formatted as a CSV line.
     * @return A CSV string representing the client.
     */
    @Override
    public String toString() {
        return accountNumber + "," + firstName + "," + lastName + "," + email + "," + phone;
    }

    /**
     * Static method to parse a client from a CSV string.
     * @param clientString The CSV string containing client data.
     * @return A new instance of Client created from the parsed data.
     */
    public static Client fromString(String clientString) {
        String[] parts = clientString.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid client string: " + clientString);
        }
        long accountNumber = Long.parseLong(parts[0]);
        String firstName = parts[1];
        String lastName = parts[2];
        String email = parts[3];
        String phone = parts[4];
        return new Client(accountNumber, firstName, lastName, email, phone);
    }
}
