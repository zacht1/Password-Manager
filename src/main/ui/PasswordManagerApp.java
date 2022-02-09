package ui;

import model.AccountCard;
import model.AccountRepository;

import java.util.Objects;
import java.util.Scanner;

public class PasswordManagerApp {
    private static final String BAD_INPUT_MESSAGE = "... unknown input";

    private AccountRepository passwordManager;
    private String password;
    private Scanner input;

    public PasswordManagerApp() {
        runPasswordManager();
    }

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

    private void displayMainMenu() {
        System.out.println("\nPlease select from the following menu:");
        System.out.println("\tTo add a new card -> new");
        System.out.println("\tTo view all account cards -> view");
        System.out.println("\tTo search for a specific card -> search");
        System.out.println("\tTo quit MyPasswordManager -> quit");
    }

    //this might be source of error
    private void createNewCard() {
        System.out.println("\nPlease give your new card a title:");
        AccountCard newCard = new AccountCard(input.next());
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

    private void displayCreateMenu() {
        System.out.println("\nWhat fields would you like to add to your new card:");
        System.out.println("\tLogin/Username -> l");
        System.out.println("\tPassword -> p");
        System.out.println("\tEmail Address -> e");
        System.out.println("\tWebsite URL -> u");
        System.out.println("\tFinished filling in fields -> q");
    }

    private void handleCreatorInstruction(String instruction, AccountCard ac) {
        if (Objects.equals(instruction, "l")) {
            System.out.println("\nPlease enter your login/username:");
            ac.setLogin(input.next());
        } else if (Objects.equals(instruction, "p")) {
            System.out.println("\nPlease enter your password:");
            ac.setPassword(input.next());
        } else if (Objects.equals(instruction, "e")) {
            System.out.println("\nPlease enter your email address:");
            ac.setEmail(input.next());
        } else if (Objects.equals(instruction, "u")) {
            System.out.println("\nPlease enter the websites URL:");
            ac.setUrl(input.next());
        } else {
            System.out.println("\n" + BAD_INPUT_MESSAGE);
        }
    }

    private void viewAllCards() {
        System.out.println(passwordManager.getAccountTitles());
    }

    private void searchForCard() {
        System.out.println("\nEnter name of card you are looking for:");
        AccountCard card = passwordManager.getSpecificCard(input.next());

        boolean foundCard;
        foundCard = card != null;
        boolean shouldClose = false;

        if (!foundCard) {
            System.out.println("No card with that title in MyPasswordManager");
            shouldClose = true;
        }

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

    private void displayCardMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tCopy username -> u");
        System.out.println("\tCopy password -> p");
        System.out.println("\tEdit account information -> e");
        System.out.println("\tView all account information -> v");
        System.out.println("\tDelete this card -> d");
        System.out.println("\tBack to main menu -> q");
    }

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

    private void handleLoginCopy(AccountCard card) {
        if (card.copyLogin()) {
            card.copyLogin();
            System.out.println("Login was copied to clipboard");
        } else {
            System.out.println("This account card has no login information");
        }
    }

    private void handlePasswordCopy(AccountCard card) {
        if (card.copyPassword()) {
            card.copyPassword();
            System.out.println("Password was copied to clipboard");
        } else {
            System.out.println("This account card has no password");
        }
    }

    private void printCardInfo(AccountCard card) {
        System.out.println("\nTitle: " + card.getTitle());
        System.out.println("\tLogin: " + card.getLogin());
        System.out.println("\tPassword: " + card.getPassword());
        System.out.println("\tEmail Address: " + card.getEmail());
        System.out.println("\tWebsite URL: " + card.getUrl());
    }

    private void deleteCard(AccountCard card) {
        passwordManager.getAccounts().remove(card);
    }

    private void editCard(AccountCard card) {
        System.out.println("What information would you like to change?");

        boolean shouldClose = false;
        boolean toMainMenu = false;

        while (!shouldClose) {
            displayEditMenu();
            String instruction = input.next();

            if (Objects.equals(instruction, "q")) {
                shouldClose = true;
            } else if (Objects.equals(instruction, "m")) {
                shouldClose = true;
                toMainMenu = true;
            } else {
                handleEditInstructions(instruction, card);
            }

        }
    }

    private void displayEditMenu() {
        System.out.println("\tTitle -> t");
        System.out.println("\tLogin -> l");
        System.out.println("\tPassword -> p");
        System.out.println("\tEmail Address -> e");
        System.out.println("\tWebsite URL -> u");
        System.out.println("\tFinished editing this card -> q");
        System.out.println("\tReturn to main menu -> m");
    }

    private void handleEditInstructions(String instruction, AccountCard card) {
        if (Objects.equals(instruction, "t")) {
            changeTitle(card);
        } else if (Objects.equals(instruction, "l")) {
            changeLogin(card);
        } else if (Objects.equals(instruction, "p")) {
            changePassword(card);
        } else if (Objects.equals(instruction, "e")) {
            changeEmail(card);
        } else if (Objects.equals(instruction, "u")) {
            changeURL(card);
        } else {
            System.out.println(BAD_INPUT_MESSAGE);
        }
    }

    private void changeTitle(AccountCard card) {
        System.out.println("\nCurrent title: " + card.getTitle());
        System.out.println("Enter new title:");
        String newTitle = input.next();

        if (Objects.equals(newTitle, "d")) {
            card.setTitle(null);
        } else if (Objects.equals(newTitle, "")) {
            ;
        } else {
            card.setTitle(newTitle);
        }
    }

    private void changeLogin(AccountCard card) {
        System.out.println("\nCurrent login: " + card.getLogin());
        System.out.println("Enter new login or enter 'd' to delete current login:");
        String newLogin = input.next();

        if (Objects.equals(newLogin, "d")) {
            card.setLogin(null);
        } else if (Objects.equals(newLogin, "")) {
            ;
        } else {
            card.setLogin(newLogin);
        }
    }

    private void changePassword(AccountCard card) {
        System.out.println("\nCurrent password: " + card.getPassword());
        System.out.println("Enter new password or enter 'd' to delete current password:");
        String newPassword = input.next();

        if (Objects.equals(newPassword, "d")) {
            card.setPassword(null);
        } else if (Objects.equals(newPassword, "")) {
            ;
        } else {
            card.setPassword(newPassword);
        }
    }

    private void changeEmail(AccountCard card) {
        System.out.println("\nCurrent Email address: " + card.getEmail());
        System.out.println("Enter new Email address or enter 'd' to delete current Email address:");
        String newEmail = input.next();

        if (Objects.equals(newEmail, "d")) {
            card.setEmail(null);
        } else if (Objects.equals(newEmail, "")) {
            ;
        } else {
            card.setEmail(newEmail);
        }
    }

    private void changeURL(AccountCard card) {
        System.out.println("\nCurrent website URL: " + card.getUrl());
        System.out.println("Enter new website URL:");
        String newURL = input.next();

        if (Objects.equals(newURL, "d")) {
            card.setUrl(null);
        } else if (Objects.equals(newURL, "")) {
            ;
        } else {
            card.setUrl(newURL);
        }
    }
}
