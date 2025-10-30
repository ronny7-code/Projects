import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {

    private final Connection connection;
    private final Scanner scanner;

    public User(Connection connection, Scanner scanner){
        this.connection  = connection;
        this.scanner = scanner;
    }

    public void register(){
        scanner.nextLine();
        System.out.print("Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if(userExists(email)){
            System.out.println("User already exists for this email address!");
            return;
        }
        String query = "INSERT INTO user(fullName, email, password) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows > 0){
                System.out.println("Registration Successful!");
            }
            else{
                System.out.println("Registration Failed!");
            }

        } catch (SQLException e) {
            System.out.println("Exception while preparing statement: " + e.getMessage());
        }
    }

    public String login(){
        scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        String query = "SELECT * FROM user WHERE email = ? AND password = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return email;
                } else {
                    return null;
                }
        }
        catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public boolean userExists(String email){

        String query = "SELECT * FROM user WHERE email = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return true;
                }
        }
        catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return false;
    }

}