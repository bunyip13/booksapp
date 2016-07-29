package pl.bastus.bookapp;

import java.util.Date;
import java.util.Scanner;

class Book {
	private String bookTitle;
	private Date bookDate;
	private float bookPrice;
	private String author;
	private String titleLoaded;
	
	private static Scanner sc = new Scanner(System.in);

	public Book(String bookTitle, Date bookDate, float bookPrice, String author) {
		super();
		this.bookTitle = bookTitle;
		this.bookDate = bookDate;
		this.bookPrice = bookPrice;
		this.author = author;
	}

	Book() {
	}

	public String showBookInfo() {
		return bookTitle + ", date: " + bookDate + ", price: " + bookPrice + ", author: " + author;
	}

    String getBookTitle() {
		return bookTitle;
	}

	void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	Date getBookDate() {
		return bookDate;
	}

	void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	float getBookPrice() {
		return bookPrice;
	}

	void setBookPrice(float bookPrice) {
		this.bookPrice = bookPrice;
	}

	String getAuthor() {
		return author;
	}

	void setAuthor(String author) {
		this.author = author;
	}

	String gettitleLoaded() {
		return titleLoaded;
	}

	void settitleLoaded(String titleLoaded) {
		this.titleLoaded = titleLoaded;
	}

	static String getUserInput() {
		return sc.nextLine().trim();
	}
}