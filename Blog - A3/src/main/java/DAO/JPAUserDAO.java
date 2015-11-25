package DAO;

import blogPost.Blogger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Tommy on 11/24/2015.
 */
@Repository
public class JPAUserDAO implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean userExists(String userName)
    {

        Query q = em.createQuery("SELECT x FROM Blogger x where x.username = :name").setParameter("name", userName);
        List<Blogger> results = (List<Blogger>) q.getResultList();

        return !results.isEmpty();

    }

    @Override
    //create user account
    public void insert(Blogger newUser)
    {
        em.persist(newUser);
    }

    @Override
    //check if userlogin is correct
    public boolean userLogin(String username, String password)
    {

        Query q = em.createQuery("SELECT x FROM Blogger x where x.username = :name and x.password = :password").setParameter("name", username)
                .setParameter("password", password);
        List<Blogger> results = (List<Blogger>) q.getResultList();

        return !results.isEmpty();
    }


}
