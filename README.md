# Banking-System(JAVA + MYSQL)

> A simple console-based banking system written in **Java** with **MySQL** integration using **JDBC** and  project build in **Maven**.
> Demonstrating CRUD operations, user authentication, and transaction history.
>  **Features:-**
> Create a new user bank account.
> Login with email and PIN.
> Deposit and withdraw money.
> Check account balance.
> View transaction history.
> MySQL database integration using JDBC.
>
> **Project Structure:-**
> BankingSystem/
├── src/
│   └── main/
│       └── java/
│           └── com/bank/
│               ├── Main.java
│               ├── DBConnection.java
│               ├── User.java
│               └── BankingOperations.java
├── pom.xml
└── README.md
>
>  **Setup Instructions:-**
 Prerequisites
 Java 17+
 MySQL Server (8.x)
 IntelliJ IDEA or VS Code
 Maven
>
> **How to Run the App:-**
Clone this repo
Import into IntelliJ or VS Code
Make sure MySQL is running and update your credentials in DBConnection.java:-
 private static final String URL = "jdbc:mysql://localhost:3306/banking_system";
 private static final String USER = "root";
 private static final String PASSWORD = "yourpassword";
>
> **Contributing**
Pull requests are welcome! Feel free to fork the repo and improve error handling, security, or even add a GUI.



