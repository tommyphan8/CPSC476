package Services;

import DAO.PostDAO;
import blogPost.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Tommy on 11/24/2015.
 */
@Service
public class BlogPostServiceImp implements BlogPostService {

    @Autowired
    private PostDAO pd;

    @Override
    @Transactional
    public void create(BlogPost post)
    {
        pd.create(post);
    }

    @Override
    public List<BlogPost> getAll()
    {
        return pd.getAll();
    }

    @Override
    public BlogPost getBlogPost(int ID)
    {
        return pd.getBlogPost(ID);
    }

//    public String getBlogID(BlogPost post);

    @Override
    public List<BlogPost> getALLUser(String username)
    {
        return pd.getALLUser(username);
    }
}
