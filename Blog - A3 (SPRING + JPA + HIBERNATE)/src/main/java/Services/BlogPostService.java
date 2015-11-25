package Services;

import blogPost.BlogPost;

import java.util.List;

/**
 * Created by Tommy on 11/24/2015.
 */
public interface BlogPostService {

    public void create(BlogPost post);

    public List<BlogPost> getAll();

    public BlogPost getBlogPost(int ID);

    public List<BlogPost> getALLUser(String username);
}
