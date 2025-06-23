/*
    Name: Lau Chun Yi 
    Adm No: p2400149
    Class: DIT/FT/2B/22
*/
package Assignment1;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
/**
 * @author Lau Chun Yi 
 */

/**
 * Handles all student-related operations including:
 * - Student records management
 * - Book borrowing/returning functionality
 */
public class StudentManagement {
    private ArrayList<Student> students = new ArrayList<>();

    /**
     * Constructor pre-populates with sample student data
     */
    public StudentManagement() {
        this.addStudent("p111111", "John Tan");
        this.addStudent("p222222", "Peter Goh");
        this.addStudent("p333333", "Jack Lim");
        this.addStudent("p444444", "Ken Lau");
        this.addStudent("p555555", "Shawn Chan");
        this.addStudent("p666666", "Sam Khoo");
        this.addStudent("p777777", "Steve");
        this.addStudent("p888888", "Max Loh");
    }

    /**
     * Main student management menu loop
     */
    public void run() {
        
        while (true) {
            String inputNo = JOptionPane.showInputDialog(
                    null,
                    "Enter your option:\n\n1. Display all students\n2. Search student by name\n3. Add new student\n4. Display total number of students\n5. Borrow Book\n6 Return Book\n7. Exit",
                    "Mini Library System - Student Management",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (inputNo == null) {
                JOptionPane.showMessageDialog(null, "Program exited.");
                System.exit(0);
            }

            inputNo = inputNo.trim();

            switch (inputNo) {
                case "1":
                    this.displayAllStudents();
                    break;

                case "2":
                    this.searchStudentByName();
                    break;

                case "3":
                    this.promptAndAddStudent();
                    break;

                case "4":
                    this.showTotalStudents();
                    break;

                case "5": // Handle book borrowing
                    this.promptAndBorrowBook();
                    break;

                case "6":
                    this.promptAndReturnBook();
                    break;

                case "7":
                    StudentLibrary.run(); // Return to main menu

                default:
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter 1–6.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }


    /**
     * Adds a new student to the system
     */
    public void addStudent(String adminNumber, String name) {
        Student student = new Student(adminNumber, name);
        this.students.add(student);
    }

    /**
     * Displays all students in formatted HTML table
     */
    public void displayAllStudents() {
        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No students found.");
            return;
        }

        StringBuilder htmlTable = new StringBuilder("<html><table border='1'>");
        htmlTable.append("<tr><th>Admin#</th><th>Name</th><th>Books</th></tr>");
        
        for (Student s : students) {
            htmlTable.append(String.format(
                "<tr><td>%s</td><td>%s</td><td>%s</td></tr>",
                s.getAdminNumber(),
                s.getName(),
                s.getBooks().isEmpty() ? "None" : s.getBooks()
            ));
        }
        
        htmlTable.append("</table></html>");
        
        JOptionPane.showMessageDialog(null, htmlTable.toString(), 
            "Student List", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Searches for a student by name
     */
    public void searchStudentByName() {
        Student searchedStudent = null;

        while (true) {
            String searchName = JOptionPane.showInputDialog("Enter the Student name to search:");
            if (searchName == null) // Handle cancel/close
                return;

            searchName = searchName.trim(); // Clean the searchName input
        
            // Loop through each student object
            for (Student s : this.students) {
                if (s.getName().equals(searchName)) {
                    searchedStudent = s; // Searched student object
                    break;
                }
            }

            // searchedStudent is truthy; student object of searchName can be found
            if (searchedStudent != null) {
                JOptionPane.showMessageDialog(
                    null, 
                    String.format(
                        "<html><table border='1'><tr><th>Admin#</th><th>Name</th><th>Books</th></tr><tr><td>%s</td><td>%s</td><td>%s</td></tr></table></html>", 
                        searchedStudent.getAdminNumber(), 
                        searchedStudent.getName(), 
                        searchedStudent.getBooks()
                    ), 
                    "Student Found", 
                    JOptionPane.INFORMATION_MESSAGE
                );
                return;

            // Student object of searchName cannot be found
            } else JOptionPane.showMessageDialog(null, "Cannot find the student '" + searchName + "'!!!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handles new student creation with validation
     */
    public void promptAndAddStudent() {

        String adminNo = null;
        String name = null;

        while (true) {
            adminNo = JOptionPane.showInputDialog("Enter the new student admin number:");
            if (adminNo == null) return; // User clicks close/cancel
            
            adminNo = adminNo.trim(); // Clean the adminNo input
            
            if (!adminNo.matches("^[pP]\\d{6}$")) // Admin number matches format: p*******
                JOptionPane.showMessageDialog(null, "Format of Admin Number must be a 'p' followed by seven numerical digits.\nE.g., p242482, p219472, p238274");
            else // Admin number doesn't match format
                break;
        }

        while (true) {
            name = JOptionPane.showInputDialog("Enter the new student name:");
            if (name == null) return; // User clicks cancel/close

            name = name.trim(); // Clean the name input

            if (name.length() < 3) // Name is less than 3 characters long
                JOptionPane.showMessageDialog(null, "Name must be at least 3 characters long.");
            else // Name is at least 3 characters long
                break;
        }
    
        this.addStudent(adminNo, name);
        JOptionPane.showMessageDialog(null, "Student added successfully.");
    
    }

    /**
     * Displays total student count
     */
    public void showTotalStudents() {
        JOptionPane.showMessageDialog(null, "Total number of students: " + students.size());
    }

    /**
     * Handles book borrowing process
     */
    public void promptAndBorrowBook() {
        Student student = null; // Student object that borrowed the book
        Book borrowedBook = null; // Borrowed book object

        // Student lookup
        while (true) {
            String adminNo = JOptionPane.showInputDialog(null, "Adm No", "Input", JOptionPane.QUESTION_MESSAGE);
            if (adminNo == null) return; // User clicks cancel/close
            for (Student s : students) {
                if (s.getAdminNumber().equals(adminNo)) {
                    student = s; // Found student object
                    break;
                }
            }
            // admin number found
            if (student != null) break;
            // admin number not found
            else JOptionPane.showMessageDialog(null, "Student " + adminNo + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Book borrowing
        while (true) {
            String ISBN = JOptionPane.showInputDialog(null, "Book ISBN", "Input", JOptionPane.QUESTION_MESSAGE);
            if (ISBN == null) return; // User clicks cancel/close
            for (Book b : BookManagement.allBooks) {
                if (ISBN.equals(Integer.toString(b.getISBN()))) {
                    borrowedBook = b;
                    break;
                }
            }
            // Book can be found
            if (borrowedBook != null) {
                // book is available
                if (borrowedBook.getAvailability() == true) {
                    student.borrowBook(borrowedBook); // This method also sets the book availability to false
                    JOptionPane.showMessageDialog(null, "Book borrowed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
                    // book is unavailable
                    JOptionPane.showMessageDialog(null, "Book ISBN " + ISBN + " is currently unavailable.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            // Book cannot be found
            else JOptionPane.showMessageDialog(null, "Book ISBN " + ISBN + " not found.", "Error", JOptionPane.ERROR_MESSAGE);            
        }
    }

    public void promptAndReturnBook() {
        Student student = null; // object of student who wants to return the book object
        Book returnBook = null;

        while (true) {
            // Student lookup
            String adminNo = JOptionPane.showInputDialog("Enter student admin number:");
            if (adminNo == null) return; // User clicks close/cancel
    
            for (Student s : students) {
                if (s.getAdminNumber().equals(adminNo)) {
                    student = s; // Found student object
                    break;
                }
            }
    
            // Admin number found
            if (student != null) {
                // Student has borrowed books
                if (!student.getBooks().isEmpty()) break;
                // Student has no borrowed books
                else JOptionPane.showMessageDialog(null, "This student has no books to return!");
            }
            // Admin number not found
            else JOptionPane.showMessageDialog(null, "Student not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        while (true) {
            List<Integer> borrowedBookISBNs = student.getBooks().stream()
                    .map(Book::getISBN)
                    .collect(Collectors.toList());
            List<String> borrowedBookTitles = student.getBooks().stream()
                    .map(Book::getTitle)
                    .collect(Collectors.toList());

            String borrowedBookTitlesAndISBNs = "";

            for (int i=0; i<student.getBooks().size(); i++) {
                borrowedBookTitlesAndISBNs += (borrowedBookTitles.get(i) + " (ISBN: " + borrowedBookISBNs.get(i) + ")\n");
            }

            String returnBookISBN = JOptionPane.showInputDialog(
                String.format(
                    "Books borrowed by %s\n%s\n\nEnter book ISBN to return:", 
                    student.getName(), 
                    borrowedBookTitlesAndISBNs
                ));
    
            if (returnBookISBN == null) return; // User clicks close/cancel

            returnBookISBN = returnBookISBN.trim(); // Clean the input string
    
            for (int i=0; i<student.getBooks().size(); i++) {
                Book b = student.getBooks().get(i);
                if (Integer.toString(b.getISBN()).equals(returnBookISBN)) {
                    student.removeBook(i);
                    returnBook = b; // If student has this book, returnBook is no longer null
                    break;
                }
            }

            // return book ISBN is inside the list of the student's borrowed books
            if (returnBook != null) {
                for (Book b : BookManagement.allBooks) {
                    if (Integer.toString(b.getISBN()).equals(returnBookISBN)) {
                        b.setAvailability(true); // Update book availability
                        break;
                    }
                }
                JOptionPane.showMessageDialog(null, "Book returned successfully!");
                return;
                // return book title is not inside the list of the student's borrowed books
            } else JOptionPane.showMessageDialog(null, student.getName() + " didn't borrow that book!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
