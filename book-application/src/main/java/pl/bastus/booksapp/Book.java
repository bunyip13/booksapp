package pl.bastus.booksapp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("unused")
@Entity
@Table(name = "booksapp", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Book {
    private int bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookDate;
    private LocalDate bookAddedDate;
    private float bookPrice;
    private DateTimeFormatter day = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /*
    Book(String bookId, String bookTitle, String bookDate, String bookPrice, String bookAuthor, String bookAddedDate) {
        this.bookId = Integer.valueOf(bookId);
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookDate = bookDate;
        this.bookAddedDate = LocalDate.parse(bookAddedDate);
        this.bookPrice = Float.parseFloat(bookPrice);
    }

    Book(String bookTitle, String bookDate, String bookPrice, String bookAuthor, String bookAddedDate) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookDate = bookDate;
        this.bookPrice = Float.parseFloat(bookPrice);
        this.bookAddedDate = LocalDate.parse(bookAddedDate);
    }
    */

    Book(int bookId, String bookTitle, String bookAuthor, String bookDate, LocalDate bookAddedDate, float bookPrice) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookDate = bookDate;
        this.bookAddedDate = bookAddedDate;
        this.bookPrice = bookPrice;
    }

    Book(String bookTitle, String bookAuthor, String bookDate, LocalDate bookAddedDate, float bookPrice) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookDate = bookDate;
        this.bookAddedDate = bookAddedDate;
        this.bookPrice = bookPrice;
    }

    Book() {
    }

    private Book(BookBuilder bb) {
        this.bookId = bb.bookId;
        this.bookTitle = bb.bookTitle;
        this.bookAuthor = bb.bookAuthor;
        this.bookDate = bb.bookDate;
        this.bookAddedDate = bb.bookAddedDate;
    }

    private static class BookBuilder {
        private int bookId;
        private String bookTitle;
        private String bookAuthor;
        private String bookDate;
        private LocalDate bookAddedDate;
        private float bookPrice;

        public BookBuilder(){}

        public BookBuilder bookId(int bookId) {
            this.bookId = bookId;
            return this;
        }

        public BookBuilder bookTitle(String bookTitle) {
            this.bookTitle = bookTitle;
            return this;
        }

        public BookBuilder bookAuthor(String bookAuthor) {
            this.bookAuthor = bookAuthor;
            return this;
        }

        public BookBuilder bookDate(String bookDate) {
            this.bookDate = bookDate;
            return this;
        }

        public BookBuilder bookAddedDate(LocalDate bookAddedDate) {
            this.bookAddedDate = bookAddedDate;
            return this;
        }

        public BookBuilder bookPrice(float bookPrice) {
            this.bookPrice = bookPrice;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
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

    /* Book ID */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, length = 11)
    int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    /*Book Title */
    @Column(name = "title", length = 30)
    String getBookTitle() {
        return bookTitle;
    }
    void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    /* Author */
    @Column(name = "author", length = 30)
    String getBookAuthor() {
        return bookAuthor;
    }
    void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    /* Date */
    @Column(name = "date", length = 4)
    String getBookDate() {
        return bookDate;
    }
    void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    /* Date added */
    @Column(name = "date_added")
    LocalDate getBookAddedDate() {
        return bookAddedDate;
    }
    void setBookAddedDate(LocalDate bookAddedDate) {
        this.bookAddedDate = bookAddedDate;
    }
    /*String getBookAddedDateString() {
        return bookAddedDate.format(day);
    }
    void setBookAddedDate(String bookAddedDate) {
        this.bookAddedDate = LocalDate.parse(bookAddedDate, day);
    }*/

    /* Price */
    @Column(name = "price")
    float getBookPrice() {
        return bookPrice;
    }
    void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }
    /*String getBookPriceString() {
        return Float.toString(bookPrice);
    }*/

    @Override
    public String toString() {
        return "Title: " +getBookTitle()+ ", author: " +getBookAuthor()+ ", date: " +getBookDate()+
                ", date added: " +getBookAddedDate()+ ", price: " +getBookPrice();
    }
}