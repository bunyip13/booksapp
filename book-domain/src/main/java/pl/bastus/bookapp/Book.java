package pl.bastus.bookapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Book {
    private String bookTitle;
    private String bookDate;
    private float bookPrice;
    private String author;
    private LocalDate bookAddedDate;

    private DateTimeFormatter day = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /* String constructor */
    @SuppressWarnings("unused")
    Book(String bookTitle, String bookDate, String bookPrice, String author, String bookAddedDate) {
        this.bookTitle = bookTitle;
        this.bookDate = bookDate;
        this.bookPrice = Float.parseFloat(bookPrice);
        this.author = author;
        this.bookAddedDate = LocalDate.parse(bookAddedDate);
    }

    /* Normal constructor */
    @SuppressWarnings("unused")
    Book(String bookTitle, String bookDate, float bookPrice, String author, LocalDate bookAddedDate) {
        this.bookTitle = bookTitle;
        this.bookDate = bookDate;
        this.bookPrice = bookPrice;
        this.author = author;
        this.bookAddedDate = bookAddedDate;
    }

    public Book() {
    }

    @Override
    public boolean equals(Object b) {
        Book book = null;
        if (b == null) {
            return false;
        }
        if (!(b instanceof Book)) {
            return false;
        }
        try {
            book = (Book) b;
        } catch (ClassCastException cce) {
            cce.printStackTrace();
            System.out.println("Class cast exception, probably !(instaceof Book)");
        }
        return getBookTitle().equals(book.getBookTitle());
    }

    @Override
    public int hashCode() {
        return getBookTitle().hashCode();
    }

    @SuppressWarnings("unused")
    String showBookInfo() {
        return getBookTitle() + ", date: " + getBookDate() + ", price: " + getBookPrice() + ", author: " + getAuthor();
    }


    /*Book Title */
    String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    /* Date */
    String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    /* Price */
    float getBookPrice() {
        return bookPrice;
    }

    @SuppressWarnings("unused")
    String getBookPriceString() {
        return Float.toString(bookPrice);
    }

    public void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }

    void setBookPrice(String bookPrice) {
        this.bookPrice = Float.parseFloat(bookPrice);
    }

    /* Author */
    String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    /* Date added */
    LocalDate getBookAddedDate() {
        return bookAddedDate;
    }

    @SuppressWarnings("unused")
    String getBookAddedDateString() {
        return bookAddedDate.format(day);
    }

    void setBookAddedDate(LocalDate bookAddedDate) {
        this.bookAddedDate = bookAddedDate;
    }

    @SuppressWarnings("unused")
    void setBookAddedDate(String bookAddedDate) {
        this.bookAddedDate = LocalDate.parse(bookAddedDate, day);
    }

    @Override
    public String toString() {
        return bookTitle;
    }
}