package pl.bastus.bookapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Book {
    private String bookTitle;
    private LocalDate bookDate;
    private float bookPrice;
    private String author;
    private LocalDate bookAddedDate;

    private DateTimeFormatter day = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");

    Book(String bookTitle, String bookDate, String bookPrice, String author, String bookAddedDate) {
        this.bookTitle = bookTitle;
        this.bookDate = LocalDate.parse(bookDate);
        this.bookPrice = Float.parseFloat(bookPrice);
        this.author = author;
        this.bookAddedDate = LocalDate.parse(bookAddedDate);
    }

    Book(String bookTitle, LocalDate bookDate, float bookPrice, String author, LocalDate bookAddedDate) {
        this.bookTitle = bookTitle;
        this.bookDate = bookDate;
        this.bookPrice = bookPrice;
        this.author = author;
        this.bookAddedDate = bookAddedDate;
    }

    Book() {
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
            System.out.println("Class cast exception, probably b !(instaceof Book)");
        }
        return getBookTitle().equals(book.getBookTitle());
    }

    @Override
    public int hashCode() {
        return getBookTitle().hashCode();
    }

    /*
    public String showBookInfo() {
        return getBookTitle() + ", date: " + getBookDate() + ", price: " + getBookPrice() + ", author: " + getAuthor();
    }
    */

    /*Book Title */
    String getBookTitle() {
        return bookTitle;
    }

    void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    /* Date */
    LocalDate getBookDate() {
        return bookDate;
    }

    String getBookDateString() {
        return bookDate.format(year);
    }

    void setBookDate(LocalDate bookDate) {
        this.bookDate = bookDate;
    }

    void setBookDate(String bookDate) {
        this.bookDate = LocalDate.parse(bookDate);
    }

    /* Price */
    float getBookPrice() {
        return bookPrice;
    }

    void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }

    /* Author */
    String getAuthor() {
        return author;
    }

    void setAuthor(String author) {
        this.author = author;
    }

    /* Date added */
    LocalDate getBookAddedDate() {
        return bookAddedDate;
    }

    String getBookAddedDateString() {
        return bookAddedDate.format(day);
    }

    void setBookAddedDate(LocalDate bookAddedDate) {
        this.bookAddedDate = bookAddedDate;
    }

    void setBookAddedDate(String bookAddedDate) {
        this.bookAddedDate = LocalDate.parse(bookAddedDate);
    }

    @Override
    public String toString() {
        return bookTitle;
    }
}