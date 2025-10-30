package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MainClass {

    private static final String DRIVER_URL = "com.mysql.cj.jdbc.Driver";

    // Database url, username and password
     private static final String URL = "Put your Driver URl Here";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";

    public static void main(String[] args) {

        // Loading driver
        try{
         Class.forName(DRIVER_URL);
        } catch (ClassNotFoundException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // Connection to database
        Connection connection = null;
        Scanner scanner = new Scanner(System.in);
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Patient patient = new Patient(connection, scanner);
            Doctor doctor = new Doctor(connection);

            while (true){

                System.out.println("----- HOSPITAL MANAGEMENT SYSTEM -----");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patient");
                System.out.println("3. View Doctor");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice){
                    case 1 -> {
                        patient.addPatient();
                        System.out.println();
                    }
                    case 2 -> {
                        patient.viewPatient();
                        System.out.println();
                    }
                    case 3 -> {
                        doctor.viewDoctors();
                        System.out.println();
                    }
                    case 4 -> {
                        bookAppointment(patient, doctor, connection, scanner);
                        System.out.println();
                    }
                    case 5 -> {
                        System.out.println("Exiting Banking System...");
                        return;
                    }
                    default -> System.out.println("Enter a valid choice");
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception while establishing connection: " + e.getMessage());
        }finally {
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

    public static void bookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner){
        System.out.print("Enter Patient ID: ");
        int patientID = scanner.nextInt();
        System.out.print("Enter Doctor ID: ");
        int doctorID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Appointment date(YYYY-MM-DD): ");
        String appointmentDate = scanner.nextLine();

        if(patient.getPatientById(patientID) && doctor.getDoctorById(doctorID)){
            if(doctorAvailable(doctorID, appointmentDate, connection)){

                String appointmentQuery = "INSERT INTO appointments(patientId, doctorId, appointmentDate) VALUES" +
                        "(?, ?, ?);";
                try (PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery)) {
                    preparedStatement.setInt(1, patientID);
                    preparedStatement.setInt(2, doctorID);
                    preparedStatement.setString(3, appointmentDate);

                    int affectedRows = preparedStatement.executeUpdate();
                    if(affectedRows > 0){
                        System.out.println("Appointment booked! ");
                    }
                    else{
                        System.out.println("Failed to book appointment! ");
                    }

                } catch (SQLException e) {
                    System.out.println("Exception: " + e.getMessage());
                }
            }
            else{
                System.out.println("Doctor not available on this date! ");
            }
        }
        else{
            System.out.println("Either doctor or patient doesn't exists");
        }
    }

    public static boolean doctorAvailable(int id, String appointmentDate, Connection connection) {

        String query = "SELECT COUNT(*) FROM appointments WHERE doctorId = ? AND appointmentDate = ?;";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, appointmentDate);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int count  = resultSet.getInt(1);
                if(count == 0){
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return false;
    }
}