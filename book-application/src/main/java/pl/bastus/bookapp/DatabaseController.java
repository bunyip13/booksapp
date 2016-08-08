package pl.bastus.bookapp;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class DatabaseController {

    /*
    First call to ResultSet next() method call moves the cursor to the first row
    and subsequent calls moves the cursor to next rows in the result set.
    If there are no more rows then it returns false and come out of the while loop.
    We are using result set getXXX() method to get the columns value and then writing them to the console.
    */

    @SuppressWarnings("unused")
    private void addToDatabase(String title, String author,
                         String date, LocalDate date_added, Float price) {
        /*String query = "INSERT INTO booksapp VALUES (" +
                "NULL, '"+title+"', '"+author+"', '"+date+"', '"+date_added+"', '"+price+"');";*/
        String query = "INSERT INTO booksapp VALUES (NULL, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnect.connectDatabase();
             PreparedStatement ps = conn.prepareStatement(query)) {
            // set the parameter, prepareStatement uses wildcards and setString for,
            // suprise, prepare statement
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, date);
            ps.setDate(4, java.sql.Date.valueOf(date_added));
            ps.setFloat(5, price);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Title=" + rs.getString("title")
                        + ",author=" + rs.getString("author")
                        + ",date=" + rs.getString("date")
                        + ",date added=" + rs.getString("date_added")
                        + ",price=" + rs.getString("price"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("addToDatabase succesfull");
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

        try (Connection conn = DatabaseConnect.connectDatabase();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, bookTitle);
            ps.setString(2, bookAuthor);
            ps.setString(3, bookDate);
            ps.setDate(4, java.sql.Date.valueOf(bookAddedDate));
            ps.setFloat(5, bookPrice);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Title=" + rs.getString("title")
                        + ",author=" + rs.getString("author")
                        + ",date=" + rs.getString("date")
                        + ",date added=" + rs.getString("date_added")
                        + ",price=" + rs.getString("price"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("addBookToDatabase succesfull");
    }

    @SuppressWarnings("unused")
    private void addBooksToDatabase(List<Book> bookList) {
        for (Book book : bookList) {
            addBookToDatabase(book);
        }
        System.out.println("addBooksToDatabase succesfull");
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

    void getBooksFromDatabase() { // TODO: prepared statement
        final String QUERY = "SELECT id,title,author,date,date_added,price FROM booksapp";

        try(Connection con = DatabaseConnect.connectDatabase();
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
        System.out.println("getBooksFromDatabase succesfull");
    }

    @SuppressWarnings("unused")
    void getBooksFromDatabase1() { // TODO: prepared statement
        // columns from table
        final String QUERY = "SELECT * FROM booksapp";
        try (Connection con = DatabaseConnect.connectDatabase();
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
        System.out.println("getBooksFromDatabase1 succesfull");
    }

    @SuppressWarnings("unused")
    void removeBookFromDatabaseByID(String stringID) { // TODO: prepared statement
        int id = Integer.valueOf(stringID);
        String QUERY = "DELETE FROM booksapp WHERE id = " + id;

        try (Connection con = DatabaseConnect.connectDatabase();
             Statement stmt = con.createStatement()) {
            stmt.execute(QUERY);
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        System.out.println("removeBookFromDatabaseByID succesfull");
    }

    void removeBookFromDatabaseByTitle(String title) { // TODO: prepared statement
        String QUERY = "DELETE FROM booksapp WHERE title = '" + title + "'";

        try (Connection con = DatabaseConnect.connectDatabase();
             Statement stmt = con.createStatement()) {
            stmt.execute(QUERY);
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        System.out.println("removeBookFromDatabaseByTitle succesfull");
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