package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        try {
            Configuration config = new Configuration().configure();
            // Create the SessionFactory from hibernate.cfg.xml

            String jdbcDbUrl = System.getenv("JDBC_DATABASE_URL");
            if (null != jdbcDbUrl) {
                config.setProperty("hibernate.connection.url", System.getenv("JDBC_DATABASE_URL"));
                config.setProperty("hibernate.connection.username", "JDBC_DATABASE_USERNAME");
                config.setProperty("hibernate.connection.password", "JDBC_DATABASE_PASSWORD");
            }

            return config.buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
