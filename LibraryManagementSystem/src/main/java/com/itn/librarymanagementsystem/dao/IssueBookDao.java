/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itn.librarymanagementsystem.dao;

import com.itn.librarymanagementsystem.entity.Book;
import com.itn.librarymanagementsystem.entity.IssueBook;
import com.itn.librarymanagementsystem.entity.Member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author bijay
 */
public class IssueBookDao {
    
    public Connection startConnection(){
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementdatabase", "root", "CallMeRonaldo@7");
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex);
        } catch (ClassNotFoundException ex) {
         System.out.println("Exception: " + ex);
     }
        return con;
    }
    
    public boolean issueBook(IssueBook issue, Member member, Book book){
        Connection con = startConnection();
        String query = "INSERT INTO issueTable(memberId, bookId, edition, issueDate, returnDate) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, issue.getMemberId());
            ps.setInt(2, issue.getBookId());
            ps.setString(3, issue.getEdition());
            ps.setDate(4, issue.getIssueDate());
            ps.setDate(5, issue.getReturnDate());
            
           if(memberExits(member) && bookAvailable(book)){
               int affectedRows = ps.executeUpdate();
               if(affectedRows > 0){
                   boolean decreased = decreaseQuantity(book);
                   if(decreased){
                       return true;
                   }
               }
           }
           
        } catch (SQLException ex) {
             System.out.println("Exception: " + ex);
        }
        return false;
    }

    private boolean memberExits(Member member) {
        Connection con = startConnection();
        String query = "SELECT * FROM members WHERE id = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, member.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
            
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex);
        }
        return false;
    }

    private boolean bookAvailable(Book book) {
        Connection con = startConnection();
        String query = "SELECT quantity FROM books WHERE id = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, book.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int quantity = rs.getInt("quantity");
                if(quantity > 0){
                    return true;
                }
            }
            
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex);
        }
        return false;
    }

    private boolean decreaseQuantity(Book book) {
        Connection con = startConnection();
        String query = "UPDATE books SET quantity = quantity - 1 WHERE id = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, book.getId());
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0){
                return true;
            }
            
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex);
        }
        return false;
    }
    
}
