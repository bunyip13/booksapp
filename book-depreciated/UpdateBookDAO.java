package pl.bastus.booksapp;

import java.time.LocalDate;

class UpdateBookDAO extends AddBookDAO implements Input {
    private DatabaseControllerDAO dc;

    @SuppressWarnings("unused")
    void updateBook() {
        printUpdate();

        String userChoice;
        do {
            printUpdateSelect();
            userChoice = getUserInput();
            switch (userChoice) {
                case "1":
                    updateBookByID();
                    break;
                case "2":
                    updateBookByTitle();
            }
        } while (!userChoice.equalsIgnoreCase("x"));
    }

    void updateBookByID() {
        printUpdateID();
        int id = getBookID();
        String title = addTitle();
        String author = addAuthor();
        String date = addDate();
        LocalDate addedDate = addedDate();
        Float price = addPrice();

        Book book = new Book(title, author, date, addedDate, price);
        dc = new DatabaseControllerDAO();
        dc.updateBookDatabaseSelectID(id, book);
        System.out.println("Thank you. Book updated.");
    }

    private void updateBookByTitle() {
        printUpdateTitle();
        String oldTitle = getBookTitle();
        String title = addTitle();
        String author = addAuthor();
        String date = addDate();
        LocalDate addedDate = addedDate();
        Float price = addPrice();

        Book book = new Book(title, author, date, addedDate, price);
        dc = new DatabaseControllerDAO();
        dc.updateBookDatabaseByTitle(oldTitle, book);
        System.out.println("Thank you. Book updated.");
    }

    private Integer getBookID() {
        System.out.println("Enter the ID of the book you want to update: ");
        String userInput = getUserInput();
        return Integer.valueOf(userInput);
    }

    private String getBookTitle() {
        System.out.println("Enter the Title of the book you want to update: ");
        return getUserInput();
    }

    private void printUpdateSelect() {
        System.out.println();
        System.out.println("Search book by id or title?");
        System.out.println("[1] ID");
        System.out.println("[2] Title");
        System.out.println("[x] Exit");
    }

    private void printUpdate() {
        System.out.println();
        System.out.println("###################");
        System.out.println("#   Update Book   #");
        System.out.println("###################");
        System.out.println();
    }

    private void printUpdateID() {
        System.out.println();
        System.out.println("#########################");
        System.out.println("#   Update book by ID   #");
        System.out.println("#########################");
        System.out.println();
    }

    private void printUpdateTitle() {
        System.out.println();
        System.out.println("############################");
        System.out.println("#   Update book by Title   #");
        System.out.println("############################");
        System.out.println();
    }
}
