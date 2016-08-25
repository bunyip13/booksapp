package pl.bastus.booksapp;

import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("unused")
class BookDAO {
	private BookDAO bookDAO;
	private ArrayList<Book> books = new ArrayList<>();

	/* GETTERS && SETTERS */
	void addBookToBooks(Book b) {
		books.add(b);
	}

	@SuppressWarnings("unused")
	private ArrayList<Book> getBooks() {
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

	/* SHOWING */
	void showBooks() {
		printShowBooks();
		//new DatabaseControllerDAO().getBooksToList();
	}

	private void printShowBooks() {
		System.out.println();
		System.out.println("#################");
		System.out.println("#   Book list   #");
		System.out.println("#################");
		System.out.println();
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

    /* SORTING */ // TODO: sorting
	@SuppressWarnings("unused")
	void sortTitle() {
		Collections.sort(books, (Book b1, Book b2) -> b1.getBookTitle().compareTo(b2.getBookTitle()));
		books.forEach(Book::toString);
	}
	@SuppressWarnings("unused")
	void sortAuthor() {
		Collections.sort(books, (Book b1, Book b2) -> b1.getBookAuthor().compareTo(b2.getBookAuthor()));
		books.forEach(Book::toString);
	}
	/*@SuppressWarnings("unused")
	void sortPrice() {
		Collections.sort(books, (Book b1, Book b2) -> b1.getBookPrice().compareTo(b2.getBookPrice()));
		books.forEach(Book::toString);
	}*/
	@SuppressWarnings("unused")
	void sortDate() {
		Collections.sort(books, (Book b1, Book b2) -> b1.getBookDate().compareTo(b2.getBookDate()));
		books.forEach(Book::toString);
	}

	/* END */
}