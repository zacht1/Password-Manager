# My Password Manager

MyPasswordManager is an application that allows the user to store and access any account information using *one* 
universal masterPassword. The user can locally store new account information, edit previously stored account information, and 
delete old out of date account information. MyPasswordManager can help any user remember account information, and create
secure unique passwords for all their accounts. 

Anybody can benefit from using a masterPassword manager. In order to keep your accounts protected your passwords must be
long, complicated and unique. This makes remembering passwords and account information for the dozens of web accounts 
you have extremely difficult, using a masterPassword manager is the best solution.

This project is of interest to me, because I currently do use a masterPassword manager and have always been interested in how
they securely store my passwords.  I am hoping this project will give me an opportunity to learn more about how 
masterPassword managers work, and how they protect users' passwords.

## Phase 1 User Stories
- As a user, I want to be able to add an account card to my masterPassword manager 
- As a user, I want to be able to add any account information to an account card 
- As a user, I want to be able to view all the account cards in my masterPassword manager 
- As a user, I want to be able to view all the account information in any of my account cards 
- As a user, I want to be able to delete an account card from my masterPassword manager 
- As a user, I want to be able to edit any account information of an account card
- As a user, I want to be able to copy a masterPassword to the clipboard without using ctrl+c
- As a user, I want MyPasswordManager to generate strong passwords of any length for me

## Phase 2 User Stories
- As a user, I want to be able to load my masterPassword manager from file when I give the correct masterPassword
- As a user, I want my masterPassword manager to automatically save to file when I close the application

## Phase 4: Task 2
Example of logs:

Wed Mar 30 15:15:11 PDT 2022\
Amazon card added to the account card repository

Wed Mar 30 15:15:19 PDT 2022\
Netflix card added to the account card repository

Wed Mar 30 15:15:27 PDT 2022\
Apple card added to the account card repository

Wed Mar 30 15:15:40 PDT 2022\
AirCanada Aeroplan card added to the account card repository

Wed Mar 30 15:16:06 PDT 2022\
Facebook card added to the account card repository

Wed Mar 30 15:16:09 PDT 2022\
Google card deleted from the account repository

## Phase 4: Task 3
Given more time to work on this project I would improve the design of my code in the following ways:

- In the UML class diagram we see that MainWindow has an association with AccountCard and CardViewPanel, 
and CardView panel has an association with AccountCard.  In order to improve these relationships by lowering coupling
I would have MainWindow have an association with CardViewPanel and then have only CardViewPanel have an association with 
AccountCard

- There are many places in my code where I would remove duplication given more time. For example I would have all 
DialogBox classes extend a common parent class in order to remove much of the duplication within the five classes.

- In my design we can notice that there are relationships that employ a subject-observer relationship, where the observer
needs to be updated every time the subject is modified. In my design the subject would be MainWindow and the observers 
would be CardListPanel, CardViewPanel, passwordManagerApp, AccountCard, and AccountRepository. I would implement the
Observer Pattern so that everytime a card was added, deleted, or edited in the MainWindow the CardListPanel, CardViewPanel, 
passwordManagerApp, AccountCard, and AccountRepository would be notified/updated.


### Sources & Acknowledgments
- Logo images from https://www.canva.com/
- Icons from https://jetbrains.design/intellij/resources/icons_list/