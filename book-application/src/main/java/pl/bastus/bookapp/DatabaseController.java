package pl.bastus.bookapp;

import java.sql.*;
import java.util.ArrayList;

class DatabaseController {
    //private static final String SELECT_BOOK_QUERY = "SELECT ?,?,?,?,?,? FROM booksapp";
    private static final String SELECT_ALL_BOOKS_QUERY = "SELECT * FROM booksapp";

    /*
    First call to ResultSet next() method call moves the cursor to the first row
    and subsequent calls moves the cursor to next rows in the result set.
    If there are no more rows then it returns false and come out of the while loop.
    We are using result set getXXX() method to get the columns value and then writing them to the console.
    */

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
            //getBooksFromDatabase(tables.get(tableInt));
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }*/

    void getBooksFromDatabase() { // TODO: prepared statement
        try (Connection con = DatabaseConnect.connectDatabase();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL_BOOKS_QUERY)) {

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
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