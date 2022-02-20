package persistence;

import model.AccountCard;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Credit: This class is based on the code from the JsonTest class in JsonSerializationDemo
public class JsonTest {

    protected void checkCard(String title, String login, String password, String email, String url, AccountCard card) {
        assertEquals(title, card.getTitle());
        assertEquals(login, card.getLogin());
        assertEquals(password, card.getPassword());
        assertEquals(email, card.getEmail());
        assertEquals(url, card.getUrl());
    }

}
