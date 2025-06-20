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

    static ArrayList<Book> allBooks = new ArrayList<>();

    public void run() {

        this.addBook("Basic Java", "Peter Lim", 800372, 21.90, "Programming");
        this.addBook("Java Script Essential", "Tim Steven", 800837, 19.90, "Programming");
        this.addBook("ABC of Database", "Sue Ling", 800278, 15.90, "Programming");
        this.addBook("Clean Code", "Robert C. Martin", 978013, 32.99, "Programming");
        this.addBook("The Pragmatic Programmer", "Andrew Hunt", 978020, 28.50, "Software Engineering");

        while (true) {
            String inputNo = JOptionPane.showInputDialog(null, "Enter your option: \n\n1. Display all books\n2. Search book by title\n3. Add new book\n4. Display total books costs\n5. Exit\n\n ", "Mini Library System - Book Management", JOptionPane.QUESTION_MESSAGE);
            if (inputNo == null) {
                JOptionPane.showMessageDialog(null, "Program exited.");
                System.exit(0);
            }

            inputNo = inputNo.trim();

            switch (inputNo) {
                case "1":
                    this.displayAllBooks();
                    break;
                case "2":
                    while (true) {
                        String title = JOptionPane.showInputDialog(null, "Enter the Book name to search", "Input", JOptionPane.QUESTION_MESSAGE);
                        if (title == null) 
                            break;
                        Book foundBook = this.searchBookByTitle(title);
                        if (foundBook != null) 
                            JOptionPane.showMessageDialog(null, "Book :\nBook Title : " + foundBook.getTitle() + "\nBook Author : " + foundBook.getAuthor() + "\nAvailability : " + foundBook.getAvailability(), "Message", JOptionPane.INFORMATION_MESSAGE);
                        else 
                            JOptionPane.showMessageDialog(null, "Cannot find the book \"" + title + "\"", title, JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "3":
                    String title;
                    String author;
                    Integer ISBN;
                    Double price;
                    String category;
                    
                    while (true) {
                        title = JOptionPane.showInputDialog(null, "Enter the new book title :", "Input", JOptionPane.QUESTION_MESSAGE);
                        if (title == null || title.length() >= 3) 
                            break;
                        JOptionPane.showMessageDialog(null, "Book title must be at least 3 characters long.");
                    }
                    if (title == null) 
                        break;

                    while (true) {
                        author = JOptionPane.showInputDialog(null, "Enter the new book author :", "Input", JOptionPane.QUESTION_MESSAGE);
                        if (author == null || author.length() >= 3) 
                            break;
                        JOptionPane.showMessageDialog(null, "Book author must be at least 3 characters long.");
                    }
                    if (author == null) 
                        break;

                    while (true) {
                        ISBN = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the new book ISBN :", "Input", JOptionPane.QUESTION_MESSAGE));
                        if (ISBN == null || String.valueOf(Math.abs(ISBN)).length() >= 6) 
                            break;
                        JOptionPane.showMessageDialog(null, "Book ISBN must be at least 6 digits long.");
                    }
                    if (ISBN == null) 
                        break;

                    while (true) {
                        price = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the new book price :", "Input", JOptionPane.QUESTION_MESSAGE));
                        if (price == null || price >= 5.0) 
                            break;
                        JOptionPane.showMessageDialog(null, "Minimum price is $5.");
                    }
                    if (price == null) 
                        break;

                    while (true) {
                        category = JOptionPane.showInputDialog(null, "Enter the new book category :", "Input", JOptionPane.QUESTION_MESSAGE);
                        if (category == null || category.length() >= 3) 
                            break;
                        JOptionPane.showMessageDialog(null, "Book category must be at least 3 characters long.");
                    }
                    if (title == null) 
                        break;

                    this.addBook(title, author, ISBN, price, category);
                    JOptionPane.showMessageDialog(null, "Book Added", "Message", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "4":
                    Double totalBooksCost = 0.0;
                    for (Book book : allBooks) {
                        totalBooksCost += book.getPrice();
                    }
                    JOptionPane.showMessageDialog(null, "Total book cost is $" + totalBooksCost, "Message", JOptionPane.INFORMATION_MESSAGE);
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
        // String text = "";
        // for (int i=0; i<allBooks.size(); i++) {
        //     text += ("Book " + (i+1) + ": \nBook Title: " + allBooks.get(i).getTitle() + "\nBook Author: " + allBooks.get(i).getAuthor() + "\nAvailability: " + allBooks.get(i).getAvailability() + "\n\n");
        // }
        // JOptionPane.showMessageDialog(null, text, "All Books", JOptionPane.INFORMATION_MESSAGE);
        StringBuilder htmlTable = new StringBuilder("<html><table border='1'>");
        htmlTable.append("<tr><th>ID</th><th>ISBN</th><th>Title</th><th>Author</th><th>Availability</th></tr>");
        for (int i=0; i<allBooks.size(); i++) {
            htmlTable.append(String.format(
                "<tr><td>%s</td><td></td><td>%s</td><td>%s</td><td>%s</td></tr>",
                i+1, 
                allBooks.get(i).getISBN(), 
                allBooks.get(i).getTitle(), 
                allBooks.get(i).getAuthor(), 
                allBooks.get(i).getAvailability()
            ));
        }
        
        htmlTable.append("</table></html>");
        JOptionPane.showMessageDialog(null, htmlTable.toString(), "Student List", JOptionPane.INFORMATION_MESSAGE);
    }

    private Book searchBookByTitle(String title) {
        for (Book book : allBooks) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

}
