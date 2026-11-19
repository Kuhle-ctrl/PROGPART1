/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.iie.prog5121.progpart1;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    // Parallel / List storage for Part 3
    private static List<Message> sentMessages = new ArrayList<>();
    private static List<Message> disregardedMessages = new ArrayList<>();
    private static List<Message> storedMessages = new ArrayList<>();

    // Add message based on action choice
    public static void handleMessageAction(Message msg, int choice) {
        switch (choice) {
            case 1:
                sentMessages.add(msg);
                System.out.println("Message successfully sent.");
                break;
            case 2:
                disregardedMessages.add(msg);
                System.out.println("Press 0 to delete the message.\nMessage successfully deleted.");
                break;
            case 3:
                storedMessages.add(msg);
                msg.storeMessageJSON();
                System.out.println("Message successfully stored.");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    // a. Display sender and recipient of all stored messages
    public static void displayStoredSendersAndRecipients() {
        System.out.println("\n--- Stored Messages (Sender & Recipient) --");
        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages found.");
            return;
        }
        for (Message m : storedMessages) {
            System.out.println("Recipient: " + m.getRecipientCell() + " | Message ID: " + m.getMessageID());
        }
    }

    // b. Display the longest stored message
    public static String getLongestStoredMessage() {
        if (storedMessages.isEmpty()) return "No stored messages.";
        Message longest = storedMessages.get(0);
        for (Message m : storedMessages) {
            if (m.getMessageText().length() > longest.getMessageText().length()) {
                longest = m;
            }
        }
        return longest.getMessageText();
    }

    // c. Search for a message ID and display corresponding recipient and message
    public static void searchByMessageID(String targetID) {
        boolean found = false;
        for (Message m : storedMessages) {
            if (m.getMessageID().equals(targetID)) {
                System.out.println("Found! Recipient: " + m.getRecipientCell() + " | Message: " + m.getMessageText());
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Message ID not found.");
    }

    // d. Search for all messages stored for a particular recipient
    public static void searchByRecipient(String targetRecipient) {
        System.out.println("\n-- Messages for Recipient: " + targetRecipient + " ---");
        boolean found = false;
        for (Message m : storedMessages) {
            if (m.getRecipientCell().equals(targetRecipient)) {
                System.out.println("- " + m.getMessageText());
                found = true;
            }
        }
        if (!found) System.out.println("No messages found for this recipient.");
    }

    // e. Delete a message using the message hash
    public static boolean deleteByHash(String targetHash) {
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).getMessageHash().equalsIgnoreCase(targetHash)) {
                storedMessages.remove(i);
                return true;
            }
        }
        return false;
    }

    // f. Display a report that lists the full details of all stored messages
    public static void displayFullReport() {
        System.out.println("\n=========== STORED MESSAGES REPORT ================");
        if (storedMessages.isEmpty()) {
            System.out.println("No messages in storage.");
            return;
        }
        for (Message m : storedMessages) {
            System.out.println("Hash: " + m.getMessageHash());
            System.out.println("ID: " + m.getMessageID());
            System.out.println("To: " + m.getRecipientCell());
            System.out.println("Text: " + m.getMessageText());
            System.out.println("-----------------------");
        }
    }
         public String[] readStoredMessagesFromJson(String filePath) {
        try {
            // Read JSON file contents into a string
            String jsonContent = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filePath)));
            
            // Clean formatting and split into an array
            jsonContent = jsonContent.replace("[", "").replace("]", "").replace("\"", "").replace("{", "").replace("}", "");
            String[] storedMessagesArray = jsonContent.split(",");
            
            return storedMessagesArray;
        } catch (java.io.IOException e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
            return new String[0];
        }
    }
    }
