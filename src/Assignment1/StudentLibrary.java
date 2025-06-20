/*
    Name: Jovan Yap Keat An
    Adm No: p2429407
    Class: DIT/FT/2B/22
*/
package Assignment1;

import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author Jovan
 */
public class StudentLibrary {

    public static void run() {
        while (true) {
            String inputNo = JOptionPane.showInputDialog(
                null,
                "Enter your option:\n\n1) Student management\n2) Books in library\n3) Exit",
                "Mini Library System",
                JOptionPane.QUESTION_MESSAGE
            );

            // Handle cancel or close button
            if (inputNo == null) {
                JOptionPane.showMessageDialog(null, "Program exited.");
                break;
            }

            inputNo = inputNo.trim();

            switch (inputNo) {
                case "1":
                    // Call your Student Management class/method here
                    StudentManagement studentManagement = new StudentManagement();
                    studentManagement.run();
                    break;

                case "2":
                    // Call your Books in Library class/method here
                    BookManagement bookManagement = new BookManagement();
                    bookManagement.run();
                    break;

                case "3":
                    JOptionPane.showMessageDialog(null, "Exiting program.");
                    System.exit(0);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter 1, 2, or 3.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }
    public static void main(String ...args) {
        run();
    }
}
