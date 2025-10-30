package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {

    private Connection connection;
    private Scanner scanner;
    public Patient(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient(){
        scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();
        String query = "INSERT INTO patients(name, age, gender) VALUES (?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);
                preparedStatement.setString(3, gender);

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Patient Added Successfully!");
                } else {
                    System.out.println("Failed to add Patient!");
                }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void viewPatient() {
        String query = "SELECT * FROM patients;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patients: ");
            System.out.println("+------------+-------------------+------------+--------------+");
            System.out.println("| Patient ID | Name              | Age        | Gender       |");
            System.out.println("+------------+-------------------+------------+--------------+");

            while (resultSet.next()){
               int id = resultSet.getInt("id");
               String name = resultSet.getString("name");
               int age = resultSet.getInt("age");
               String gender = resultSet.getString("gender");
                System.out.printf("| %-10d | %-17s | %-10d | %-11s |\n", id, name, age, gender);
                System.out.println("+------------+-------------------+------------+--------------+");
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public boolean getPatientById(int id){
        String query = "SELECT * FROM patients WHERE id = ?;";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return false;
    }
}