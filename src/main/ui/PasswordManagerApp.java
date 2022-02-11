package ui;

import model.AccountCard;
import model.AccountRepository;

import java.util.Objects;
import java.util.Scanner;

// Represents the console app for MyPasswordManager
// Credit: Much of the scanner/user interaction/console code in this class is influenced by the TellerApp sample project
public class PasswordManagerApp {
    private static final String BAD_INPUT_MESSAGE = "... unknown input";

    private AccountRepository passwordManager;
    private String password;                   // implement in further phases of project
    private Scanner input;

    // EFFECTS: runs MyPasswordManager app
    public PasswordManagerApp() {
        runPasswordManager();
    }

    // MODIFIES: this
    // EFFECTS: initializes account repository and processes user input
    private void runPasswordManager() {
        boolean shouldEnd = false;
        String instruction;

        passwordManager = new AccountRepository();
        password = "12345";
        input = new Scanner(System.in);

        while (!shouldEnd) {
            displayMainMenu();
            instruction = input.next();
            instruction = instruction.toLowerCase();

            if (instruction.equals("quit")) {
                shouldEnd = true;
            } else {
                processInstruction(instruction);
            }
        }
    }

    // EFFECTS: processes user input
    private void processInstruction(String instruction) {
        if (Objects.equals(instruction, "new")) {
            createNewCard();
        } else if (Objects.equals(instruction, "view")) {
            viewAllCards();
        } else if (Objects.equals(instruction, "search")) {
            searchForCard();
        } else {
            System.out.println(BAD_INPUT_MESSAGE);
        }
    }

    // EFFECTS: displays main menu on console
    private void displayMainMenu() {
        System.out.println("\nPlease select from the following menu:");
        System.out.println("\tTo add a new card -> new");
        System.out.println("\tTo view all account cards -> view");
        System.out.println("\tTo search for a specific card -> search");
        System.out.println("\tTo quit MyPasswordManager -> quit");
    }

    // MODIFIES: this
    // EFFECTS: creates a new AccountCard within the account repository, and processes further user input
    private void createNewCard() {
        System.out.println("\nPlease give your new card a title:");
        input.nextLine();
        String title;
        title = input.nextLine();
        AccountCard newCard = new AccountCard(title);
        passwordManager.addCard(newCard);

        boolean shouldClose = false;

        while (!shouldClose) {
            displayCreateMenu();
            String instruction = input.next();

            if (Objects.equals(instruction, "q")) {
                shouldClose = true;
            } else {
                handleCreatorInstruction(instruction, newCard);
            }
        }
    }

    // EFFECTS: displays card creation menu on the console
    private void displayCreateMenu() {
        System.out.println("\nWhat fields would you like to add to your new card:");
        System.out.println("\tLogin/Username -> l");
        System.out.println("\tPassword -> p");
        System.out.println("\tEmail Address -> e");
        System.out.println("\tWebsite URL -> w");
        System.out.println("\tFinished filling in fields -> q");
    }

    // MODIFIES: card
    // EFFECTS: handles user input
    private void handleCreatorInstruction(String instruction, AccountCard card) {
        if (Objects.equals(instruction, "l")) {
            System.out.println("\nPlease enter your login/username:");
            card.setLogin(input.next());
        } else if (Objects.equals(instruction, "p")) {
            handlePasswordGeneration(card);
        } else if (Objects.equals(instruction, "e")) {
            System.out.println("\nPlease enter your email address:");
            card.setEmail(input.next());
        } else if (Objects.equals(instruction, "w")) {
            System.out.println("\nPlease enter the websites URL:");
            card.setUrl(input.next());
        } else {
            System.out.println("\n" + BAD_INPUT_MESSAGE);
        }
    }

    // MODIFIES: card
    // EFFECTS: handles user input, and based on input changes password to randomly generated password, or changes
    //          password to user input password
    private void handlePasswordGeneration(AccountCard card) {
        System.out.println("\nGenerator password for you?");
        System.out.println("\tYes -> y");
        System.out.println("\tNo -> n");
        String instruction = input.next();

        if (Objects.equals(instruction, "y")) {
            System.out.println("Please enter desired password length:");
            String newPassword = card.generatePassword(input.nextInt());
            System.out.println("Password set to " + newPassword);
        } else {
            System.out.println("Please enter your password:");
            card.setPassword(input.next());
        }
    }

    // EFFECTS: prints out all account card titles within the account repository
    private void viewAllCards() {
        System.out.println(passwordManager.getAccountTitles());
    }

    // MODIFIES : this
    // EFFECTS: handles user input, and deletes card if user inputs 'd'
    private void searchForCard() {
        System.out.println("\nEnter name of card you are looking for:");

        input.nextLine();
        String cardTitle;
        cardTitle = input.nextLine();
        AccountCard card = passwordManager.getSpecificCard(cardTitle);

        boolean shouldClose = isCardFound(card);

        while (!shouldClose) {
            displayCardMenu();
            String instruction = input.next();

            if (Objects.equals(instruction, "q")) {
                shouldClose = true;
            } else if (Objects.equals(instruction, "d")) {
                deleteCard(card);
                System.out.println(card.getTitle() + " card deleted");
                shouldClose = true;
            } else {
                handleCardInstructions(instruction, card);
            }
        }
    }

    // EFFECTS: returns true and prints out message if card is null, otherwise returns false
    private boolean isCardFound(AccountCard card) {
        boolean foundCard;
        foundCard = card != null;

        if (!foundCard) {
            System.out.println("No card with that title in MyPasswordManager");
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: displays account card menu on the console
    private void displayCardMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tCopy username -> u");
        System.out.println("\tCopy password -> p");
        System.out.println("\tEdit account information -> e");
        System.out.println("\tView all account information -> v");
        System.out.println("\tDelete this card -> d");
        System.out.println("\tBack to main menu -> q");
    }

    // MODIFIES: this
    // EFFECTS: handles user input
    private void handleCardInstructions(String instruction, AccountCard card) {
        if (Objects.equals(instruction, "u")) {
            handleLoginCopy(card);
        } else if (Objects.equals(instruction, "p")) {
            handlePasswordCopy(card);
        } else if (Objects.equals(instruction, "e")) {
            editCard(card);
        } else if (Objects.equals(instruction, "v")) {
            printCardInfo(card);
        } else if (Objects.equals(instruction, "d"))  {
            deleteCard(card);
        } else {
            System.out.println(BAD_INPUT_MESSAGE);
        }
    }

    // EFFECTS: if card has login then copies login to clipboard
    private void handleLoginCopy(AccountCard card) {
        if (card.copyLogin()) {
            card.copyLogin();
            System.out.println("Login was copied to clipboard");
        } else {
            System.out.println("This account card has no login information");
        }
    }

    // EFFECTS: if card has password then copies password to clipboard
    private void handlePasswordCopy(AccountCard card) {
        if (card.copyPassword()) {
            card.copyPassword();
            System.out.println("Password was copied to clipboard");
        } else {
            System.out.println("This account card has no password");
        }
    }

    // EFFECTS: displays an account cards info in the console on a new line
    private void printCardInfo(AccountCard card) {
        System.out.println("\nTitle: " + card.getTitle());
        System.out.println("\tLogin: " + card.getLogin());
        System.out.println("\tPassword: " + card.getPassword());
        System.out.println("\tEmail Address: " + card.getEmail());
        System.out.println("\tWebsite URL: " + card.getUrl());
    }

    // EFFECTS: displays an account cards info in the console
    private void printCardInfoNoNewLine(AccountCard card) {
        System.out.println("Title: " + card.getTitle());
        System.out.println("\tLogin: " + card.getLogin());
        System.out.println("\tPassword: " + card.getPassword());
        System.out.println("\tEmail Address: " + card.getEmail());
        System.out.println("\tWebsite URL: " + card.getUrl());
    }

    // MODIFIES: this
    // EFFECTS: deletes given card from account repository
    private void deleteCard(AccountCard card) {
        passwordManager.getAccounts().remove(card);
    }

    // MODIFIES: card
    // EFFECTS: handles user input
    private void editCard(AccountCard card) {
        System.out.println("Here is the cards current information:");
        printCardInfoNoNewLine(card);

        boolean shouldClose = false;

        while (!shouldClose) {
            displayEditMenu();
            String instruction = input.next();

            if (Objects.equals(instruction, "q")) {
                shouldClose = true;
            } else {
                handleEditInstructions(instruction, card);
            }

        }
    }

    // EFFECTS: displays account card edit menu on the console
    private void displayEditMenu() {
        System.out.println("\nWhat information would you like to change?");
        System.out.println("\tTitle -> t");
        System.out.println("\tLogin -> l");
        System.out.println("\tPassword -> p");
        System.out.println("\tEmail Address -> e");
        System.out.println("\tWebsite URL -> w");
        System.out.println("\tFinished editing this card -> q");
    }

    // MODIFIES: card
    // EFFECTS: handles user input
    private void handleEditInstructions(String instruction, AccountCard card) {
        if (Objects.equals(instruction, "t")) {
            changeTitle(card);
        } else if (Objects.equals(instruction, "l")) {
            changeLogin(card);
        } else if (Objects.equals(instruction, "p")) {
            changePassword(card);
        } else if (Objects.equals(instruction, "e")) {
            changeEmail(card);
        } else if (Objects.equals(instruction, "w")) {
            changeURL(card);
        } else {
            System.out.println(BAD_INPUT_MESSAGE);
        }
    }

    // MODIFIES: card
    // EFFECTS: changes the given account cards title to user input title, or deletes current title if input 'd'
    private void changeTitle(AccountCard card) {
        System.out.println("\nCurrent title: " + card.getTitle());
        System.out.println("Enter new title:");

        input.nextLine();
        String newTitle;
        newTitle = input.nextLine();

        card.setTitle(newTitle);
    }

    // MODIFIES: card
    // EFFECTS: changes the given account cards login to user input login, or deletes current login if input 'd'
    private void changeLogin(AccountCard card) {
        System.out.println("\nCurrent login: " + card.getLogin());
        System.out.println("Enter new login or enter 'd' to delete current login:");
        String newLogin = input.next();

        if (Objects.equals(newLogin, "d")) {
            card.setLogin(null);
            System.out.println("Login/username deleted");
        } else {
            card.setLogin(newLogin);
        }
    }

    // MODIFIES: card
    // EFFECTS: changes the given account cards password to user input password or random generated password,
    //          or deletes current password if input 'd'
    private void changePassword(AccountCard card) {
        System.out.println("\nCurrent password: " + card.getPassword());
        System.out.println("Please select from the following:");
        System.out.println("\tEnter new password -> n");
        System.out.println("\tDelete password -> d");
        String newPassword = input.next();

        if (Objects.equals(newPassword, "d")) {
            card.setPassword(null);
            System.out.println("Password deleted");
        } else if (Objects.equals(newPassword, "n")) {
            handlePasswordGeneration(card);
        } else {
            System.out.println(BAD_INPUT_MESSAGE);
        }
    }

    // MODIFIES: card
    // EFFECTS: changes the given account cards email to user input email, or deletes current email if input 'd'
    private void changeEmail(AccountCard card) {
        System.out.println("\nCurrent Email address: " + card.getEmail());
        System.out.println("Enter new Email address or enter 'd' to delete current Email address:");
        String newEmail = input.next();

        if (Objects.equals(newEmail, "d")) {
            card.setEmail(null);
            System.out.println("Email deleted");
        } else {
            card.setEmail(newEmail);
        }
    }

    // MODIFIES: card
    // EFFECTS: changes the given account cards url to user input url, or deletes current url if input 'd'
    private void changeURL(AccountCard card) {
        System.out.println("\nCurrent website URL: " + card.getUrl());
        System.out.println("Enter new website URL or enter 'd' to delete current website url:");
        String newURL = input.next();

        if (Objects.equals(newURL, "d")) {
            card.setUrl(null);
            System.out.println("Website url deleted");
        } else {
            card.setUrl(newURL);
        }
    }
}
