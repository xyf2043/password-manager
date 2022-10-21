
# Password Manager

This is my UBC CPSC210 project that comes from my school github account in CPSC210 github Enterprise. NO 
COPY or CLONE is allowed since it violates UBC academic misconduct policy.

## Overview about the project

This application, *password manager*, aims to help record password (with its username) on 
website accounts, credit cards, etc. 

**Password Manager** could record both password and username at the same time as a user.

This application could assist people **who need**:
- to remember many passwords or usernames for accounts on websites, cards, and so on.
- to ensure privacy, since the password-record function on website browser is not so safe.
- convenience, for example, just search for either username or password in the project list, both of them would
appear. There is also a 'delete' and 'edit' function to delete or change passwords and usernames.


## Why I interested in this project?

I create this project because I once faced the trouble with passwords on the website
accounts. Sometimes I would forget username, or sometimes I would forget passwords.
However, using same password for each account would lead to a loss to security of accounts. 
With the password manager, I believe the application would solve these potential problems.

## User Stories
- As a user, I want to insert multiple passwords and usernames in my password list.
- As a user, I want to search my username or password in my list if I forget them.
- As a user, I want to select one password or username and edit them.
- As a user, I want to select one password or username and delete them.
- As a user, I want to get the whole list of my passwords and user names.
- As a user, I want to clear my whole password and username list.
- As a user, I want to save my password and username list.
- As a user, I want to load my password and username list.
- As a user, I want to save my password list before quit app.
- As a user, I want to load my password list when app starts.

## Phase 4: Task 2
- Choose Option 1: design a class that is robust.
- In userList class, there is insertUser method need to make sure no duplicated users in userList, 
there need to be a require clause originally.
- Because we need to avoid duplicated users in list, so the insertUser method 
could throw exception to prevent same user added to the list.

## Phase 4: Task 3
- There could be the refactoring on the PasswordManagerApp since some panel and frame have
same size and same position, which means I only need to change their text and title.
- Therefore, I could write a method that create a format of panel and frame. 
If I need different panels, I just need to use setText methods for panels.
- Also, in PasswordEditor, to perform refactoring, 
I can generate 'override equals' to identify whether 
the current user is same as the given user's password and username



