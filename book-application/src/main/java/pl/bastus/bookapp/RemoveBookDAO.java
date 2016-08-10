package pl.bastus.bookapp;

class RemoveBookDAO implements Input {
    private DatabaseRemove dbr;

    /* REMOVING */
    void removeBook() {
        System.out.println();
        System.out.println("###################");
        System.out.println("#   Remove book   #");
        System.out.println("###################");
        System.out.println();

        String userChoice;
        do {
            System.out.println();
            System.out.println("Search book by id or title?");
            System.out.println("[1] ID");
            System.out.println("[2] Title");
            System.out.println("[x] Exit");

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
        System.out.println();
        System.out.println("#########################");
        System.out.println("#   Remove book by ID   #");
        System.out.println("#########################");
        System.out.println();
        System.out.println("Enter the ID of the book you want to remove:");

        String userChoice = getUserInput();
        dbr = new DatabaseRemove();
        dbr.removeBookFromDatabaseByID(userChoice);
    }

    private void removeBookByTitle() {
        System.out.println();
        System.out.println("############################");
        System.out.println("#   Remove book by Title   #");
        System.out.println("############################");
        System.out.println();
        System.out.println("Enter the Title of the book you want to remove:");

        String userChoice = getUserInput();
        dbr = new DatabaseRemove();
        dbr.removeBookFromDatabaseByTitle(userChoice);
    }

}
