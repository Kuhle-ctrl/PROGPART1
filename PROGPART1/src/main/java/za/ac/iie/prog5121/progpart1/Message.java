/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.iie.prog5121.progpart1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Message {
    private String messageID;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    private int messageNumber;

    // Constructor
    public Message(String recipientCell, String messageText, int messageNumber) {
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageNumber = messageNumber;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Auto-generate a 10-digit Message ID
    private String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }

    // 1. checkMessageID(): Ensures ID is not more than 10 characters
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // 2. checkRecipientCell(): Ensures recipient cell number is formatted correctly with an international code
    public boolean checkRecipientCell() {
        return recipientCell != null && recipientCell.startsWith("+") && recipientCell.length() >= 10 && recipientCell.length() <= 12;
    }

    // 3. checkMessageLength(): Ensures message does not exceed 250 characters
    public boolean checkMessageLength() {
        return messageText != null && messageText.length() <= 250;
    }

    // 4. createMessageHash(): Format -> [First 2 digits of ID]:[Message Number]:[FIRST and LAST words in CAPS]
    public String createMessageHash() {
        if (messageID == null || messageID.length() < 2 || messageText == null || messageText.trim().isEmpty()) {
            return "";
        }
        String firstTwoDigits = messageID.substring(0, 2);
        
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "").toUpperCase();
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "").toUpperCase();

        return firstTwoDigits + ":" + messageNumber + ":" + firstWord + lastWord;
    }

    // 5. sentMessage(): Handles user choice (1: Send, 2: Disregard/Delete, 3: Store)
    public String sentMessage(int choice) {
        switch (choice) {
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.\nMessage successfully deleted.";
            case 3:
                storeMessageJSON();
                return "Message successfully stored.";
            default:
                return "Invalid option.";
        }
    }

    // 6. Store message into a JSON file format
    public void storeMessageJSON() {
        String jsonContent = "{\n" +
                " \"MessageID\": \"" + messageID + "\",\n" +
                " \"MessageHash\": \"" + messageHash + "\",\n" +
                " \"Recipient\": \"" + recipientCell + "\",\n" +
                " \"Message\": \"" + messageText + "\"\n" +
                "}";
        try (FileWriter file = new FileWriter("stored_messages.json", true)) {
            file.write(jsonContent + ",\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters
    public String getMessageID() { return messageID; }
    public String getRecipientCell() { return recipientCell; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }
}