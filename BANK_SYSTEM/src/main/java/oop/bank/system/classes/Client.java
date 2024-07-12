package oop.bank.system.classes;

public class Client {
    private long accountNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public Client(long accountNumber, String firstName, String lastName, String email, String phone) {
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public Client(String firstName, String lastName, String email, String phone) {
        this(0, firstName, lastName, email, phone); // Initialize with accountNumber as 0 for new clients
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

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

    @Override
    public String toString() {
        return accountNumber + "," + firstName + "," + lastName + "," + email + "," + phone;
    }

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
