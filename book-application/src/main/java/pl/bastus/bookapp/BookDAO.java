package pl.bastus.bookapp;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookDAO {
	private List<Book> books = new LinkedList<>();
	private Book book;
	private DatabaseController dbc = new DatabaseController();
	private DateTimeFormatter day = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // SQL przechowuje daty z pauzami

	/* SCANNER */
	private static Scanner sc = new Scanner(System.in);
	@NotNull
	private String getUserInput() {
		return sc.nextLine().trim();
	}

	/* GETTERS */
	public List<Book> getBooks() {
		return books;
	}

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
			System.out.println("[2] Delete book"); // TODO: update / modify
			System.out.println("[3] Show books");
			System.out.println("[x] Exit");

			userChoice = getUserInput();
			switch (userChoice) {
				case "1":
					addBook();
					break;
				case "2":
					removeBook();
					break;
				case "3":
					showBooks();
					break;
			}
		} while (!userChoice.equalsIgnoreCase("x"));
	}

	/* ADDING */
	private void addBook() {
		System.out.println();
		System.out.println("################");
		System.out.println("#   Add Book   #");
		System.out.println("################");
		System.out.println();

		book = new Book();
		addTitle();
		addAuthor();
		addDate();
		addPrice();
		addedDate();
		System.out.println("Thank you. Book: " + book.getBookTitle()
				+ ", author: " + book.getAuthor()
                + ", price: " + book.getBookPrice()
				+ ", date: " + book.getBookDate()
				+ ", added date: " + book.getBookAddedDate() + " added.");
		dbc.addBookToDatabase(book);
	}

	private void addTitle() {
		System.out.print("Book title: ");
		book.setBookTitle(getUserInput());
		/*
		Pattern pattern = Pattern.compile("regex"); Matcher matcher =
		pattern.matcher("check this"); matcher.matches(); // true or false
		*/
	}

	private void addAuthor() {
		System.out.print("Book author: ");
		book.setAuthor(getUserInput());
	}

	private void addDate() {
//		Pattern datePattern = Pattern.compile("[0-3]?[0-9]\\.[0-1]?[0-9]\\.[0-2][0-9]{3}");
        Pattern datePattern = Pattern.compile("[1-2][0-9]{3}");
		String loadedDate;
		do {
            System.out.print("Date: ");
			loadedDate = getUserInput();
            Matcher datePattern_Matcher = datePattern.matcher(loadedDate);
			if (datePattern_Matcher.matches()) {
                try {
                    book.setBookDate(loadedDate);
                } catch (NullPointerException pe) {
                    System.out.println("Something not right, date pattern should match: 2014");
                }
            }
		}
		while (book.getBookDate() == null);
	}

	private void addedDate() {
        book.setBookAddedDate(LocalDate.parse(LocalDate.now().format(day), day));
	}

	private void addPrice() {
		Pattern pricePattern = Pattern.compile("[0-9]+(\\.[0-9]+)?");
		Integer bookPriceInt = null;
		do {
			Matcher pricePattern_Matcher;
			System.out.print("Book price: ");
			String priceLoaded_Scanner = getUserInput();
			pricePattern_Matcher = pricePattern.matcher(priceLoaded_Scanner);

			// if (pricePattern.matcher(priceLoaded_Scanner).matches()) {
			// shortcut
			if (pricePattern_Matcher.matches()) {
				book.setBookPrice(priceLoaded_Scanner);
				bookPriceInt = Math.round(book.getBookPrice());
			}
		}
		while (bookPriceInt == null);
		// while (Pattern.matches("[0-9]+(\\.[0-9]+)?", getbookPriceStr()));
	}

	public void addBookToBooks(Book b) {
		books.add(b);
		// System.out.println("Book added!");
	}

	/* SHOWING */
	private void showBooks() {
		System.out.println();
		System.out.println("#################");
		System.out.println("#   Book list   #");
		System.out.println("#################");
		System.out.println();

		dbc.getBooksFromDatabase(); // TODO: only one table, so maybe only print it out?
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
		}

	/* REMOVING */
	private void removeBook() {
		System.out.println();
		System.out.println("###################");
		System.out.println("#   Remove book   #");
		System.out.println("###################");
		System.out.println();

		String userChoice;
		do {
			System.out.println();
			System.out.println("Search book by id or title?");
			System.out.println("[1] ID");
			System.out.println("[2] Title");
			System.out.println("[x] Exit");

			userChoice = getUserInput();
			switch (userChoice) {
				case "1":
					removeBookByID();
					break;
				case "2":
					removeBookByTitle();
			}
		} while (!userChoice.equalsIgnoreCase("x"));
	}

	private void removeBookByID() {
		System.out.println();
		System.out.println("#########################");
		System.out.println("#   Remove book by ID   #");
		System.out.println("#########################");
		System.out.println();
		System.out.println("Enter the ID of the book you want to remove:");

		String userChoice = getUserInput();
		dbc.removeBookFromDatabaseByID(userChoice);
	}

	private void removeBookByTitle() {
		System.out.println();
		System.out.println("############################");
		System.out.println("#   Remove book by Title   #");
		System.out.println("############################");
		System.out.println();
		System.out.println("Enter the Title of the book you want to remove:");

		String userChoice = getUserInput();
		dbc.removeBookFromDatabaseByTitle(userChoice);
	}

	/* SORTING *//* // TODO: sorting
	@SuppressWarnings("unused")
	void sortTitle() {
		getBooks();
		Collections.sort(books, (Book b1, Book b2) -> b1.getBookTitle().compareTo(b2.getBookTitle()));
		books.forEach(Book::showBookInfo);
	}
	@SuppressWarnings("unused")
	void sortAuthor() {
		getBooks();
		Collections.sort(books, (Book b1, Book b2) -> b1.getAuthor().compareTo(b2.getAuthor()));
		books.forEach(Book::showBookInfo);
	}
	@SuppressWarnings("unused")
	void sortPrice() {
		getBooks();
		Collections.sort(books, (Book b1, Book b2) -> b1.getBookPriceString().compareTo(b2.getBookPriceString()));
		books.forEach(Book::showBookInfo);
	}
	@SuppressWarnings("unused")
	void sortDate() {
		getBooks();
		Collections.sort(books, (Book b1, Book b2) -> b1.getBookDate().compareTo(b2.getBookDate()));
		books.forEach(Book::showBookInfo);
	}

	/* END */
}