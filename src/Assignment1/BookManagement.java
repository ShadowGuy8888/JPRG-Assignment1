/*
    Name: Jovan Yap Keat An
    Adm No: p2429407
    Class: DIT/FT/2B/22
*/
package Assignment1;

import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *
 * @author Jovan
 */
public class BookManagement {

    ArrayList<Book> allBooks = new ArrayList<>();

    public void run() {

        while (true) {
            String inputNo = JOptionPane.showInputDialog(null, "Enter your option: \n\n1. Display all books\n2. Search book by title\n3. Add new book\n4. Display total books costs\n5. Exit\n\n ", "Mini Library System - Book Management", JOptionPane.QUESTION_MESSAGE);
            if (inputNo == null) {
                System.exit(0);
            }

            inputNo = inputNo.trim();

            switch (inputNo) {
                case "1":
                    displayAllBooks();
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    StudentLibrary.run();
                default:
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter 1, 2, 3, 4, or 5.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }

    public void addBook(String title, String author, Integer ISBN, Double price, String category) {
        Book book = new Book(title, author, ISBN, price, category);
        allBooks.add(book);
    }

    private void displayAllBooks() {
        String text = "";
        for (int i=0; i<allBooks.size(); i++) {
            text += ("Book " + (i+1) + ": \nBook Title: " + allBooks.get(i).getTitle() + "\nBook Author: " + allBooks.get(i).getAuthor() + "\nAvailability: " + allBooks.get(i).getAvailability() + "\n\n");
        }
        JOptionPane.showMessageDialog(null, text, "All Books", JOptionPane.INFORMATION_MESSAGE);
    }

}
