package persistence;


import model.AccountRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;

// Credit: This class is based on the code from the JsonReaderTest class in JsonSerializationDemo
public class JsonReaderTest extends JsonTest{
    JsonReader jsonReader;

    @Test
    public void testJsonReaderUnknownFile() {
        jsonReader = new JsonReader("./data/testNonExistentFile.json");

        assertThrows(IOException.class, () -> { AccountRepository accounts = jsonReader.readAccounts();
        }, "IOException was expected");
    }

    @Test
    public void testJsonReaderEmptyAccountRepository() {
        jsonReader = new JsonReader("./data/testEmptyAccountRepository.json");

        try {
            AccountRepository accounts = jsonReader.readAccounts();
            assertEquals("1234abcd", accounts.getPassword());
            assertEquals(0, accounts.numAccounts());
        } catch (IOException e) {
            fail("Could not read from file");
        }
    }

    @Test
    public void testJsonReaderNonEmptyAccountRepository() {
        jsonReader = new JsonReader("./data/testNonEmptyAccountRepository.json");

        try {
            AccountRepository accounts = jsonReader.readAccounts();
            assertEquals("password", accounts.getPassword());
            assertEquals(2, accounts.numAccounts());
            checkCard("Google", null, "12345abcd", null, null,
                    accounts.getAccounts().get(0));
            checkCard("Amazon", "Johnd", "password", "josn_d334@gmail.com", null,
                    accounts.getAccounts().get(1));
        } catch (IOException e) {
            fail("Could not read from file");
        }
    }

    @Test
    public void testJsonReaderFullAccountRepository() {
        jsonReader = new JsonReader("./data/testFullAccountRepository.json");

        try {
            AccountRepository accounts = jsonReader.readAccounts();
            assertEquals("1234abcd", accounts.getPassword());
            assertEquals(2, accounts.numAccounts());
            checkCard("Google", "Guest", "password", "guest@gmail.com", "google.com",
                    accounts.getAccounts().get(0));
            checkCard("Amazon", "John123", "12345", "john_doe@gmail.com", "amazon.com",
                    accounts.getAccounts().get(1));
        } catch (IOException e) {
            fail("Could not read from file");
        }
    }
}
