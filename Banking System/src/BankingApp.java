import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class BankingApp {

    private static final String URL = "Put your Driver URl Here";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Exception while loading driver: " + e.getMessage());
        }

        Connection connection = null;
        Scanner scanner = new Scanner(System.in);

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            User user = new User(connection, scanner);
            Account account = new Account(connection, scanner);
            AccountManager accountManager = new AccountManager(connection, scanner);

            String email;
            long accNumber;

            while(true){
                System.out.println("-------- WELCOME TO BANKING SYSTEM --------");
                System.out.println();
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice){

                    case 1 -> user.register();
                    case 2 -> {
                        email = user.login();
                        if(email != null){
                            System.out.println();
                            System.out.println("User logged in");
                            if(account.accountExists(email)){
                                System.out.println();
                                System.out.println("1. Open bank account");
                                System.out.println("2. Exit");
                                int option = scanner.nextInt();
                                if(option == 1){
                                    accNumber = account.openAccount(email);
                                    System.out.println("Account Created Successfully! ");
                                    System.out.println("Your account number is: " + accNumber);
                                }
                                else if(option == 2){
                                    break;
                                }
                                else{
                                    System.out.println("Enter a valid choice");
                                }
                            }
                            accNumber = account.getAccNumber(email);
                            int choice1 = 0;

                            while (choice1 != 5){
                                System.out.println();
                                System.out.println("1. Debit Money");
                                System.out.println("2. Credit Money");
                                System.out.println("3. Transfer Money");
                                System.out.println("4. Check Balance");
                                System.out.println("5. Log Out");
                                System.out.print("Enter your choice: ");
                                choice1 = scanner.nextInt();
                                switch (choice1){
                                    case 1 -> accountManager.debitMoney(accNumber);
                                    case 2 -> accountManager.creditMoney(accNumber);
                                    case 3 -> accountManager.transferMoney(accNumber);
                                    case 4 -> accountManager.getBalance(accNumber);
                                    case 5 -> System.out.println("logging out..");
                                    default -> System.out.println("Enter a valid choice");
                                }
                            }
                        }
                        else{
                            System.out.println("Incorrect Email or Password! ");
                        }
                    }
                    case 3 -> {
                        System.out.println("Thank You for using Banking System :)");
                        System.out.println("Exiting System...");
                        return;
                    }
                    default -> System.out.println("Enter a valid choice");
                }

            }

        } catch (SQLException e) {
            System.out.println("Exception while establishing connection: " + e.getMessage());
        }
        scanner.close();
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}