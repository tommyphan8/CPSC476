package DAO;
import blogPost.BlogPost;

import java.util.List;

/**
 * Created by Tommy on 10/26/2015.
 */
public interface PostDAO {

    public void create(BlogPost post);

    public List<BlogPost> getAll();

    public BlogPost getBlogPost(int ID);

    public String getBlogID(BlogPost post);

    public List<BlogPost> getALLUser(String username);


}
