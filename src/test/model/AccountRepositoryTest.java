package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountRepositoryTest {
    private AccountRepository testAccounts;
    private AccountCard testCard1;
    private AccountCard testCard2;

    @BeforeEach
    public void setup() {
        testAccounts = new AccountRepository();
        testCard1 = new AccountCard("Google");
        testCard2 = new AccountCard("Facebook");
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testAccounts.getAccounts().size());
    }

    @Test
    public void testGetAccountTitles() {
        AccountRepository testAccounts2 = testAccounts;
        testAccounts.addCard(testCard1);
        assertEquals(1, testAccounts.getAccountTitles().size());
        assertEquals(testAccounts2.getAccounts(), testAccounts.getAccounts());
    }

    @Test
    public void testGetSpecificCard() {
        AccountRepository testAccounts2 = testAccounts;
        testAccounts.addCard(testCard1);
        testCard2.setEmail("guest@gmail.com");
        testAccounts.addCard(testCard2);

        assertEquals("Google", testAccounts.getSpecificCard("Google").getTitle());
        assertEquals("guest@gmail.com", testAccounts.getSpecificCard("Facebook").getEmail());
        assertEquals(testAccounts2.getAccounts(), testAccounts.getAccounts());
        assertNull(testAccounts.getSpecificCard("Instagram"));
        assertNull(testAccounts.getSpecificCard("Facebo0k"));
    }

    @Test
    public void testDeleteSpecificCard() {
        AccountRepository testAccounts2 = testAccounts;
        testAccounts.addCard(testCard1);
        testAccounts.addCard(testCard2);

        assertTrue(testAccounts.deleteSpecificCard("Google"));
        assertEquals(1, testAccounts.getAccounts().size());

        assertFalse(testAccounts.deleteSpecificCard("Gmail"));
        assertEquals(1, testAccounts.getAccounts().size());

        assertTrue(testAccounts.deleteSpecificCard("Facebook"));
        assertEquals(0, testAccounts.getAccounts().size());
    }
}
