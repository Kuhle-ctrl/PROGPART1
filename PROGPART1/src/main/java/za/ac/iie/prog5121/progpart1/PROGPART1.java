/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package za.ac.iie.prog5121.progpart1;
import java.util.Scanner;
/**
 *
 * @author Student
 */


public class PROGPART1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login userLogin = new Login();

        // 1. Handle Registration and Login first (from Part 1)
        System.out.println("--- REGISTRATION ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter cell number: ");
        String cellNumber = scanner.nextLine();
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        String regStatus = userLogin.registerUser(username, password, cellNumber, firstName, lastName);
        System.out.println(regStatus);

        if (!regStatus.contains("successfully")) {
            return; // Exit if registration fails
        }

        System.out.println("\n--- LOGIN ---");
        System.out.print("Enter username: ");
        String loginUser = scanner.nextLine();
        System.out.print("Enter password: ");
        String loginPass = scanner.nextLine();

        boolean isLoggedIn = userLogin.loginUser(loginUser, loginPass);
        System.out.println(userLogin.returnLoginStatus(isLoggedIn));

        if (!isLoggedIn) {
            return; // Exit if login fails
        }

        // 2. QuickChat Menu Loop (Part 2 & 3 Integration)
        boolean running = true;
        while (running) {
            System.out.println("\n==============================");
            System.out.println(" QUICKCHAT MENU ");
            System.out.println("==============================");
            System.out.println("1. Send a message");
            System.out.println("2. Disregard a message");
            System.out.println("3. Store a message");
            System.out.println("4. Stored Messages Options");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 5) {
                running = false;
                System.out.println("Exiting QuickChat. Goodbye!");
                break;
            }

            if (choice >= 1 && choice <= 3) {
                System.out.print("Enter recipient cell number: ");
                String recipient = scanner.nextLine();
                System.out.println("Enter message text: ");
                String text = scanner.nextLine();
                System.out.println("Enter message number: ");
                int msgNum = scanner.nextInt();
                scanner.nextLine();

                Message msg = new Message(recipient, text, msgNum);
                
                // Validate message length and recipient format before handling
                if (msg.checkMessageLength() && msg.checkRecipientCell()) {
                    MessageManager.handleMessageAction(msg, choice);
                } else {
                    System.out.println("Error: Message exceeds 250 characters or invalid recipient format.");
                }
            } 
            else if (choice == 4) {
                // Stored Messages Sub-menu (Part 3 Requirements)
                boolean subRunning = true;
                while (subRunning) {
                    System.out.println("\n--- STORED MESSAGES MENU ---");
                    System.out.println("a. Display sender and recipient of all stored messages");
                    System.out.println("b. Display the longest stored message");
                    System.out.println("c. Search for a message ID");
                    System.out.println("d. Search messages by recipient");
                    System.out.println("e. Delete a message using message hash");
                    System.out.println("f. Display full stored messages report");
                    System.out.println("g. Return to main menu");
                    System.out.print("Select an option: ");
                    
                    String subChoice = scanner.nextLine().toLowerCase();

                    switch (subChoice) {
                        case "a":
                            MessageManager.displayStoredSendersAndRecipients();
                            break;
                        case "b":
                            System.out.println("Longest Message: " + MessageManager.getLongestStoredMessage());
                            break;
                        case "c":
                            System.out.print("Enter Message ID to search: ");
                            String searchId = scanner.nextLine();
                            MessageManager.searchByMessageID(searchId);
                            break;
                        case "d":
                            System.out.print("Enter Recipient cell to search: ");
                            String searchRecip = scanner.nextLine();
                            MessageManager.searchByRecipient(searchRecip);
                            break;
                        case "e":
                            System.out.print("Enter Message Hash to delete: ");
                            String hashDel = scanner.nextLine();
                            boolean deleted = MessageManager.deleteByHash(hashDel);
                            if (deleted) {
                                System.out.println("Message successfully deleted.");
                            } else {
                                System.out.println("Hash not found.");
                            }
                            break;
                        case "f":
                            MessageManager.displayFullReport();
                            break;
                        case "g":
                            subRunning = false;
                            break;
                        default:
                            System.out.println("Invalid sub-menu option.");
                    }
                }
            } else {
                System.out.println("Invalid main menu option.");
            }
        }
        scanner.close();
    }
}
