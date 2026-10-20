/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.iie.prog5121.progpart1;


import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 1);
        assertTrue(msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        // Creates a string longer than 250 characters
        String longText = new String(new char[251]).replace("\0", "A");
        Message msg = new Message("+27718693002", longText, 1);
        assertFalse(msg.checkMessageLength());
    }

    @Test
    public void testRecipientCellFormatting() {
        Message validMsg = new Message("+27718693002", "Test message", 1);
        assertTrue(validMsg.checkRecipientCell());

        Message invalidMsg = new Message("08966553", "Test message", 1);
        assertFalse(invalidMsg.checkRecipientCell());
    }

    @Test
    public void testMessageHashGeneration() {
        Message msg = new Message("1234567890", "Hi Mike, can you join us for dinner tonight?", 0);
        String hash = msg.createMessageHash();
        assertTrue(hash.contains(":0:"));
        assertTrue(hash.endsWith("HITONIGHT"));
    }

    @Test
    public void testSentMessageOptions() {
        Message msg = new Message("+27718693002", "Test", 1);
        assertEquals("Message successfully sent.", msg.sentMessage(1));
        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }
}