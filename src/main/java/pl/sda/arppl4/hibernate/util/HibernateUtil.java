package pl.sda.arppl4.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


// (Antywzorzec)
//
// Wzorzec projektowy Singleton
// (fun fact - jest to również nazwa whisky)
//
// Rozwiązuje problem:
//      - jak stworzyć obiekt aby istniał tylko jeden na cały projekt?
//      - jak sprawić by ten obiekt ładował się TYLKO RAZ?
//      - jak sprawić by był ogólnodostępny?
//
// "To zależy"
// Wady:
//  - jeśli nadużywamy singletonów, to łamią zasadę obiektowości (łamie oop)
//  - jeśli nadużywamy, to zbyt wiele obiektów zyskuje nadmierną "moc statyczną" (godlike object) (łamie SOLID)
//  - nie wszystko powinno być ogólnodostępne (public static) (łamie paradygmaty programowania obiektowego)
//
// Zalety:
//  - jest sensownym rozwiązaniem na rozwiązanie trudnego problemu
//

public class HibernateUtil {
    public static final HibernateUtil INSTANCE = new HibernateUtil();

    public SessionFactory sessionFactory;

    // To jest taka łądowarka naszej konfiguracji

    private HibernateUtil() {
        loadConfiguration();
    }

    private void loadConfiguration() {
        // Załadowanie "Registry" jako kolekcji parametrów konfiguracyjnych do rejestru.
        // Stworzenie obiektu zawierającego zestaw ustawień.
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        // Stworzenie obiektu metadata - dane opisujące połączenie z bazą danych.
        Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
        // Wykorzystujemy metadane do skonfigurowania/parametryzacji fabryki. Tworzymy fabrykę.
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}