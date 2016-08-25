package pl.bastus.booksapp;

import java.sql.SQLException;

class Interface {
	public static void main(String[] args) {
		Menu menu = new Menu();
        try {
            menu.menuDisplay();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}