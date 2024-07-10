package oop.bank.system;

public class Client {
    private long accountNumber; // Using long for account numbers due to their potentially large size
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

    public String getFullName() {
        return firstName + " " + lastName; // Concatenate first name and last name for full name
    }

    public long getAccountNumber() {
        return accountNumber;
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
}

