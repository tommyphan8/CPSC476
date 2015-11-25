package DAO;

import blogPost.BlogPost;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Tommy on 11/24/2015.
 */

@Repository
public class JPAPostDAO implements PostDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(BlogPost post)
    {
        em.persist(post);
    }

    @Override
    public List<BlogPost> getAll()
    {
        Query q = em.createQuery("SELECT x FROM BlogPost x");
        List<BlogPost> results = (List<BlogPost>) q.getResultList();
        return results;
    }

    @Override
    public BlogPost getBlogPost(int ID)
    {

        Query q = em.createQuery("SELECT x FROM BlogPost x where x.ID = :id").setParameter("id", ((long)ID));
        BlogPost result = (BlogPost) q.getSingleResult();
        System.out.print("Here" + result.getTimeStamp());
        return result;

    }


    @Override
    public List<BlogPost> getALLUser(String username)
    {
        Query q = em.createQuery("SELECT x from BlogPost x where x.userName = :uname").setParameter("uname", username);
        List<BlogPost> results = (List<BlogPost>) q.getResultList();
        return results;
    }
}
