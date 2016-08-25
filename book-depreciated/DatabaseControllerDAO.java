package pl.bastus.booksapp;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
class DatabaseControllerDAO implements DatabaseController {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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
    }
    */

    public void addToDatabase(Book book) {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(INSERT_BOOK_QUERY)) {
                con.setAutoCommit(false);
                ps.setString(1, book.getBookTitle());
                ps.setString(2, book.getBookAuthor());
                ps.setString(3, book.getBookDate());
                ps.setDate(4, java.sql.Date.valueOf(book.getBookAddedDate()));
                ps.setFloat(5, book.getBookPrice());
                int out = ps.executeUpdate();
                con.commit();
                System.out.println("addBookToDatabase succesfull");
                if(out !=0) {
                    System.out.println("Book " +book.getBookTitle()+ " added.");
                } else {
                    System.out.println("Book " +book.getBookTitle()+ " adding failed");
                }
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

    public void addBooksToDatabase(List<Book> bookList) {
        for (Book book : bookList) {
            addToDatabase(book);
        }
        System.out.println("addBooksToDatabase succesfull");
    }

    public void updateBookDatabaseSelectID(int id, Book book) {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(UPDATE_BOOK_ID_QUERY)) {
                con.setAutoCommit(false);
                ps.setString(1, book.getBookTitle());
                ps.setString(2, book.getBookAuthor());
                ps.setString(3, book.getBookDate());
                ps.setDate(4, java.sql.Date.valueOf(book.getBookAddedDate()));
                ps.setFloat(5, book.getBookPrice());
                ps.setInt(6, id);
                int out = ps.executeUpdate();
                con.commit();
                if(out !=0) {
                    System.out.println("Book updated with id = " +book.getBookId());
                } else {
                    System.out.println("No Book found with id = " +book.getBookId());
                }
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

    public void updateBookDatabaseID(Book book) {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(UPDATE_BOOK_ID_QUERY)) {
                con.setAutoCommit(false);
                ps.setString(1, book.getBookTitle());
                ps.setString(2, book.getBookAuthor());
                ps.setString(3, book.getBookDate());
                ps.setDate(4, java.sql.Date.valueOf(book.getBookAddedDate()));
                ps.setFloat(5, book.getBookPrice());
                ps.setInt(6, book.getBookId());
                int out = ps.executeUpdate();
                con.commit();
                if(out !=0) {
                    System.out.println("Book updated with id = " +book.getBookId());
                } else {
                    System.out.println("No Book found with id = " +book.getBookId());
                }
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

    public void updateBookDatabaseByTitle(String oldTitle, String bookTitle, String bookAuthor,
                                          String bookDate, LocalDate bookAddedDate, float bookPrice) {
        try (Connection con = dataSource.getConnection()) {
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

    public void updateBookDatabaseByTitle(String oldTitle, Book book) {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(UPDATE_BOOK_TITLE_QUERY)) {
                con.setAutoCommit(false);
                ps.setString(1, book.getBookTitle());
                ps.setString(2, book.getBookAuthor());
                ps.setString(3, book.getBookDate());
                ps.setDate(4, java.sql.Date.valueOf(book.getBookAddedDate()));
                ps.setFloat(5, book.getBookPrice());
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

    public void removeBookFromDatabaseByID(String stringID) {
        int id = Integer.valueOf(stringID);
        try (Connection con = dataSource.getConnection()) {
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

    public void removeBookFromDatabaseByID(int id) {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(REMOVE_BOOK_ID_QUERY)) {
                con.setAutoCommit(false);
                ps.setInt(1, id); // jedynka i kolejne ewentualne liczby odnoszą się do ilości znaków zapytania
                int out = ps.executeUpdate();
                con.commit();

                if(out !=0) {
                    System.out.println("removeBookFromDatabaseByID succesfull for ID = " + id);
                } else {
                    System.out.println("No Book found with id = " + id);
                }
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

    public void removeBookFromDatabaseByTitle(String title) {
        try (Connection con = dataSource.getConnection()) {
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

    public Book getBookById(int id) {
        Book book = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BOOKS_ID_QUERY)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                book = new Book(rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("date"),
                        rs.getString("date_added"),
                        rs.getString("price"));
                System.out.println("Book " +book.toString()+ " loaded succesfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("getBooksFromDatabase succesfull");
        return book;
    }

    public List<Book> getBooksToList() {
        List<Book> bookList = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL_BOOKS_QUERY)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Book book = new Book(rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("date"),
                        rs.getString("date_added"),
                        rs.getString("price"));
                bookList.add(book);
                System.out.println("Book " +book.toString()+ " loaded succesfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("getBooksFromDatabase succesfull");
        return bookList;
    }
}