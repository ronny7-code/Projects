/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itn.librarymanagementsystem.entity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author Asus
 */
public class Book {
    
    
    private int id;
    private String name;
    private String[] author;
    private String genre;
    private String isbn;
    private String edition;
    private int quantity;
    private double price;
    private LocalDate addedDate;
  
    public Book(){
        isbn = String.valueOf(java.util.UUID.randomUUID());
    }
    
    public Book(int id, String name, String[] author, String genre, String isbn, String edition, int quantity, double price, LocalDate addedDate) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.edition = edition;
        this.quantity = quantity;
        this.price = price;
        this.addedDate = addedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAuthor() {
        return author;
    }

    public void setAuthor(String[] author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public LocalDate getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDate addedDate) {
        this.addedDate = addedDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Arrays.deepHashCode(this.author);
        hash = 71 * hash + Objects.hashCode(this.genre);
        hash = 71 * hash + Objects.hashCode(this.isbn);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        return true;
    }

    
}