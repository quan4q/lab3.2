package accounts;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

public class UsersDAO {
    private final Session session;

    public UsersDAO(Session session){
        this.session = session;
    }

    public UserProfile getUserByLogin(String login){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserProfile> query = builder.createQuery(UserProfile.class);
        Root<UserProfile> root = query.from(UserProfile.class);
        query.select(root).where(builder.equal(root.get("login"), login));

        return session.createQuery(query).uniqueResult();
    }

    public void insertUser(UserProfile user){
        session.persist(user);
    }

    public UserProfile getUserByID(long id){
        return session.get(UserProfile.class, id);
    }
}
