package pl.bastus.bookapp;

import java.sql.SQLException;

class Interface {
	public static void main(String[] args) {
		BookDAO bookdao = new BookDAO();
        try {
            bookdao.menuDisplay();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}