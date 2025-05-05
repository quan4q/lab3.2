package accounts;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class UsersDB {
    private final SessionFactory factory;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public UsersDB(){
        this.factory = new Configuration().configure().buildSessionFactory();
    }

    public long setNewUser(String login, String password, String email){
        try(Session session = factory.openSession()){
            Transaction transaction = session.beginTransaction();
            UsersDAO usersDAO = new UsersDAO(session);
            UserProfile user = new UserProfile(login, password, email);
            usersDAO.insertUser(user);
            transaction.commit();

            return user.getId();
        }
    }

    public UserProfile getUser(String login){
        try (Session session = factory.openSession()){
            UsersDAO usersDAO = new UsersDAO(session);
            UserProfile user = usersDAO.getUserByLogin(login);

            if(user == null){
                session.close();
                return null;
            }

            return user;
        }
    }

    public UserProfile getUser(long id){
        try (Session session = factory.openSession()){
            UsersDAO usersDAO = new UsersDAO(session);
            return usersDAO.getUserByID(id);
        }
    }
}
