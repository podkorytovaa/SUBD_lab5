import org.hibernate.SessionFactory;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;

public class HibernateSessionFactory {
    private static SessionFactory sessionFactory;

    protected static void buildSessionFactory() {
        try {
            StandardServiceRegistry std = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(std).getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        }
        catch (Exception exception){
            System.out.println("Ошибка при попытке соединения.");
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
