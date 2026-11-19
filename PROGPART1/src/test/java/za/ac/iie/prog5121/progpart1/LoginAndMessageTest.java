/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.iie.prog5121.progpart1;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginAndMessageTest {
    @Test
    // Check if the cell number starts with +27 and has 9 digits after it (12 charaters in total)
    public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        return cellPhoneNumber !=null && cellPhoneNumber.matches("^\\+27\\d{9}$");
    }

    @Test
    //  Check if the password is at least 8 characters, has a capital letter,
    public boolean checkPasswordComplexity(String password){
        if (password == null || password.length() <8){
            return false;
        }
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
       
        return hasUppercase && hasDigit && hasSpecialChar;
    }



    @Test
    public void testCheckMessageLength_Valid() {
        Message msg = new Message("+27834557896", "Hello this is a short test message.", 1);
        assertTrue(msg.checkMessageLength());
    }

    @Test
    public void testCheckMessageLength_Invalid() {
        // Create a string longer than 250 characters
        String longText = "A".repeat(251);
        Message msg = new Message("+27834557896", longText, 1);
       assertFalse(msg.checkMessageLength());
    }
}
