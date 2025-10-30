import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class HotelReservationSystem {

    // URL, Username and password of database
    private static final String URL = "Put your Driver URl Here";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";

    public static void main(String[] args) {

        // Loading drivers here
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Exception: " + e);
        }

        // Declaring outside so we can close it in finally block
        Connection connection;
        Statement statement;
        Scanner scanner = new Scanner(System.in);

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            while (true){
                mainMenu();

                System.out.print("Choose an option: ");
                int option = scanner.nextInt();

                switch (option){
                    case 1 -> reserveRoom(scanner, statement);
                    case 2 -> viewReservation(statement);
                    case 3 -> getRoomNumber(scanner, statement);
                    case 4 -> updateReservation(scanner, statement);
                    case 5 -> deleteReservation(scanner, statement);
                    case 0 -> {
                        exit();
                        scanner.close();
                        statement.close();
                        connection.close();
                        System.exit(0);
                    }
                    default -> System.out.println("Enter a valid choice! ");
                }
            }

        } catch (SQLException e) {
            System.out.println("Exception: " + e);
        }

    }

    private static void mainMenu() {
        System.out.println();
        System.out.println("HOTEL MANAGEMENT SYSTEM");
        System.out.println();
        System.out.println("1. Reserve a room");
        System.out.println("2. View Reservation");
        System.out.println("3. Get Room Number");
        System.out.println("4. Update Reservation");
        System.out.println("5. Delete Reservation");
        System.out.println("0. Exit ");
        System.out.println();

    }

    // Method to reserve a room
    private static void reserveRoom(Scanner scanner, Statement statement) {

        // Taking customers details input
        System.out.print("Enter guest name: ");
        String name = scanner.next();
        scanner.nextLine();
        System.out.print("Enter room number: ");
        int roomNum = scanner.nextInt();
        System.out.print("Enter contact number: ");
        String contactNum = scanner.next();

        // SQL Query to insert
        String query = "INSERT INTO reservations (guestName, roomNumber, contactNumber)" +
                       "VALUES ('" + name + "', '" + roomNum + "', '" + contactNum + "');";

        try{
            int affectedRows = statement.executeUpdate(query);

            if(affectedRows > 0){
                System.out.println("Reservation Successful!");
            }
            else{
                System.out.println("Reservation Failed! ");
            }

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    // Method to view reservations
    private static void viewReservation(Statement statement) {

        // SQL Query to read
        String query = "SELECT reservationID, guestName, roomNumber, contactNumber, reservationDate FROM reservations;";

        try {

            ResultSet resultSet = statement.executeQuery(query);

            // Displaying Reservations
            System.out.println();
            System.out.println("Reservations Details: ");
            while (resultSet.next()){
                int reservationId = resultSet.getInt("reservationID");
                String guestName = resultSet.getString("guestName");
                int roomNumber = resultSet.getInt("roomNumber");
                String contactNumber = resultSet.getString("contactNumber");
                String reservationDate = resultSet.getTimestamp("reservationDate").toString();

                System.out.println();
                System.out.printf("[Reservation ID: %d, Guest Name: %s, Room Number: %d, Contact Number: %s, Reservation Date: %s]"
                        , reservationId, guestName, roomNumber, contactNumber, reservationDate);
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }

    }

    // Method to get room number
    public static void getRoomNumber(Scanner scanner, Statement statement){

        System.out.print("Enter reservation ID: ");
        int reservationID = scanner.nextInt();
        System.out.print("Enter guest name: ");
        String guestName = scanner.next();

        // SQL Query to get id
        String query = "SELECT roomNumber FROM reservations WHERE " +
                       " reservationID = " + reservationID + " AND guestName = '" + guestName + "';";

        try {

            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next()){
                int roomNumber = resultSet.getInt("roomNumber");
                System.out.println("Room number for Reservation ID " + reservationID +
                                   " and Guest " + guestName + " is: " + roomNumber);
            }
            else{
                System.out.println("Sorry, Reservation not found for the given ID and guest name.");
            }

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }

    }

    // Method to update reservation
    public static void updateReservation(Scanner scanner, Statement statement){

        System.out.print("Enter reservation ID to update: ");
        int reservationID = scanner.nextInt();
        scanner.nextLine();

        if(!reservationExits(reservationID, statement)){
            System.out.println("Reservation not found for the given ID");
            return;
        }

        System.out.print("Enter new guest name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new room number: ");
        int newRoomNumber = scanner.nextInt();
        System.out.print("Enter new contact number: ");
        String newContactNumber = scanner.next();

        // SQL Query to update
        String query = "UPDATE reservations SET guestName = '" + newName + "', " +
                "roomNumber = " + newRoomNumber + ", " + "contactNumber = '" + newContactNumber  + "' WHERE " +
                "reservationID = " + reservationID + ";";

        try {
            int affectedRows = statement.executeUpdate(query);

            if(affectedRows > 0){
                System.out.println("Reservation Updated Successfully");
            }
            else{
                System.out.println("Reservation Update Failed");
            }

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }

    }

    // Method to delete reservation
    public static void deleteReservation(Scanner scanner, Statement statement){

        System.out.print("Enter reservation ID to delete: ");
        int reservationId = scanner.nextInt();

        if(!reservationExits(reservationId, statement)){
            System.out.println("Reservation not found for the given ID");
            return;
        }

        // SQL Query to delete
        String query = "DELETE FROM reservations WHERE reservationID = " + reservationId;

        try {
            int affectedRows = statement.executeUpdate(query);

            if(affectedRows > 0){
                System.out.println("Reservation Deleted Successfully");
            }
            else{
                System.out.println("Reservation Delete Failed");
            }

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    // Method to check if reservation exits or not
    public static boolean reservationExits(int reservationId, Statement statement){
        // SQL Query SELECT
        String query = "SELECT reservationId FROM reservations WHERE reservationId = " + reservationId;

        try {
            ResultSet resultSet = statement.executeQuery(query);

            return resultSet.next(); // This returns either boolean value(true or false)

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }

    }

    // Method for exiting program including sleep method of Thread class
    public static void exit(){

        System.out.print("Exciting System");
        int i = 5;
        try {

        while (i != 0){
            System.out.print(".");
                Thread.sleep(450);
            i--;
            }
        } catch (InterruptedException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        System.out.println();
        System.out.println("Thank you for using Hotel Management System !!!");
    }
}