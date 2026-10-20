/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.iie.prog5121.progpart1;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageManagerTest {

    @Test
    public void testLongestStoredMessage() {
        // Setup mock data in MessageManager or test methods
        Message msg1 = new Message("+27834557896", "Did you get the cake?", 1);
        Message msg2 = new Message("+27838884557", "Where are you? You are late! I have asked you to be on time.", 2);
        
        MessageManager.handleMessageAction(msg2, 3); // Store message 2
        
        String longest = MessageManager.getLongestStoredMessage();
        assertTrue(longest.contains("Where are you?"));
    }

    @Test
    public void testDeleteByHash() {
        Message msg = new Message("+27834484567", "Yohoooo, I am at your gate.", 3);
        MessageManager.handleMessageAction(msg, 3); // Store
        
        String hash = msg.getMessageHash();
        boolean deleted = MessageManager.deleteByHash(hash);
        assertTrue(deleted);
    }
}

