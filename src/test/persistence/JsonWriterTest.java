package persistence;

import model.AccountCard;
import model.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

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
        testJsonWriter = new JsonWriter("./data/illegal\0\filename.json",
                "./data/illegal\0\filename12.json");
        assertThrows(FileNotFoundException.class, testJsonWriter::open, "FileNotFoundException was expected");
    }

    @Test
    public void testEmptyAccountRepository() {
        testJsonWriter = new JsonWriter("./data/testEmptyWriter.json",
                "./data/testPasswordWriter.json");

        try {
            testJsonWriter.open();
            testJsonWriter.write(testAR);
            testJsonWriter.close();

            testJsonReader = new JsonReader("./data/testEmptyWriter.json",
                    "./data/testPasswordWriter.json");
            AccountRepository accounts = testJsonReader.readAccounts();
            accounts.setMasterPassword(testJsonReader.readMasterPassword());

            assertEquals("password", accounts.getMasterPassword());
            assertEquals(0, accounts.numAccounts());

        } catch (FileNotFoundException e) {
            fail("Could not open destination file");
        } catch (IOException e) {
            fail("Could not read from destination file");
        }
    }

    @Test
    public void testNonEmptyAccountRepository() {
        testJsonWriter = new JsonWriter("./data/testNonEmptyWriter.json",
                "./data/testPasswordWriter.json");
        testAR.addCard(createCard("Netflix", "Guest123", "1qaz2wsx", "jimbo@gmail.com",
                "netflix.com"));
        testAR.addCard(createCard("UBC Student Services Center", "cwlName", "password",
                "student@student.ubc.ca", "cas.id.ubc.ca"));

        try {
            testJsonWriter.open();
            testJsonWriter.write(testAR);
            testJsonWriter.close();

            JsonReader reader = new JsonReader("./data/testNonEmptyWriter.json",
                    "./data/testPasswordWriter.json");
            AccountRepository accounts = reader.readAccounts();
            accounts.setMasterPassword(reader.readMasterPassword());

            assertEquals("password", accounts.getMasterPassword());
            assertEquals(2, accounts.numAccounts());
            checkCard("Netflix", "Guest123", "1qaz2wsx", "jimbo@gmail.com",
                    "netflix.com", new Date(), accounts.getAccounts().get(0));
            checkCard("UBC Student Services Center", "cwlName", "password",
                    "student@student.ubc.ca", "cas.id.ubc.ca", new Date(), accounts.getAccounts().get(1));

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
