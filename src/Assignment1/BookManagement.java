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

/**
 * Handles all book-related operations including:
 * - Book inventory management
 * - Search functionality
 * - Cost calculations
 */
public class BookManagement {

    static ArrayList<Book> allBooks = new ArrayList<>();

    /**
     * Constructor pre-populates with sample book data
     */
    public BookManagement() {
        this.addBook("Basic Java", "Peter Lim", 800372, 21.90, "Programming");
        this.addBook("Java Script Essential", "Tim Steven", 800837, 19.90, "Programming");
        this.addBook("ABC of Database", "Sue Ling", 800278, 15.90, "Programming");
        this.addBook("Clean Code", "Robert C. Martin", 978013, 32.99, "Programming");
        this.addBook("The Pragmatic Programmer", "Andrew Hunt", 978020, 28.50, "Software Engineering");
    }

    /**
     * Main book management menu loop
     */
    public void run() {

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
                    this.searchBookByTitle();
                    break;
                case "3":
                    this.promptAndAddBook();
                    break;
                case "4":
                    this.showTotalBooksCost();
                    break;
                case "5":
                    StudentLibrary.run(); // Return to main menu
                default:
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter 1, 2, 3, 4, or 5.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }

    /**
     * Adds a new book to inventory
     */
    public void addBook(String title, String author, Integer ISBN, Double price, String category) {
        Book book = new Book(title, author, ISBN, price, category);
        allBooks.add(book);
    }

    /**
     * Displays all books in formatted HTML table
     */
    public void displayAllBooks() {
        StringBuilder htmlTable = new StringBuilder("<html><table border='1'>");
        htmlTable.append("<tr><th>ISBN</th><th>Title</th><th>Author</th><th>Availability</th></tr>");
        for (int i=0; i<allBooks.size(); i++) {
            htmlTable.append(String.format(
                "<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",
                allBooks.get(i).getISBN(), 
                allBooks.get(i).getTitle(), 
                allBooks.get(i).getAuthor(), 
                allBooks.get(i).getAvailability()
            ));
        }
        
        htmlTable.append("</table></html>");
        JOptionPane.showMessageDialog(null, htmlTable.toString(), "Student List", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Searches for book by exact title match
     */
    public void searchBookByTitle() {
        Book searchedBook = null;

        while (true) {
            String searchTitle = JOptionPane.showInputDialog(null, "Enter the Book name to search", "Input", JOptionPane.QUESTION_MESSAGE);
            if (searchTitle == null) return;

            searchTitle = searchTitle.trim(); // clean the searchTitle input

            // Loop through all the library book objects to find the book
            for (Book b : allBooks) {
                if (b.getTitle().equals(searchTitle)) {
                    searchedBook = b; // Once book is found, searchBook is no longer null
                    break;
                }
            }

            // book object of searchTitle can be found
            if (searchedBook != null) 
                JOptionPane.showMessageDialog(null, "Book :\nBook Title : " + searchedBook.getTitle() + "\nBook Author : " + searchedBook.getAuthor() + "\nAvailability : " + searchedBook.getAvailability(), "Message", JOptionPane.INFORMATION_MESSAGE);
            else // book not found
                JOptionPane.showMessageDialog(null, "Cannot find the book \"" + searchTitle + "\"", searchTitle, JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handles new book addition with validation
     */
    public void promptAndAddBook() {
        String title = null;
        String author = null;
        String ISBN = null;
        String price = null;
        String category = null;
        
        while (true) {
            title = JOptionPane.showInputDialog(null, "Enter the new book title :", "Input", JOptionPane.QUESTION_MESSAGE);
            if (title == null) return;
            // Title validation
            else if (title.length() < 3) JOptionPane.showMessageDialog(null, "Book title must be at least 3 characters long.");
            else break;
        }

        while (true) {
            author = JOptionPane.showInputDialog(null, "Enter the new book author :", "Input", JOptionPane.QUESTION_MESSAGE);
            if (author == null) return;
            // Author validation
            else if (author.length() < 3) JOptionPane.showMessageDialog(null, "Book author must be at least 3 characters long.");
            else break;
        }

        while (true) {
            ISBN = JOptionPane.showInputDialog(null, "Enter the new book ISBN :", "Input", JOptionPane.QUESTION_MESSAGE);
            if (ISBN == null) return;
            else if (!ISBN.matches("\\d{1,}")) JOptionPane.showMessageDialog(null, "Book ISBN must be a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            else break;
        }

        while (true) {
            price = JOptionPane.showInputDialog(null, "Enter the new book price :", "Input", JOptionPane.QUESTION_MESSAGE);
            // Price validation
            if (price == null) return;
            else if (!price.matches("^\\d+(\\.\\d+)?$")) JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            else if (price.matches("^\\d+(\\.\\d+)?$")) {
                if (Double.parseDouble(price) < 5.0) JOptionPane.showMessageDialog(null, "Minimum price is $5.");
                else break;
            }
        }

        while (true) {
            category = JOptionPane.showInputDialog(null, "Enter the new book category :", "Input", JOptionPane.QUESTION_MESSAGE);
            // Category validation
            if (category == null) return;
            else if (category.length() < 3) JOptionPane.showMessageDialog(null, "Book category must be at least 3 characters long.");
            else break;
        }

        this.addBook(title, author, Integer.parseInt(ISBN), Double.parseDouble(price), category);
        JOptionPane.showMessageDialog(null, "Book Added", "Message", JOptionPane.INFORMATION_MESSAGE);
    
    }

    /**
     * Calculates and displays total inventory value
     */
    public void showTotalBooksCost() {
        Double totalBooksCost = 0.0;
        for (Book book : allBooks) {
            totalBooksCost += book.getPrice();
        }
        JOptionPane.showMessageDialog(null, "Total book cost is $" + totalBooksCost, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

}
