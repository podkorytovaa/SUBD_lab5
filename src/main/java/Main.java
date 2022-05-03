import org.hibernate.*;

public class Main {
    public static void main(String[] args) {
        HibernateSessionFactory.buildSessionFactory();
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    }
}
