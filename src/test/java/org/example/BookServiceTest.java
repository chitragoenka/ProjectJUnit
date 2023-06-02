package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BookServiceTest {
    @Mock
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        System.out.println("Starting tests for method with @BeforeEach");
    }

    @AfterEach
    public void tearDown() {
        bookService = null;
        System.out.println("Compiling class after testing with @AfterEach");
    }

    @Test
    public void testSearchBook_Positive() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("1984", "George Orwell", "Dystopian", 9.99));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", "Fiction", 14.99));

        when(bookService.searchBook("1984")).thenReturn(books);

        List<Book> result = bookService.searchBook("1984");

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(bookService, times(1)).searchBook("1984");
    }

    @Test
    public void testSearchBook_Negative() {
        when(bookService.searchBook("NonexistentBook")).thenReturn(new ArrayList<>());

        List<Book> result = bookService.searchBook("NonexistentBook");

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(bookService, times(1)).searchBook("NonexistentBook");
    }

    @Test
    public void testSearchBook_EdgeCase() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("1984", "George Orwell", "Dystopian", 9.99));
        books.add(new Book("Brave New World", "Aldous Huxley", "Dystopian", 12.99));
        books.add(new Book("Fahrenheit 451", "Ray Bradbury", "Dystopian", 11.99));
        books.add(new Book("Animal Farm", "George Orwell", "Allegorical novella", 8.99));

        doReturn(books.stream()
                .filter(book -> book.getGenre().equals("Dystopian"))
                .collect(Collectors.toList()))
                .when(bookService).searchBook("Dystopian");

        List<Book> result = bookService.searchBook("Dystopian");

        assertEquals(3, result.size());
        assertTrue(result.stream().allMatch(book -> book.getGenre().equals("Dystopian")));
        verify(bookService, times(1)).searchBook("Dystopian");
    }


    @Test
    public void testPurchaseBook_Positive() {
        User user = new User("JohnDoe", "password", "johndoe@example.com");
        Book book = new Book("1984", "George Orwell", "Dystopian", 9.99);

        when(bookService.purchaseBook(user, book)).thenReturn(true);

        boolean result = bookService.purchaseBook(user, book);

        assertTrue(result);
        verify(bookService, times(1)).purchaseBook(user, book);
    }

    @Test
    public void testPurchaseBook_Negative() {
        User user = new User("JohnDoe", "password", "johndoe@example.com");
        Book book = new Book("NonexistentBook", "Unknown Author", "Unknown Genre", 9.99);

        when(bookService.purchaseBook(user, book)).thenReturn(false);

        boolean result = bookService.purchaseBook(user, book);

        assertFalse(result);
        verify(bookService, times(1)).purchaseBook(user, book);
    }

    @Test
    public void testPurchaseBook_EdgeCase() {
        User user = new User("JohnDoe", "password", "johndoe@example.com");
        Book book = new Book("NonexistentBook", "Unknown Author", "Unknown Genre", 9.99);

        // Simulate an edge condition where the book price is 0
        book.setPrice(0);

        when(bookService.purchaseBook(user, book)).thenReturn(true);

        boolean result = bookService.purchaseBook(user, book);

        assertTrue(result);
        verify(bookService, times(1)).purchaseBook(user, book);
        // Add additional assertions to check specific edge conditions if applicable
    }



    @Test
    public void testAddBookReview_Positive() {
        User user = new User("JohnDoe", "password", "johndoe@example.com");
        Book book = new Book("1984", "George Orwell", "Dystopian", 9.99);
        String review = "Amazing book!";

        when(bookService.addBookReview(user, book, review)).thenReturn(true);

        boolean result = bookService.addBookReview(user, book, review);

        assertTrue(result);
        verify(bookService, times(1)).addBookReview(user, book, review);
    }

    @Test
    public void testAddBookReview_Negative() {
        User user = new User("JohnDoe", "password", "johndoe@example.com");
        Book book = new Book("1984", "George Orwell", "Dystopian", 9.99);
        String review = "Amazing book!";

        List<Book> purchasedBooks = new ArrayList<>();
        purchasedBooks.add(new Book("AnotherBook", "Another Author", "Another Genre", 9.99));
        user.setPurchasedBooks(purchasedBooks);

        when(bookService.addBookReview(user, book, review)).thenReturn(false);

        boolean result = bookService.addBookReview(user, book, review);

        assertFalse(result);
        verify(bookService, times(1)).addBookReview(user, book, review);
    }

    @Test
    public void testAddBookReview_EdgeCase() {
        User user = new User("JohnDoe", "password", "johndoe@example.com");
        Book book = new Book("1984", "George Orwell", "Dystopian", 9.99);
        String review = "Amazing book!";

        when(bookService.addBookReview(user, book, review)).thenReturn(true);

        boolean result = bookService.addBookReview(user, book, review);

        assertTrue(result);
        verify(bookService, times(1)).addBookReview(user, book, review);
    }

    @Test
    public void testAddBook_Positive() {
        Book book = new Book("1984", "George Orwell", "Dystopian", 9.99);

        when(bookService.addBook(book)).thenReturn(true);

        boolean result = bookService.addBook(book);

        assertTrue(result);
        verify(bookService, times(1)).addBook(book);
    }

    @Test
    public void testAddBook_Negative() {
        Book book = new Book("1984", "George Orwell", "Dystopian", 9.99);

        when(bookService.addBook(book)).thenReturn(false);

        boolean result = bookService.addBook(book);

        assertFalse(result);
        verify(bookService, times(1)).addBook(book);
    }

    @Test
    public void testAddBook_EdgeCase() {
        // Test adding a book with invalid or null data
        Book invalidBook = new Book(null, "Unknown Author", "Unknown Genre", 9.99);

        when(bookService.addBook(invalidBook)).thenReturn(false);

        boolean result = bookService.addBook(invalidBook);

        assertFalse(result);
        verify(bookService, times(1)).addBook(invalidBook);
        // Add additional assertions to check specific edge conditions if applicable

        // Test adding a book that already exists in the database
        Book existingBook = new Book("1984", "George Orwell", "Dystopian", 9.99);

        when(bookService.addBook(existingBook)).thenReturn(false);

        boolean result2 = bookService.addBook(existingBook);

        assertFalse(result2);
        verify(bookService, times(1)).addBook(existingBook);
        // Add additional assertions to check specific edge conditions if applicable
    }


    @Test
    public void testRemoveBook_Positive() {
        Book book = new Book("1984", "George Orwell", "Dystopian", 9.99);

        when(bookService.removeBook(book)).thenReturn(true);

        boolean result = bookService.removeBook(book);

        assertTrue(result);
        verify(bookService, times(1)).removeBook(book);
    }

    @Test
    public void testRemoveBook_Negative() {
        Book book = new Book("NonexistentBook", "Unknown Author", "Unknown Genre", 9.99);

        when(bookService.removeBook(book)).thenReturn(false);

        boolean result = bookService.removeBook(book);

        assertFalse(result);
        verify(bookService, times(1)).removeBook(book);
    }


    @Test
    public void testRemoveBook_EdgeCase() {
        // Test removing a book that is not in the database
        Book book = new Book("NonexistentBook", "Unknown Author", "Unknown Genre", 9.99);

        when(bookService.removeBook(book)).thenReturn(false);

        boolean result = bookService.removeBook(book);

        assertFalse(result);
        verify(bookService, times(1)).removeBook(book);

        // Test removing a book that is in the database
        Book book2 = new Book("1984", "George Orwell", "Dystopian", 9.99);

        when(bookService.removeBook(book2)).thenReturn(true);

        boolean result2 = bookService.removeBook(book2);

        assertTrue(result2);
        verify(bookService, times(1)).removeBook(book2);
    }

}
