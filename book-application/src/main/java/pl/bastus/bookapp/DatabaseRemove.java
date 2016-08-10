package pl.bastus.bookapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class DatabaseRemove {
    private static final String REMOVE_BOOK_ID_QUERY = "DELETE FROM booksapp WHERE id = ?";
    private static final String REMOVE_BOOK_TITLE_QUERY = "DELETE FROM booksapp WHERE title = ?";

    @SuppressWarnings("unused")
    void removeBookFromDatabaseByID(String stringID) {
        int id = Integer.valueOf(stringID);
        try (Connection con = DatabaseConnect.connectDatabase()) {
            try (PreparedStatement ps = con.prepareStatement(REMOVE_BOOK_ID_QUERY)) {
                con.setAutoCommit(false);
                ps.setInt(1, id); // jedynka i kolejne ewentualne liczby odnoszą się do ilości znaków zapytania
                ps.executeUpdate();
                con.commit();
                System.out.println("removeBookFromDatabaseByID succesfull for ID = " + id);
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
    void removeBookFromDatabaseByTitle(String title) {
        try (Connection con = DatabaseConnect.connectDatabase()) {
            try (PreparedStatement ps = con.prepareStatement(REMOVE_BOOK_TITLE_QUERY)) {
                con.setAutoCommit(false);
                ps.setString(1, title);
                ps.executeUpdate();
                con.commit();
                System.out.println("removeBookFromDatabaseByTitle succesfull for Title = " + title);
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
