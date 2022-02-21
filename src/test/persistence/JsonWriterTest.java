package persistence;

import model.AccountCard;
import model.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{
    AccountRepository testAR;
    JsonWriter testJsonWriter;
    JsonReader testJsonReader;

    @BeforeEach
    private void setup() {
        testAR = new AccountRepository("password");
    }

    @Test
    public void testInvalidDestinationFile() {
        testJsonWriter = new JsonWriter("./data/illegal\0\filename.json");
        assertThrows(FileNotFoundException.class, testJsonWriter::open, "FileNotFoundException was expected");
    }

    @Test
    public void testEmptyAccountRepository() {
        testJsonWriter = new JsonWriter("./data/testEmptyWriter");

        try {
            testJsonWriter.open();
            testJsonWriter.write(testAR);
            testJsonWriter.close();

            testJsonReader = new JsonReader("./data/testEmptyWriter");
            AccountRepository accounts = testJsonReader.readAccounts();

            assertEquals("password", accounts.getPassword());
            assertEquals(0, accounts.numAccounts());

        } catch (FileNotFoundException e) {
            fail("Could not open destination file");
        } catch (IOException e) {
            fail("Could not read from destination file");
        }
    }

    @Test
    public void testNonEmptyAccountRepository() {
        testJsonWriter = new JsonWriter("./data/testNonEmptyWriter");
        testAR.addCard(createCard("Netflix", "Guest123", "1qaz2wsx", "jimbo@gmail.com",
                "netflix.com"));
        testAR.addCard(createCard("UBC Student Services Center", "cwlName", "password",
                "student@student.ubc.ca", "cas.id.ubc.ca"));

        try {
            testJsonWriter.open();
            testJsonWriter.write(testAR);
            testJsonWriter.close();

            JsonReader reader = new JsonReader("./data/testNonEmptyWriter");
            AccountRepository accounts = reader.readAccounts();

            assertEquals("password", accounts.getPassword());
            assertEquals(2, accounts.numAccounts());
            checkCard("Netflix", "Guest123", "1qaz2wsx", "jimbo@gmail.com",
                    "netflix.com", accounts.getAccounts().get(0));
            checkCard("UBC Student Services Center", "cwlName", "password",
                    "student@student.ubc.ca", "cas.id.ubc.ca", accounts.getAccounts().get(1));

        } catch (FileNotFoundException e) {
            fail("Could not open destination file");
        } catch (IOException e) {
            fail("Could not read from destination file");
        }
    }

    private AccountCard createCard(String title, String login, String password, String email, String url) {
        AccountCard card = new AccountCard(title);
        card.setLogin(login);
        card.setPassword(password);
        card.setEmail(email);
        card.setUrl(url);
        return card;
    }
}