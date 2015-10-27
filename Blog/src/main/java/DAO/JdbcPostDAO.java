package DAO;

import blogPost.BlogPost;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

/**
 * Created by Tommy on 10/26/2015.
 */
public class JdbcPostDAO extends JdbcDaoSupport implements PostDAO {

    @Override
    public void create(BlogPost post)
    {
        String query = "INSERT INTO post (username, subject, body, timeStamp)\n" +
                "values(?, ?, ?, ?)";

        getJdbcTemplate().update(query, new Object[]{post.getUserName(),post.getSubject(),post.getBody(),post.getTimeStamp()});

    }

    @Override
    public List<BlogPost> getAll()
    {
        String query = "Select * from post";

        List<BlogPost> blogPosts = getJdbcTemplate().query(query, new BeanPropertyRowMapper(BlogPost.class));

        return blogPosts;

    }

    @Override
    public BlogPost getBlogPost(int ID)
    {
        String query = "Select * from post where ID =?";

        try {
            BlogPost post = (BlogPost) getJdbcTemplate().queryForObject(query, new Object[]{ID}, new BeanPropertyRowMapper(BlogPost.class));
            return post;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public String getBlogID(BlogPost post)
    {
        String query = "Select ID from post where username=? and subject=? and body=? and timestamp=?";

        try{
            return (String)getJdbcTemplate().queryForObject(query, new Object[] {post.getUserName(),
                    post.getSubject(), post.getBody(), post.getTimeStamp()}, String.class);
        } catch(EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public List<BlogPost> getALLUser(String username)
    {
        String query = "SELECT * from post where username=?";

        return getJdbcTemplate().query(query, new Object[]{username}, new BeanPropertyRowMapper(BlogPost.class));
    }
}
