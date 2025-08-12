/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itn.librarymanagementsystem.dao;

import com.itn.librarymanagementsystem.entity.Member;
//import java.sql.Statement;
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
public class MemberDao {
    
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/librarymanagementdatabase";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "CrisRonaldo@77";
    
    public boolean addMember(Member member){
        // this method receives an object of member class containing all member data
        // then saves the member data in database table and return true if success 
        // otherwise returns false
        
        boolean status = false;
        Connection conn = null;
//        Statement stmt;
        PreparedStatement ps = null;
        
        try{
            // Step 1 : Load mysql database driver
            Class.forName(DRIVER_CLASS_NAME);
            
            // Step 2 : Create database connection with the help of driver manager
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            
            // Step 3 : Create statement object
//            stmt = conn.createStatement();

            //Step 4 : Write SQL query
// String sql = "INSERT INTO members(firstName, middleName, lastName, address, email, phoneNumber, gender, interest, membership)"
//            + " VALUES ('"
//            + member.getFirstName()+ "','" 
//            + member.getMidName() + "','" 
//            + member.getLastName() + "','" 
//            + member.getAddress()+ "','" 
//            + member.getEmail() + "','" 
//            + member.getPhoneNo() + "','" 
//            + member.getGender() + "','" 
//            + member.getInterest() + "','" 
//            + member.getMembershipType()+ "')";


            String sql =  "INSERT INTO members(firstName, middleName, lastName, address, email, phoneNumber, gender, interest, membership)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
   
            ps = conn.prepareStatement(sql);
 
            ps.setString(1, member.getFirstName());
            ps.setString(2, member.getMidName());
            ps.setString(3, member.getLastName());
            ps.setString(4, member.getAddress());
            ps.setString(5, member.getEmail());
            ps.setString(6, member.getPhoneNo());
            ps.setString(7, member.getGender());
            ps.setString(8, member.getInterest());
            ps.setString(9, member.getMembershipType());
 
            // Step 5 : Execute SQL using statement object's executeUpdate() method
//            int i = stmt.executeUpdate(sql);
            int i = ps.executeUpdate();
            
            if(i == 1){
                status = true;
            }
            
        }
        catch(ClassNotFoundException e){
            System.out.println("Exception: " + e.getMessage());
        }
        catch(SQLException e){
            System.out.println("Exception is: " + e.getMessage());
        }
        finally{
            try {
                if(ps != null){
                ps.close();
                }
                if(conn != null){
                conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Exception: " + e.getMessage());
            }
            
        }
        return status;
    }
    
    public ArrayList<Member> getAllMembers(){
        ArrayList<Member> memberList = new ArrayList<>();
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
   
        try {
            Class.forName(DRIVER_CLASS_NAME);
            
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String query = "SELECT * FROM members";
            stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
          
            while(rs.next()){
                
                Member member = new Member();
                member.setId(rs.getInt("id"));
                member.setFirstName(rs.getString("firstName"));
                member.setMidName(rs.getString("middleName"));
                member.setLastName(rs.getString("lastName"));
                member.setAddress(rs.getString("address"));
                member.setEmail(rs.getString("email"));
                member.setPhoneNo(rs.getString("phoneNumber"));
                member.setGender(rs.getString("gender"));
                member.setInterest(rs.getString("interest"));
                member.setMembershipType(rs.getString("membership"));
                
                memberList.add(member);
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
        return memberList;
    }
    
}
