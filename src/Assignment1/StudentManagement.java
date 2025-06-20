/*
    Name: Lau Chun Yi 
    Adm No: p2400149
    Class: DIT/FT/2B/22
*/
package Assignment1;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/**
 *
 * @author Lau Chun Yi 
 */
public class StudentManagement {
    private ArrayList<Student> students = new ArrayList<>();

    public void run() {
        this.addStudent("p111111", "John Tan");
        this.addStudent("p222222", "Peter Goh");
        this.addStudent("p333333", "Jack Lim");
        this.addStudent("p444444", "Ken Lau");
        this.addStudent("p555555", "Shawn Chan");
        this.addStudent("p666666", "Sam Khoo");
        this.addStudent("p777777", "Steve");
        this.addStudent("p888888", "Max Loh");
        
        while (true) {
            String inputNo = JOptionPane.showInputDialog(
                    null,
                    "Enter your option:\n\n1. Display all students\n2. Search student by name\n3. Add new student\n4. Display total number of students\n5. Borrow Book\n6. Exit",
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
                    this.searchStudent();
                    break;

                case "3":
                    this.addNewStudent();
                    break;

                case "4":
                    this.showTotalStudents();
                    break;

                case "5":
                    this.borrow();
                    break;

                case "6":
                    StudentLibrary.run();

                default:
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter 1–6.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }

    // Add student without books
    public void addStudent(String adminNumber, String name) {
        Student student = new Student(adminNumber, name);
        students.add(student);
    }

    private void displayAllStudents() {
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

    private void searchStudent() {
        String searchName;
        Student searchedStudent = null;
        while (true) {
            searchName = JOptionPane.showInputDialog("Enter the Student name to search:");
            if (searchName == null)  // User clicked cancel
                return; 
            // else if (!searchName.trim().isEmpty()) {
            //     break;
            // }
            // JOptionPane.showMessageDialog(null, "Cannot be empty");
        
            for (int i=0; i<students.size(); i++) {
                Student s = students.get(i);
                if (s.getName().equals(searchName.trim())) {
                    searchedStudent = s;
                    break;
                }
            }
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
                break;
            }
            JOptionPane.showMessageDialog(null, "Cannot find the student '" + searchName + "'!!!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addNewStudent() {
        String adminNo = JOptionPane.showInputDialog("Enter the new student admin number:");
        if (adminNo == null || adminNo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Admin number is required. Student not added.");
            return;
        }  
        adminNo = adminNo.trim();

        String name = JOptionPane.showInputDialog("Enter the new student name:");
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name is required. Student not added.");
            return;
        }
        name = name.trim();

        addStudent(adminNo, name);
        JOptionPane.showMessageDialog(null, "Student added successfully.");
    }

    private void showTotalStudents() {
        JOptionPane.showMessageDialog(null, "Total number of students: " + students.size());
    }

    private void borrow() {
        Student student = null;
        Book borrowedBook = null;

        while (true) {
            String adminNo = JOptionPane.showInputDialog(null, "Adm No", "Input", JOptionPane.QUESTION_MESSAGE);
            if (adminNo == null) break;
            for (Student s : students) {
                if (s.getAdminNumber().equals(adminNo)) {
                    student = s;
                    break;
                }
            }
            if (student != null) break;
            JOptionPane.showMessageDialog(null, "Student " + adminNo + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        while (student != null) {
            String ISBN = JOptionPane.showInputDialog(null, "Book ISBN", "Input", JOptionPane.QUESTION_MESSAGE);
            if (ISBN == null) break;
            for (Book b : BookManagement.allBooks) {
                if (Integer.toString(b.getISBN()).equals(ISBN)) {
                    borrowedBook = b;
                    break;
                }
            }
            if (borrowedBook != null) {
                if (borrowedBook.getAvailability() == true) {
                    student.borrowBook(borrowedBook.getTitle());
                    JOptionPane.showMessageDialog(null, "Book borrowed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                JOptionPane.showMessageDialog(null, "Book ISBN " + ISBN + " is currently unavailable.", "Error", JOptionPane.ERROR_MESSAGE);
            };
            JOptionPane.showMessageDialog(null, "Book ISBN " + ISBN + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
