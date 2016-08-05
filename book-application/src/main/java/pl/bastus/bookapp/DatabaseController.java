package pl.bastus.bookapp;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class DatabaseController {
    private Connection conn = null;
    private Scanner scan;

    void connectDatabase() {
        String userName = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/javatutorials?useSSL=false";
        try {
            conn = DriverManager.getConnection(url,userName,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void disconnectDatabase() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unused")
    private void addToDatabase(String title, String author,
                         String date, LocalDate date_added, Float price) {
        try {
            String query = "INSERT INTO booksapp VALUES (" +
                    "NULL, '"+title+"', '"+author+"', '"+date+"', '"+date_added+"', '"+price+"');";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            if (conn != null) {
                conn.close();
            }
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
        try {
            String query = "INSERT INTO booksapp VALUES (" +
                    "NULL, '"
                    + bookTitle + "', '"
                    + bookAuthor + "', '"
                    + bookDate + "', '"
                    + bookAddedDate + "', '"
                    + bookPrice + "');";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            if (conn != null) {
                conn.close();
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
        System.out.println("Books added");
    }

    void getBooksFromDatabase() {
        System.out.println("Which table do you want to view?");
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
            scan = new Scanner(System.in);
            int input = scan.nextInt();
            printData(tables.get(input));
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }

    private void printData(String table) {
        // columns from table
        try {
            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getColumns(null, null, table, "%");
            //int i = 0;
            ArrayList<String> columns = new ArrayList<>();
            while (rs.next()) { // TODO: for sure we could make it work in java 8 way
                columns.add(rs.getString(4));
                //i++;
            }

            // print the data
            Statement stmt = conn.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM " + table);
            //int j = 0;
            while (rs1.next()) {
                for (String colName : columns) {
                    Object colVar = rs1.getObject(colName);
                    System.out.println(colName + ": " + colVar);
                }
                //j++;
                System.out.println();
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }

    private void removeBookFromDatabaseByID() {
        System.out.println("Enter the ID of the book you want to remove:");
        int id = scan.nextInt();
        try {
            String query = "DELETE FROM booksapp WHERE id = " + id;
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            stmt.close();
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }

    void removeBookFromDatabaseByTitle(String title) {
        try {
            String query = "DELETE FROM booksapp WHERE title = '" + title + "'";
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            stmt.close();
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }

}
