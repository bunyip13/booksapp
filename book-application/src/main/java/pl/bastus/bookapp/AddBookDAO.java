package pl.bastus.bookapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class AddBookDAO implements Input {

    void passAddedBook() {
        printAdd();
        String title = addTitle();
        String author = addAuthor();
        String date = addDate();
        LocalDate addedDate = addedDate();
        Float price = addPrice();

        DatabaseAdd dba = new DatabaseAdd();
        dba.addToDatabase(title, author, date, addedDate, price);
        System.out.println("Thank you. Book added.");
    }

    @SuppressWarnings("unused")
    /*Book addBook() {
        String title = addTitle();
        String author = addAuthor();
        String date = addDate();
        Float price = addPrice();
        LocalDate dateAdded = addedDate();
    }*/ String addTitle() {
        System.out.print("Book title: ");
        return getUserInput();
    }

    String addAuthor() {
        System.out.print("Book author: ");
        return getUserInput();
    }

    String addDate() {
//		Pattern datePattern = Pattern.compile("[0-3]?[0-9]\\.[0-1]?[0-9]\\.[0-2][0-9]{3}");
        Pattern datePattern = Pattern.compile("[1-2][0-9]{3}");
        String loadedDate;
        Matcher datePattern_Matcher;
        do {
            System.out.print("Book date: ");
            loadedDate = getUserInput();
            datePattern_Matcher = datePattern.matcher(loadedDate);
        }
        //while (book.getBookDate() == null);
        while (!datePattern_Matcher.matches());
        return loadedDate;
    }

    LocalDate addedDate() {
        DateTimeFormatter day = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // SQL przechowuje daty z pauzami
        return LocalDate.parse(LocalDate.now().format(day), day);
    }

    Float addPrice() {
        Pattern pricePattern = Pattern.compile("[0-9]+(\\.[0-9]+)?");
        Matcher pricePattern_Matcher;
        String priceLoaded_Scanner;
        do {
            System.out.print("Book price: ");
            priceLoaded_Scanner = getUserInput();
            pricePattern_Matcher = pricePattern.matcher(priceLoaded_Scanner);
            /*if (pricePattern_Matcher.matches()) {
                book.setBookPrice(priceLoaded_Scanner);
                bookPriceInt = Math.round(book.getBookPrice());
            }*/
        }
        while (!pricePattern_Matcher.matches());
        return Float.valueOf(priceLoaded_Scanner);
        // while (Pattern.matches("[0-9]+(\\.[0-9]+)?", getbookPriceStr()));
    }

    private void printAdd() {
        System.out.println();
        System.out.println("################");
        System.out.println("#   Add Book   #");
        System.out.println("################");
        System.out.println();
    }
}
