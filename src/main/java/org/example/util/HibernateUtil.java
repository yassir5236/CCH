package org.example.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    // Instance unique de SessionFactory
    private static final SessionFactory sessionFactory = buildSessionFactory();

    // Constructeur privé pour empêcher l'instanciation externe
    private HibernateUtil() {
        // Prevent instantiation
    }

    // Méthode pour construire la SessionFactory
    private static SessionFactory buildSessionFactory() {
        try {
            // Crée la SessionFactory à partir de la configuration Hibernate
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Gérer les exceptions de création de la SessionFactory
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Méthode pour obtenir l'instance de SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Méthode pour obtenir la session actuelle
    public static Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    // Méthode pour fermer la SessionFactory
    public static void shutdown() {
        // Fermer le cache et les connexions de la SessionFactory
        getSessionFactory().close();
    }
}
