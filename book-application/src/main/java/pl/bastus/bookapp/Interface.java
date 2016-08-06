package pl.bastus.bookapp;

import java.io.File;
import java.io.IOException;
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