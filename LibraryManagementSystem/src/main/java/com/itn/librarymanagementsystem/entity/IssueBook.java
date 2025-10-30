/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itn.librarymanagementsystem.entity;

import java.sql.Date;

/**
 *
 * @author Asus
 */
public class IssueBook {
    private int memberId;
    private int bookId;
    private String edition;
    private Date issueDate;
    private Date returnDate;
    
    public IssueBook(){
        
    }
    
    public IssueBook(int memberId, int bookId, String edition, Date issueDate, Date returnDate){
        this.memberId = memberId;
        this.bookId = bookId;
        this.edition = edition;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }
    
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
            
}
