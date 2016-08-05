package pl.bastus.bookapp;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;

public class BookDAO {
	private List<Book> books = new LinkedList<>();
	private Book book;
	private DatabaseController dbc = new DatabaseController();
	private DateTimeFormatter day = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	/* Scanner */
	private static Scanner sc = new Scanner(System.in);
	@NotNull
	private String getUserInput() {
		return sc.nextLine().trim();
	}

	/* Getters */
	public List<Book> getBooks() {
		return books;
	}

	/* MENU */
	void menuDisplay() throws SQLException {
		String userChoice;
		do {
			System.out.println();
			System.out.println("What to do now?");
			System.out.println("[1] Add book"); // TODO: add to array
			//System.out.println("[2] Delete book"); // TODO: delete
			System.out.println("[3] Show books");
			//System.out.println("[4] Connect database");
			//System.out.println("[5] Add book to database");
			//System.out.println("[6] Load books from file "); // TODO: implement load from file to array
			//System.out.println("[7] Save books to file "); // TODO: implement save from array to file
			//System.out.println("[8] Erase file "); // TODO: erase
			System.out.println("[x] Exit"); // TODO: or use database... study learn read

			userChoice = getUserInput();
			switch (userChoice) {
				case "1":
					addBook();
					break;
				/*case "2":
					songdao.getSongs();
					break;*/
				case "3":
					showBooks();
					break;
				/*case "4":
					connectDatabase();
					break;
				case "5":
					addData(book.getBookTitle(),book.getAuthor(),book.getBookDate(),book.getBookAddedDate(),book.getBookPrice());
					break;*/
				case "8":
					break;
			}
		} while (!userChoice.equalsIgnoreCase("x"));
	}

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
				+ ", added date: " + book.getBookAddedDate());
		dbc.connectDatabase();
		dbc.addBookToDatabase(book);
		dbc.disconnectDatabase();
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

	private void showBooks() { // TODO: get book from database
		System.out.println();
		System.out.println("#################");
		System.out.println("#   Book list   #");
		System.out.println("#################");
		System.out.println();

		dbc.connectDatabase();
		dbc.getBooksFromDatabase();
		dbc.disconnectDatabase();

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

	/* FILE OPERATIONS *//*
	@SuppressWarnings("unused")
	private void writeFile(File f, String w) {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)))) {
			bw.write(w);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.err.format("IOException: %s%n", ioe);
		}
	}

	@SuppressWarnings("unused")
	private void writeFile2(File f, String w) {
		try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f, true)))) {
			pw.println(w);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.err.format("IOException: %s%n", ioe);
		}
	}

	@SuppressWarnings("unused")
	private void writeArrayToFile() { // TODO: write array to file
		writeFile2(file, book.getBookTitle() + "/"
				+ book.getAuthor() + "/"
				+ book.getBookDate() + "/"
				+ (book.getBookAddedDateString()) + "/n");
	}

	@SuppressWarnings("unused")
	private void getBooksFromFile(File f) {
		BufferedReader br;
		String line;
		try {
			br = new BufferedReader(new FileReader(f));
			while ((line = br.readLine()) != null) {
				addBookFromFile(line);
			}
			br.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.err.format("IOException: %s%n", ioe);
		}
	}

	private void addBookFromFile(String l) {
		String[] elements = l.split("/");
		Book nextBook = new Book(elements[0], elements[1], elements[2], elements[3], elements[4]);
		books.add(nextBook);
	}

	public Book getBookByID(Integer id) {
		if (id<books.size()) {
			return books.get(id);
		} else {
			return null;
		}
	}

	/*
	public void save(String fileName) throws FileNotFoundException {
		BufferedWriter writer = null;
		try {

			writer = new BufferedWriter(new FileWriter(fileName));
			for ( int i = 0; i < nbrMovies; i++)
			{
				writer.write(movies[i].getName());
				writer.newLine();
				writer.flush();
			}

		} catch(IOException ex) {
			ex.printStackTrace();
		} finally{
			if(writer!=null){
				writer.close();
			}
		}
	}*/

	/* SORTING */ // TODO: sorting
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