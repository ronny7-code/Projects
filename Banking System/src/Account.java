import java.sql.*;
import java.util.Scanner;

public class Account {

    private final Connection connection;
    private final Scanner scanner;

    public Account(Connection connection, Scanner scanner){
        this.connection  = connection;
        this.scanner = scanner;
    }

    //
    public long openAccount(String email){

        if(accountExists(email)) {
            String query = "INSERT INTO accounts(accNumber, fullName, email, balance, securityPin) VALUES(?, ?, ?, ?, ?);";

            scanner.nextLine();
            System.out.print("Enter Full Name: ");
            String fName = scanner.nextLine();
            System.out.print("Enter initial amount: ");
            double balance = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Enter Security Pin: ");
            String securityPin = scanner.nextLine();
            long accountNumber = generateAccNumber();

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setLong(1, accountNumber);
                preparedStatement.setString(2, fName);
                preparedStatement.setString(3, email);
                preparedStatement.setDouble(4, balance);
                preparedStatement.setString(5, securityPin);

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    return accountNumber;
                } else {
                    throw new RuntimeException("Account Creation Failed");
                }
            } catch (SQLException e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }
        throw new RuntimeException("Account Creation Failed");
    }

    public long getAccNumber(String email){
        String query = "SELECT accNumber FROM accounts WHERE email = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
             ResultSet resultSet = preparedStatement.executeQuery();

             if(resultSet.next()){
                 return resultSet.getLong("accNumber");
             }

        } catch (SQLException e) {
            System.out.println("Exception: "+ e.getMessage());
        }
        throw new RuntimeException("Account number doesn't exists");
    }

    public long generateAccNumber(){
        try(Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery("SELECT accNumber FROM accounts ORDER BY accNumber DESC LIMIT 1");

            if(resultSet.next()){
                long lastAccNumber = resultSet.getLong("accNumber");
                return lastAccNumber + 1;
            }
            else{
             return 10000100;
            }

        } catch (SQLException e) {
            System.out.println("Exception: "+ e.getMessage());
        }
        return 10000100;
    }

    public boolean accountExists(String email){

        String query = "SELECT accNumber FROM accounts WHERE email = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        }
        catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return true;
    }

}