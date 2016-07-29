package pl.bastus.bookapp;

import java.io.File;

class Interface {
	public static void main(String[] args) {
		BookDAO bookdao = new BookDAO(new File("y:\\Programowanie\\GitHub\\Tutorials\\booksapp\\Lista.txt"));
		bookdao.menuDisplay();
	}
}