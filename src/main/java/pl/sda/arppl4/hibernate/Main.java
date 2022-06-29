package pl.sda.arppl4.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // tworzymy narzędzie do konfiguracji hibernate
        HibernateUtil util = new HibernateUtil();

        // załaduj konfigurację hibernate.cfg.xml
        util.loadConfiguration();

        Student student = new Student (null, "Paweł", "Gaweł", "123", LocalDate.now());

        SessionFactory fabrykaPoloczen = util.getSessionFactory();

        Session session = null;
        try {
            session = fabrykaPoloczen.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            session.close();
        } catch (SessionException sessionException) {

        }
    }
}
