package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BookTest {

    @Test
    public void testGettersAndSetters() {
        // Create a sample book
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 12.99);

        // Test the getters
        Assertions.assertEquals("The Great Gatsby", book.getTitle());
        Assertions.assertEquals("F. Scott Fitzgerald", book.getAuthor());
        Assertions.assertEquals("Fiction", book.getGenre());
        Assertions.assertEquals(12.99, book.getPrice(), 0.01); // 0.01 is the delta for double comparison

        // Test the setters
        book.setTitle("New Title");
        Assertions.assertEquals("New Title", book.getTitle());

        book.setAuthor("New Author");
        Assertions.assertEquals("New Author", book.getAuthor());

        book.setGenre("New Genre");
        Assertions.assertEquals("New Genre", book.getGenre());

        book.setPrice(19.99);
        Assertions.assertEquals(19.99, book.getPrice(), 0.01);
    }

    @Test
    public void testGetReviews() {
        // Create a sample book with reviews
        List<String> reviews = new ArrayList<>();
        reviews.add("Great book!");
        reviews.add("Highly recommended!");

        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 12.99, reviews);

        // Test getReviews()
        List<String> bookReviews = book.getReviews();
        Assertions.assertEquals(2, bookReviews.size());
        Assertions.assertEquals("Great book!", bookReviews.get(0));
        Assertions.assertEquals("Highly recommended!", bookReviews.get(1));

        // Test setReviews()
        List<String> newReviews = new ArrayList<>();
        newReviews.add("Awesome book!");
        newReviews.add("Must read!");

        book.setReviews(newReviews);

        bookReviews = book.getReviews();
        Assertions.assertEquals(2, bookReviews.size());
        Assertions.assertEquals("Awesome book!", bookReviews.get(0));
        Assertions.assertEquals("Must read!", bookReviews.get(1));
    }
}