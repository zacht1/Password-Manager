package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AccountCardTest {
    private AccountCard testCard;

    @BeforeEach
    public void setup() {
        testCard = new AccountCard("Google");
    }

    @Test
    public void testConstructor() {
        assertEquals("Google", testCard.getTitle());
        assertNull(testCard.getEmail());
        assertNull(testCard.getLogin());
        assertNull(testCard.getPassword());
        assertNull(testCard.getUrl());
    }

    @Test
    public void testCopyPassword() throws IOException, UnsupportedFlavorException {
        assertFalse(testCard.copyPassword());
        testCard.setPassword("12345");
        assertTrue(testCard.copyPassword());
        assertEquals("12345",
                Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor));

        testCard.setPassword("password");
        assertTrue(testCard.copyPassword());
        assertEquals("password",
                Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor));
    }

    @Test
    public void testCopyLogin() throws IOException, UnsupportedFlavorException {
        assertFalse(testCard.copyLogin());
        testCard.setLogin("guest");
        assertTrue(testCard.copyLogin());
        assertEquals("guest",
                Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor));

        testCard.setLogin("admin123");
        assertTrue(testCard.copyLogin());
        assertEquals("admin123",
                Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor));
    }

    @Test
    public void testGeneratePassword() {
        assertEquals(12, testCard.generatePassword(12).length());
        assertEquals(12, testCard.getPassword().length());

        assertEquals(20, testCard.generatePassword(20).length());
        assertEquals(20, testCard.getPassword().length());
    }

    @Test
    public void testGettersSetters() {
        testCard.setTitle("Google");
        testCard.setLogin("Guest");
        testCard.setPassword("password1");
        testCard.setUrl("www.google.com");
        testCard.setEmail("guest@gmail.com");

        assertEquals("Google", testCard.getTitle());
        assertEquals("guest@gmail.com", testCard.getEmail());
        assertEquals("password1", testCard.getPassword());
        assertEquals("www.google.com", testCard.getUrl());
        assertEquals("Guest", testCard.getLogin());
    }
}