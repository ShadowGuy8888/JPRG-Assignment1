/*
    Name: Lau Chun Yi
    Adm No: p2400149
    Class: DIT/FT/2B/22
*/
package Assignment1;

import java.util.ArrayList;

public class Student {
    private static int studentCount = 0;
    private int studentNumber;    
    private String name;
    private String adminNumber;
    private ArrayList<String> books;

    public Student(String adminNumber, String name) {
        this.adminNumber = adminNumber;
        this.name = name;
        this.books = new ArrayList<>();

        studentCount++; 
        this.studentNumber = studentCount; 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminNumber() {
        return adminNumber;
    }

    public void setAdminNumber(String adminNumber) {
        this.adminNumber = adminNumber;
    }

    public ArrayList<String> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<String> books) {
        this.books = books;
    }

    public void borrowBook(String book) {
        books.add(book);
    }

    @Override
    public String toString() {
        return "Student { name: " + this.name + ", admNo: " + this.adminNumber + ", books: " + this.books + " }";
    }
}
