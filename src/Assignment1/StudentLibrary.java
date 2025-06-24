/*
    Name: Jovan Yap Keat An
    Adm No: p2429407
    Class: DIT/FT/2B/22
*/
package Assignment1;

import java.util.Arrays;
import javax.swing.JOptionPane;


 /**
  * @author Jovan Yap
  * Main class for the Student Library System
  * Acts as the entry point and main menu controller
  */
public class StudentLibrary {
    // Static instances of management classes
    private static StudentManagement studentManagement = new StudentManagement();
    private static BookManagement bookManagement = new BookManagement();

    /**
     * Main application loop that displays the primary menu
     */
    public static void run() {
        while (true) {
            // Display main menu options
            String inputNo = JOptionPane.showInputDialog(
                null,
                "Enter your option:\n\n1) Student management\n2) Books in library\n3) Exit",
                "Mini Library System",
                JOptionPane.QUESTION_MESSAGE
            );

            // Handle window close/cancel
            if (inputNo == null) {
                JOptionPane.showMessageDialog(null, "Program exited.");
                break;
            }

            inputNo = inputNo.trim(); // Clean input

            // Route to appropriate subsystem
            switch (inputNo) {
                case "1":
                    studentManagement.run(); // Launch student management
                    break;

                case "2":
                    bookManagement.run(); // Launch book management
                    break;

                case "3":
                    JOptionPane.showMessageDialog(null, "Exiting program.");
                    System.exit(0);
                    break;

                default:
                    AudioPlayer.playSound("src/Assignment1/sounds/huh.wav");
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter 1, 2, or 3.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }

    /**
     * Application entry point
     */
    public static void main(String ...args) {
        AudioPlayer.playSound("src/Assignment1/sounds/greeting.wav");
        run();
    }
}
