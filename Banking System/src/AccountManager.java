import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager {

    private final Connection connection;
    private final Scanner scanner;

    public AccountManager(Connection connection, Scanner scanner){
        this.connection  = connection;
        this.scanner = scanner;
    }

    public void creditMoney(long accNumber) throws SQLException {

        scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter security pin: ");
        String securityPin = scanner.nextLine();

        try{
            connection.setAutoCommit(false);
            if(accNumber != 0 ){
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE accNumber = ? AND securityPin = ?;");
                preparedStatement.setLong(1, accNumber);
                preparedStatement.setString(2, securityPin);
                ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                        String query = "UPDATE accounts SET balance = balance + ? WHERE accNumber = ?;";
                        PreparedStatement preparedStatement1 = connection.prepareStatement(query);
                        preparedStatement1.setDouble(1, amount);
                        preparedStatement1.setDouble(2, accNumber);
                        int affectedRows = preparedStatement1.executeUpdate();

                        if(affectedRows > 0){
                            System.out.println("Rs." + amount + " credited successfully");
                            connection.commit();
                            connection.setAutoCommit(true);
                            return;
                        }
                        else{
                            System.out.println("Transaction Failed");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }
                    }
                }
                else{
                    System.out.println("Invalid Pin!");
                }
        }
        catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        connection.setAutoCommit(true);
    }

    public void debitMoney(long accNumber) throws SQLException {

        scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter security pin: ");
        String securityPin = scanner.nextLine();

        try{
            connection.setAutoCommit(false);
            if(accNumber != 0 ){
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE accNumber = ? AND securityPin = ?;");
                preparedStatement.setLong(1, accNumber);
                preparedStatement.setString(2, securityPin);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    double currentBalance = resultSet.getDouble("balance");
                    if(amount <= currentBalance){
                        String query = "UPDATE accounts SET balance = balance - ? WHERE accNumber = ?;";
                        PreparedStatement preparedStatement1 = connection.prepareStatement(query);
                        preparedStatement1.setDouble(1, amount);
                        preparedStatement1.setDouble(2, accNumber);
                        int affectedRows = preparedStatement1.executeUpdate();

                        if(affectedRows > 0){
                            System.out.println("Rs." + amount + " debited successfully");
                            connection.commit();
                            connection.setAutoCommit(true);
                        }
                        else{
                            System.out.println("Transaction Failed");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }
                    }
                    else{
                        System.out.println("Insufficient Balance! ");
                    }
                }
                else{
                    System.out.println("Invalid Pin!");
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        connection.setAutoCommit(true);
    }

    public void transferMoney(long senderAccNumber) throws SQLException{
        scanner.nextLine();
        System.out.print("Enter receiver account number: ");
        long receiver = scanner.nextLong();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter Security Pin: ");
        String pin = scanner.nextLine();
        String query = "SELECT * FROM accounts WHERE accNumber = ? AND securityPin = ?;";

        try {
            connection.setAutoCommit(false);
            if(senderAccNumber != 0 && receiver != 0) {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setLong(1, senderAccNumber);
                preparedStatement.setString(2, pin);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    double currentBalance = resultSet.getDouble("balance");

                    if(amount <= currentBalance){
                        String debitQuery = "UPDATE accounts SET balance = balance - ? WHERE accNumber = ?;";
                        String creditQuery = "UPDATE accounts SET balance = balance + ? WHERE accNumber = ?;";

                        PreparedStatement debitPreparedStatement = connection.prepareStatement(debitQuery);
                        PreparedStatement creditPreparedStatement = connection.prepareStatement(creditQuery);

                        debitPreparedStatement.setDouble(1, amount);
                        debitPreparedStatement.setLong(2, senderAccNumber);
                        creditPreparedStatement.setDouble(1, amount);
                        creditPreparedStatement.setLong(2, receiver);

                        int rowsAffected1 = debitPreparedStatement.executeUpdate();
                        int rowsAffected2 = creditPreparedStatement.executeUpdate();

                        if(rowsAffected1 > 0 && rowsAffected2 > 0){
                            System.out.println("Transaction successful!");
                            System.out.println("Rs." + amount + " transferred successfully!");
                            connection.commit();
                            connection.setAutoCommit(true);
                        }
                        else{
                            System.out.println("Transferred Failed! ");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }
                    }
                    else {
                        System.out.println("Insufficient Balance! ");
                    }
                }

            }
            else{
                System.out.println("Invalid account number! ");
            }
        }
        catch (SQLException e){
            System.out.println("Exception: " + e.getMessage());
        }
        connection.setAutoCommit(true);
    }

    public void getBalance(long accNumber){
        scanner.nextLine();
        System.out.print("Enter Security Pin: ");
        String pin = scanner.nextLine();
        String query = "SELECT BALANCE FROM accounts WHERE accNumber = ? AND securityPin = ?;";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setLong(1, accNumber);
            preparedStatement.setString(2, pin);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                double balance = resultSet.getDouble("balance");
                System.out.println("Balance: " + balance);
            }
            else{
                System.out.println("Invalid Pin! ");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}