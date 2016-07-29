package pl.bastus.bookapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class BookDAO {
	private List<Book> books = new ArrayList<>();
	private List<Song> songs = new ArrayList<>();
	private static Scanner sc = new Scanner(System.in);

	private String getUserInput() {
		return sc.nextLine().trim();
	}

	private List<Book> getBooks() {
		return books;
	}
	private static void giveTitle() {
		System.out.print("Book title: ");
	}
	private static void giveAuthor() {
		System.out.print("Book author: ");
	}
	private static void givePrice() {
		System.out.print("Book price: ");
	}
	private static void giveDate() {
		System.out.print("Date: ");
	}

	void menuDisplay() {
		String userChoice;
		do {
			System.out.println();
			System.out.println("What to do now?");
			System.out.println("[1] Add book");
			System.out.println("[2] Add song");
			System.out.println("[3] Show books ");
			System.out.println("[x] Exit");

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
			}
		}
		while (!userChoice.equalsIgnoreCase("x"));
	}

	private void addBook() {
		System.out.println();
		System.out.println("#########################");
		System.out.println("#        ADD BOOK       #");
		System.out.println("#########################");

		Book book = new Book();

        /* Title */
		giveTitle();
		book.setBookTitle(getUserInput());

		/*
		  Pattern pattern = Pattern.compile("regex"); Matcher matcher =
		  pattern.matcher("check this"); matcher.matches(); // true or false
		 */

		/* Data */
		Pattern datePattern;
		datePattern = Pattern.compile("[0-2][0-9]{3}\\.[0-1]?[0-9]\\.[0-3]?[0-9]");
		String loadedDate;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		do {
			Matcher datePattern_Matcher;
			giveDate();
			loadedDate = getUserInput();
			datePattern_Matcher = datePattern.matcher(loadedDate);

			if (datePattern_Matcher.matches()) {
				try {
					book.setBookDate(sdf.parse(loadedDate));
				}
				catch (ParseException | NullPointerException pe) {
					System.out.println("Something not right, date pattern should match: 2014.01.05");
				}
            }
		}
		while (book.getBookDate() == null);

		/* Price */
		Pattern pricePattern = Pattern.compile("[0-9]+(\\.[0-9]+)?");
		Integer bookPriceInt = null;
		do {
			Matcher pricePattern_Matcher;
			givePrice();
			String priceLoaded_Scanner = getUserInput();
			pricePattern_Matcher = pricePattern.matcher(priceLoaded_Scanner);

			// if (pricePattern.matcher(priceLoaded_Scanner).matches()) { //
			// shortcut
			if (pricePattern_Matcher.matches()) {
				book.setBookPrice(Float.valueOf(priceLoaded_Scanner));
				bookPriceInt = Math.round(book.getBookPrice());
			}
		}
		while (bookPriceInt == null);
		// while (Pattern.matches("[0-9]+(\\.[0-9]+)?", getbookPriceStr()));

		/* Author */
		giveAuthor();
		book.setAuthor(getUserInput());

		/* Summary */
		System.out.println("Thank you. Book: " + book.getBookTitle()
				+ ", author: " + book.getAuthor()
				+ ", date: " + book.getBookDate()
				+ ", price: " + book.getBookPrice());

		addBookToBooks(book);
	}

	private void addBookToBooks(Book b) {
		books.add(b);
		System.out.println("Book added!");
	}

	private void showBooks() {
		System.out.println();
		System.out.println("###########################");
		System.out.println("#        Book list        #");
		System.out.println("###########################");

		Book book;
		for (int i = 0; i < getBooks().size(); i++) {
			book = getBooks().get(i);
			System.out.println(i + ": " + book.getBookTitle());
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
					+ ", price " + chosenBook.getBookPrice()
					+ ", date " + chosenBook.getBookDate().toString()
					+ ", author " + chosenBook.getAuthor());
		}
		else {
			System.out.println("No such book, sorry!");
		}
	}

	void getWork() {
		getSongs();
		System.out.print(songs);
		Collections.sort(songs, (Song p1, Song p2) -> p1.getTitle().compareTo(p2.getTitle()));
		Collections.sort(songs, (Song p1, Song p2) -> p1.getArtist().compareTo(p2.getArtist()));
		Collections.sort(songs, (Song p1, Song p2) -> p1.getRate().compareTo(p2.getRate()));
		Collections.sort(songs, (Song p1, Song p2) -> p1.getBpm().compareTo(p2.getBpm()));
		songs.forEach(Song::printSong);
	}

	private void getSongs() {
		try {
			File file;
			BufferedReader reader;
			String line;

			file = new File("y:\\Programowanie\\Tutorials\\ListaPiosenek.txt");
			reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine()) != null) {
				addSong(line);
			}
			reader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void addSong(String l) {
		String[] elements = l.split("/");
		Song nextSong = new Song(elements[0], elements[1], elements[2], elements[3]);
		songs.add(nextSong);
	}
}