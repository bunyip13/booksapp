package pl.bastus.booksapp;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
interface DatabaseController {
    String INSERT_BOOK_QUERY = "INSERT INTO booksapp VALUES (NULL, ?, ?, ?, ?, ?)";
    String SELECT_ALL_BOOKS_QUERY = "SELECT * FROM booksapp";
    String SELECT_BOOKS_ID_QUERY = "SELECT id, title, author, date, date_added, price FROM booksapp WHERE id = ?";
    String UPDATE_BOOK_ID_QUERY =
            "UPDATE booksapp SET title = ? , author = ? , date = ? , date_added = ? , price = ? WHERE id = ?";
    String UPDATE_BOOK_TITLE_QUERY =
            "UPDATE booksapp SET title = ? , author = ? , date = ? , date_added = ? , price = ? WHERE title = ?";
    String REMOVE_BOOK_ID_QUERY = "DELETE FROM booksapp WHERE id = ?";
    String REMOVE_BOOK_TITLE_QUERY = "DELETE FROM booksapp WHERE title = ?";

    /* ADD */
    void addToDatabase(Book book);
    void addBooksToDatabase(List<Book> bookList);

    /* UPDATE */
    void updateBookDatabaseSelectID(int id, Book book);
    void updateBookDatabaseID(Book book);
    void updateBookDatabaseByTitle(String oldTitle, String bookTitle, String bookAuthor,
                                   String bookDate, LocalDate bookAddedDate, float bookPrice);
    void updateBookDatabaseByTitle(String oldTitle, Book book);

    /* REMOVE */
    void removeBookFromDatabaseByID(String stringID);
    void removeBookFromDatabaseByID(int id);
    void removeBookFromDatabaseByTitle(String title);

    /* GET */
    Book getBookById(int id);
    List<Book> getBooksToList();
}
