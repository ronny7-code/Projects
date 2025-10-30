/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itn.librarymanagementsystem.dao;

import com.itn.librarymanagementsystem.entity.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ITN
 */
public class BookDao {
    
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/librarymanagementdatabase";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "CallMeRonaldo@7";
    
    public boolean addBook(Book book){
        try {
            Class.forName(DRIVER_CLASS_NAME);
        } catch (ClassNotFoundException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        
        Connection connection = null;
        PreparedStatement ps = null;
        String query = "INSERT INTO books(bookName, author, genre, isbn, edition, quantity, price, addedDate) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?);";
        
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            ps = connection.prepareStatement(query);
            
            ps.setString(1, book.getName());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getGenre());
            ps.setString(4, book.getIsbn());
            ps.setString(5, book.getEdition());
            ps.setInt(6, book.getQuantity());
            ps.setDouble(7, book.getPrice());
            ps.setDate(8,book.getAddedDate());
            
            int success = ps.executeUpdate();
            
            if(success > 0){
                return true;
            }
            
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        finally{
            try {
                if(ps != null){
                    ps.close();
                }
                if(connection != null){
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        }
        return false;
 }
    
     public ArrayList<Book> getAllBooks(){
        ArrayList<Book> bookList = new ArrayList<>();
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
   
        try {
            Class.forName(DRIVER_CLASS_NAME);
            Book book = null;
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String query = "SELECT * FROM books WHERE showData = 0";
            stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
          
            while(rs.next()){
                
                book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("bookName"));
                book.setAuthor(rs.getString("author"));
                book.setIsbn(rs.getString("isbn"));
                book.setEdition(rs.getString("edition"));
                book.setPrice(rs.getDouble("price"));
                book.setQuantity(rs.getInt("quantity"));
                book.setAddedDate(rs.getDate("addedDate"));
                book.setGenre(rs.getString("genre"));
                
                bookList.add(book);
            }
            
            
        } catch (ClassNotFoundException e) {
            System.out.println("Exception for class: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Exception for connection: " + e.getMessage());
        }
          finally{
            
            try {
                if(stmt != null){
                stmt.close();
                }
                 if(connection != null){
                connection.close();
                }
                
            } catch (SQLException e) {
               System.out.println("Exception while closing: " + e.getMessage());
            }
        }
        return bookList;
    }
 
     
     
    public boolean editBook(Book book){
        boolean status = false;
        Connection connection = null;
        PreparedStatement ps = null;
        String queryToUpdate = "UPDATE books SET bookName = ?, author = ?, genre = ?, isbn = ?, edition = ?, quantity = ?,"
                + "price = ?, addedDate = ? WHERE id = ? ;";
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            ps = connection.prepareStatement(queryToUpdate);
            ps.setString(1, book.getName());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getGenre());
            ps.setString(4, book.getIsbn());
            ps.setString(5, book.getEdition());
            ps.setInt(6, book.getQuantity());
            ps.setDouble(7, book.getPrice());
            ps.setDate(8,book.getAddedDate());
            ps.setInt(9, book.getId());
            
            int affectedRows = ps.executeUpdate();
            
            if(affectedRows > 0){
                status = true;
            }
            
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        finally{
            try {
                if(ps != null){
                ps.close();
                }
                if(connection != null){
                connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        }
        return status;
    }
    
       public boolean deleteInfo(Book book){
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            Class.forName(DRIVER_CLASS_NAME);
        } catch (ClassNotFoundException ex) {
           System.out.println("Exception: " + ex.getMessage());
        }
        
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String query = "UPDATE books SET showData = 1";
            stmt = connection.prepareStatement(query);
            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected > 0){
                return true;
            }
            
        } catch (SQLException ex) {
           System.out.println("Exception: " + ex.getMessage());
        }
        finally{
            
            try {
                if(stmt != null){
                stmt.close();
                }
                 if(connection != null){
                connection.close();
                }
                
            } catch (SQLException e) {
               System.out.println("Exception while closing: " + e.getMessage());
            }
        }
        return false;
    }

}