
# Bank System Application

This application simulates a banking system where users can manage different types of bank accounts, clients, and perform various banking operations. The application is written in Java and follows object-oriented programming principles.

## Table of Contents
- [Features](#features)
- [Class Diagram](#class-diagram)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

- Manage different types of bank accounts (Checking, Savings)
- Perform banking operations like deposit, withdraw, and transfer funds
- Manage client information
- Calculate annual fees and minimum balance requirements
- Simulate account activities and send notifications

## Class Diagram

![UML Diagram](path/to/uml_diagram.png)

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- An IDE or text editor of your choice

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/bank-system.git
   ```
2. Navigate to the project directory:
   ```sh
   cd bank-system
   ```
3. Open the project in your preferred IDE and build the project.

## Usage

### Running the Application

1. Navigate to the `src` directory:
   ```sh
   cd src
   ```
2. Compile the `Main.java` file:
   ```sh
   javac oop/bank/system/Main.java
   ```
3. Run the application:
   ```sh
   java oop.bank.system.Main
   ```

### Application Structure

The application consists of the following main classes:

- `BankAccount`: Abstract base class for different types of bank accounts.
- `CheckingAccount`: Class representing a checking account.
- `SavingsAccount`: Class representing a savings account.
- `Client`: Class representing a client of the bank.
- `BankManager`: Class managing the operations for the bank, handling clients and their accounts.
- `Dashboard`: Class providing a text-based user interface for interacting with the banking system.
- `IOHandling`: Class managing input and output operations related to client data.
- `BankSystemException`: Custom exception class for handling specific errors within the banking system.
- `UserInput`: Class handling dynamic user input collection based on predefined questions.

### Example Operations

- Add a new client
- Remove an existing client
- Update client details
- Deposit funds into an account
- Withdraw funds from an account
- Transfer funds between accounts
- Print account summaries

## Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

Distributed under the MIT License. See `LICENSE` for more information.
