package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UserTest {
    private User user;
    private List<Book> purchasedBooks;

    @BeforeEach
    public void setUp() {
        purchasedBooks = new ArrayList<>();
        Book book1 = new Book("Title 1", "Author 1", "Genre 1", 10);
        Book book2 = new Book("Title 1", "Author 1", "Genre 1", 15);
        purchasedBooks.add(book1);
        purchasedBooks.add(book2);
        user = new User("testuser", "password", "test@example.com", purchasedBooks);
    }

    @Test
    public void testGetUsername() {
        String expectedUsername = "testuser";
        String actualUsername = user.getUsername();
        Assertions.assertEquals(expectedUsername, actualUsername);
    }

    @Test
    public void testSetUsername() {
        String newUsername = "newusername";
        user.setUsername(newUsername);
        Assertions.assertEquals(newUsername, user.getUsername());
    }

    @Test
    public void testGetPassword() {
        String expectedPassword = "password";
        String actualPassword = user.getPassword();
        Assertions.assertEquals(expectedPassword, actualPassword);
    }

    @Test
    public void testSetPassword() {
        String newPassword = "newpassword";
        user.setPassword(newPassword);
        Assertions.assertEquals(newPassword, user.getPassword());
    }

    @Test
    public void testGetEmail() {
        String expectedEmail = "test@example.com";
        String actualEmail = user.getEmail();
        Assertions.assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void testSetEmail() {
        String newEmail = "newemail@example.com";
        user.setEmail(newEmail);
        Assertions.assertEquals(newEmail, user.getEmail());
    }

    @Test
    public void testGetPurchasedBooks() {
        List<Book> expectedPurchasedBooks = purchasedBooks;
        List<Book> actualPurchasedBooks = user.getPurchasedBooks();
        Assertions.assertEquals(expectedPurchasedBooks, actualPurchasedBooks);
    }

    @Test
    public void testSetPurchasedBooks() {
        List<Book> newPurchasedBooks = new ArrayList<>();
        Book book3 = new Book("Title 1", "Author 1", "Genre 1",5);
        newPurchasedBooks.add(book3);
        user.setPurchasedBooks(newPurchasedBooks);
        Assertions.assertEquals(newPurchasedBooks, user.getPurchasedBooks());
    }
}
