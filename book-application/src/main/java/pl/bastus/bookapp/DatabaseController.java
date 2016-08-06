package pl.bastus.bookapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

class DatabaseController {

    private static Connection connectDatabase() {
        Properties props = new Properties();
        Connection conn = null;
        try {
            FileInputStream fis = new FileInputStream("db.properties");
            props.load(fis);
            conn = DriverManager.getConnection(
                    props.getProperty("DB_URL"),
                    props.getProperty("DB_USERNAME"),
                    props.getProperty("DB_PASSWORD"));
        } catch ( IOException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /*
    void disconnectDatabase() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }*/

    @SuppressWarnings("unused")
    private void addToDatabase(String title, String author,
                         String date, LocalDate date_added, Float price) {
        String query = "INSERT INTO booksapp VALUES (" +
                "NULL, '"+title+"', '"+author+"', '"+date+"', '"+date_added+"', '"+price+"');";

        try (Connection conn = connectDatabase();
             Statement stmt = conn.createStatement()) {
            stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void addBookToDatabase(Book book) {
        String bookTitle = book.getBookTitle();
        String bookAuthor = book.getAuthor();
        String bookDate = book.getBookDate();
        float bookPrice = book.getBookPrice();
        LocalDate bookAddedDate = book.getBookAddedDate();
        String query = "INSERT INTO booksapp VALUES (" +
                "NULL, '"
                + bookTitle + "', '"
                + bookAuthor + "', '"
                + bookDate + "', '"
                + bookAddedDate + "', '"
                + bookPrice + "');";
        try (Connection conn = connectDatabase();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    private void addBooksToDatabase(List<Book> bookList) {
        for (Book book : bookList) {
            addBookToDatabase(book);
        }
        System.out.println("Books added");
    }

    /*
    void getTablesFromDatabase(String table) {
        int tableInt = Integer.valueOf(table);
        try {
            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            ArrayList<String> tables = new ArrayList<>();
            int i = 0;
            while (rs.next()) {
                System.out.println(i + " " + rs.getString(3));
                i++;
                tables.add(rs.getString(3));
            }
            //getBooksFromDatabase(tables.get(tableInt)); // TODO: no arg
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }*/

    void getBooksFromDatabase() {
        final String QUERY = "SELECT id,title,author,date,date_added,price FROM booksapp";

        try(Connection con = connectDatabase();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY)) {

            while(rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String date = rs.getString("date");
                String date_added = rs.getString("date_added");
                String price = rs.getString("price");
                System.out.println("ID: " + id +
                        ", Title: " + title +
                        ", Author: " + author +
                        ", Date: " + date +
                        ", Date added: " + date_added +
                        ", Price: " + price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    void getBooksFromDatabase1() {
        // columns from table
        final String QUERY = "SELECT * FROM booksapp";
        try (Connection con = connectDatabase();
             Statement stmt = con.createStatement()) {
            DatabaseMetaData md = con.getMetaData();
            //ResultSet rs = stmt.executeQuery(QUERY));
            ResultSet rs = md.getColumns(null, null, "booksapp", "%"); //sqlowy znaczek odpowiadający gwiazdce
            ArrayList<String> columns = new ArrayList<>();
            while (rs.next()) {
                columns.add(rs.getString(4));
            }
            // print the data
            ResultSet rs1 = stmt.executeQuery(QUERY);
            while (rs1.next()) {
                for (String colName : columns) {
                    Object colVar = rs1.getObject(colName);
                    System.out.println(colName + ": " + colVar);
                }
                System.out.println();
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    void removeBookFromDatabaseByID(String stringID) {
        int id = Integer.valueOf(stringID);
        String QUERY = "DELETE FROM booksapp WHERE id = " + id;

        try (Connection con = connectDatabase();
             Statement stmt = con.createStatement()) {
            stmt.execute(QUERY);
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }

    void removeBookFromDatabaseByTitle(String title) {
        String QUERY = "DELETE FROM booksapp WHERE title = '" + title + "'";

        try (Connection con = connectDatabase();
             Statement stmt = con.createStatement()) {
            stmt.execute(QUERY);
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }
    /*
    private void updateBookInDatabase(Book book) { // TODO: pass book
        ArrayList<String> columns = new ArrayList<>();
        try {
            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getColumns(null, null, "booksapp", "%");
            while (rs.next()) {
                columns.add(rs.getString(4));
            }
            // get values we want to change columns to
            System.out.println("Enter the new values below");
            ArrayList<String> colVals = new ArrayList<>();
            for (String column : columns) {
                System.out.println(column + ": ");
                colVals.add(title);
            }
            // create query
            Statement stmt = conn.createStatement();
            for (int i = 0; i < columns.size(); i++) {
                String query = "UPDATE booksapp SET "
                        + columns.get(i) + " = '"
                        + colVals.get(i) + "' WHERE "
                        + " id = " + id;
                stmt.executeUpdate(query);
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }*/
}
