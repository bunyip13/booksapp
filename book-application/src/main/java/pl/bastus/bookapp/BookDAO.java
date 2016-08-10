package pl.bastus.bookapp;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class BookDAO implements Input {
	private List<Book> books = new LinkedList<>();

	/* GETTERS && SETTERS */
	@SuppressWarnings("unused")
	public void addBookToBooks(Book b) {
		books.add(b);
	}

	@SuppressWarnings("unused")
	public List<Book> getBooks() {
		return books;
	}

	@SuppressWarnings("unused")
	public Book getBookByID(Integer id) { // TODO: take id from database, not from array
		if (id<books.size()) {
			return books.get(id);
		} else {
			return null;
		}
	}

	/* MENU */
	void menuDisplay() throws SQLException {
		String userChoice;
		do {
			System.out.println();
			System.out.println("What to do now?");
			System.out.println("[1] Add book");
			System.out.println("[2] Update book");
			System.out.println("[3] Delete book");
			System.out.println("[4] Show books");
			System.out.println("[x] Exit");

			userChoice = getUserInput();
			switch (userChoice) {
				case "1":
					AddBookDAO ab = new AddBookDAO();
					ab.passAddedBook();
					break;
				case "2":
					UpdateBookDAO ub = new UpdateBookDAO();
					ub.updateBookByID();
					break;
				case "3":
					RemoveBookDAO rb = new RemoveBookDAO();
					rb.removeBook();
					break;
				case "4":
					showBooks();
					break;
			}
		} while (!userChoice.equalsIgnoreCase("x"));
	}

	/* SHOWING */
	private void showBooks() {
		System.out.println();
		System.out.println("#################");
		System.out.println("#   Book list   #");
		System.out.println("#################");
		System.out.println();

        DatabaseController dbc = new DatabaseController();
		dbc.getBooksFromDatabase();
	}

		/*
		Book book;
		for (int i = 0; i < getBooks().size(); i++) {
			book = getBooks().get(i);
			System.out.println(i + ": " + book.getBookTitle() + ", " + book.getAuthor() + " (" + book.getBookDate() + ")");
		}
		System.out.println();

		Pattern numberPattern = Pattern.compile("[0-9]+");
		String loadedNumber;
		do {
			System.out.print("Which book to show?");
			loadedNumber = getUserInput();
		}
		while (!numberPattern.matcher(loadedNumber).matches());

		Integer bookNumber = Integer.parseInt(loadedNumber);
		if (getBooks().size() > bookNumber) {
			Book chosenBook = getBooks().get(bookNumber);
			System.out.println("Your choice: " + chosenBook.getBookTitle()
                    + ", author " + chosenBook.getAuthor()
					+ ", price " + chosenBook.getBookPrice()
					+ ", date " + chosenBook.getBookDate()
                    + ", date added " + chosenBook.getBookAddedDate()
            );
		}
		else {
			System.out.println("No such book, sorry!");
		}*/
}