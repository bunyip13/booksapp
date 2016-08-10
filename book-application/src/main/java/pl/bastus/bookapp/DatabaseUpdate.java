package pl.bastus.bookapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

class DatabaseUpdate {
    private static final String UPDATE_BOOK_ID_QUERY =
            "UPDATE booksapp SET title=? , author=? , date=? , date_added=? , price=? WHERE id=?";
    private static final String UPDATE_BOOK_TITLE_QUERY =
            "UPDATE booksapp SET title = ? author = ? date = ? date_added = ? price = ? WHERE title = '?'";

    void updateBookDatabaseByID(int id, String bookTitle, String bookAuthor, String bookDate, LocalDate bookAddedDate, float bookPrice) {
        try (Connection con = DatabaseConnect.connectDatabase()) {
            try (PreparedStatement ps = con.prepareStatement(UPDATE_BOOK_ID_QUERY)) {
                con.setAutoCommit(false);
                ps.setString(1, bookTitle);
                ps.setString(2, bookAuthor);
                ps.setString(3, bookDate);
                ps.setDate(4, java.sql.Date.valueOf(bookAddedDate));
                ps.setFloat(5, bookPrice);
                ps.setInt(6, id);
                ps.executeUpdate(); // TODO: update not working
                con.commit();
                System.out.println("addBookToDatabase succesfull");
            } catch (SQLException sql) {
                sql.printStackTrace();
                try {
                    con.rollback();
                    System.out.println("JDBC Transaction rolled back successfully");
                } catch (SQLException sql2) {
                    sql2.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void updateBookDatabaseByTitle(String oldTitle, String bookTitle, String bookAuthor, String bookDate, LocalDate bookAddedDate, float bookPrice) {
        try (Connection con = DatabaseConnect.connectDatabase()) {
            try (PreparedStatement ps = con.prepareStatement(UPDATE_BOOK_TITLE_QUERY)) {
                con.setAutoCommit(false);
                ps.setString(1, bookTitle);
                ps.setString(2, bookAuthor);
                ps.setString(3, bookDate);
                ps.setDate(4, java.sql.Date.valueOf(bookAddedDate));
                ps.setFloat(5, bookPrice);
                ps.setString(6, oldTitle);
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
}
