package pl.bastus.bookapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

class DatabaseAdd {
    private static final String INSERT_BOOK_QUERY = "INSERT INTO booksapp VALUES (NULL, ?, ?, ?, ?, ?)";

    @SuppressWarnings("unused")
    void addToDatabase(String title, String author,
                       String date, LocalDate date_added, Float price) {
        try (Connection con = DatabaseConnect.connectDatabase()) {
            try (PreparedStatement ps = con.prepareStatement(INSERT_BOOK_QUERY)) {
                // set the parameter, prepareStatement uses wildcards and setString for,
                // suprise, prepare statement
                con.setAutoCommit(false);
                ps.setString(1, title); // jedynka i kolejne ewentualne liczby odnoszą się do ilości znaków zapytania
                ps.setString(2, author);
                ps.setString(3, date);
                ps.setDate(4, java.sql.Date.valueOf(date_added));
                ps.setFloat(5, price);
                ps.executeUpdate();
                con.commit();
                System.out.println("addToDatabase succesfull");
            } catch (SQLException sql) {
                sql.printStackTrace();
                try {
                    con.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("")
    private void addBookToDatabase(Book book) {
        String bookTitle = book.getBookTitle();
        String bookAuthor = book.getAuthor();
        String bookDate = book.getBookDate();
        float bookPrice = book.getBookPrice();
        LocalDate bookAddedDate = book.getBookAddedDate();

        try (Connection con = DatabaseConnect.connectDatabase()) {
            try (PreparedStatement ps = con.prepareStatement(INSERT_BOOK_QUERY)) {
                con.setAutoCommit(false);
                ps.setString(1, bookTitle);
                ps.setString(2, bookAuthor);
                ps.setString(3, bookDate);
                ps.setDate(4, java.sql.Date.valueOf(bookAddedDate));
                ps.setFloat(5, bookPrice);
                ps.executeUpdate();
                con.commit();
                System.out.println("addBookToDatabase succesfull");
            } catch (SQLException sql) {
                sql.printStackTrace();
                try {
                    con.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    private void addBooksToDatabase(List<Book> bookList) {
        for (Book book : bookList) {
            addBookToDatabase(book);
        }
        System.out.println("addBooksToDatabase succesfull");
    }
}
