package pl.bastus.booksapp;

class RemoveBookDAO implements Input {
    private DatabaseControllerDAO dc;

    void removeBook() {
        printRemoveBook();
        String userChoice;
        do {
            printRemoveUserchoice();

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
        printRemoveID();

        String userChoice = getUserInput();
        dc = new DatabaseControllerDAO();
        dc.removeBookFromDatabaseByID(userChoice);
    }

    private void removeBookByTitle() {
        printRemoveTitle();

        String userChoice = getUserInput();
        dc = new DatabaseControllerDAO();
        dc.removeBookFromDatabaseByTitle(userChoice);
    }

    /* Printing system.out */
    private void printRemoveUserchoice() {
        System.out.println();
        System.out.println("Search book by id or title?");
        System.out.println("[1] ID");
        System.out.println("[2] Title");
        System.out.println("[x] Exit");
    }

    private void printRemoveBook() {
        System.out.println();
        System.out.println("###################");
        System.out.println("#   Remove book   #");
        System.out.println("###################");
        System.out.println();
    }

    private void printRemoveID() {
        System.out.println();
        System.out.println("#########################");
        System.out.println("#   Remove book by ID   #");
        System.out.println("#########################");
        System.out.println();
        System.out.println("Enter the ID of the book you want to remove:");
    }

    private void printRemoveTitle() {
        System.out.println();
        System.out.println("############################");
        System.out.println("#   Remove book by Title   #");
        System.out.println("############################");
        System.out.println();
        System.out.println("Enter the Title of the book you want to remove:");
    }

}
