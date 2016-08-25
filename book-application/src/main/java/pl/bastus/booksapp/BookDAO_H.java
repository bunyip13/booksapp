package pl.bastus.booksapp;
/*
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.List;*/
@SuppressWarnings("unused")
class BookDAO_H {
    /*static void configureHibernate() {
        Configuration config = new Configuration()
                .addPackage("pl.bastus.booksapp")
                .addAnnotatedClass(Book.class)
                .addResource("Book.hbm.xml")
                .configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties())
                .build();
        factory = config.buildSessionFactory(serviceRegistry);

        //bookDAO.insertBook(1, "Mark", "Johnson", "mark.johnson@gmail.com", "mjohnson");
        //bookDAO.insertBook(2, "Samuel", "Johnson", "sam.johnson@gmail.com", "sjohnson");
    }

    static void configureHibernateAnnotations() {
        AnnotationConfiguration aConfig = new AnnotationConfiguration()
                .addPackage("pl.bastus.booksapp")
                .addAnnotatedClass(Book.class)
                .configure();

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(aConfig.getProperties())
                .build();

        factory = aConfig.buildSessionFactory(serviceRegistry);
    }

    private int insertBook(Book book) {
        Transaction tx = null;
        int bookIdSaved = 0;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            bookIdSaved = (Integer) session.save(book);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null)
                tx.rollback();
            ex.printStackTrace();
        }
        return bookIdSaved;
    }

    @SuppressWarnings("unchecked")
    List<Book> listBooks() {
        List<Book> books = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            tx.setTimeout(5);
            books = session.createQuery("FROM booksapp").list();
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        for (Book b : books) {
            System.out.print(b.getBookId() + " ");
            System.out.print(b.getBookTitle() + " ");
            System.out.print(b.getBookAuthor() + " ");
            System.out.print(b.getBookDate() + " ");
            System.out.print(b.getBookAddedDate() + " ");
            System.out.print(b.getBookPrice() + " ");
            System.out.println();
        }
        return books;
    }

    private Book getBook(int id) {
        Book book = new Book();
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            book = (Book) session.createQuery("FROM booksapp WHERE id = " + id);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return book;
    }*/
}