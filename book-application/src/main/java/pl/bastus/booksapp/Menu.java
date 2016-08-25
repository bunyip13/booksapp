package pl.bastus.booksapp;

import java.sql.SQLException;
import org.hibernate.Session;

class Menu implements Input {

    void menuDisplay() throws SQLException {
        String userChoice;
        do {
            System.out.println();
            System.out.println("What to do now?");
            System.out.println("[1] Add book");
            System.out.println("[2] Update book");
            System.out.println("[3] Delete book");
            System.out.println("[4] Show books");

            System.out.println("[5] Add book with Hibernate");
            System.out.println("[6] Update book with Hibernate");
            System.out.println("[7] Delete book with Hibernate");
            System.out.println("[8] Show books with Hibernate");
            System.out.println("[x] Exit");

            userChoice = getUserInput();
            switch (userChoice) {
                /*case "1":
                    AddBookDAO ab = new AddBookDAO();
                    ab.passAddedBook();
                    break;
                case "2":
                    UpdateBookDAO ub = new UpdateBookDAO();
                    ub.updateBookByID();
                    break;
                case "3":
                    RemoveBookDAO rb = new RemoveBookDAO();
                    rb.removeBook();
                    break;
                case "4":
                    BookDAO bookDAO = new BookDAO();
                    bookDAO.showBooks();
                    break;*/
                /*case "5":
                    BookDAO_H.configureHibernateAnnotations();
                    new BookDAO_H().listBooks();
                    break;
                case "6":
                    BookDAO_H.configureHibernateAnnotations();
                    new BookDAO_H().listBooks();
                    break;
                case "7":
                    BookDAO_H.configureHibernateAnnotations();
                    new BookDAO_H().listBooks();
                    break;
                case "8":
                    BookDAO_H.configureHibernateAnnotations();
                    new BookDAO_H().listBooks();
                    break;*/

                case "1":
                    Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
                    session.beginTransaction();
                    session.save(new AddBookDAO().passAddedBook());
                    session.getTransaction().commit();
                    HibernateSessionFactory.getSessionFactory().close();
            }
        } while (!userChoice.equalsIgnoreCase("x"));
    }
}


/*

    Employee1 emp = new Employee1();
		emp.setName("David");
                emp.setRole("Developer");
                emp.setInsertTime(new Date());

                //Get Session
                SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
                Session session = sessionFactory.getCurrentSession();
                //start transaction
                session.beginTransaction();
                //Save the Model object
                session.save(emp);
                //Commit transaction
                session.getTransaction().commit();
                System.out.println("Employee ID="+emp.getId());

                //terminate session factory, otherwise program won't end
                sessionFactory.close();
*/