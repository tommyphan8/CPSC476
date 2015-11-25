package DAO;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import blogPost.Blogger;
/**
 * Created by Tommy on 10/25/2015.
 */
public class JdbcUserDAO extends JdbcDaoSupport implements UserDAO {

    @Override
    public boolean userExists(String username)
    {
        String query = "select username from user where username=?";
        try{
            String user = null;
            Object[] input = new Object[] {username.toUpperCase()};
            user = (String) getJdbcTemplate().queryForObject(query, input, String.class);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Override
    public void insert(Blogger newUser)
    {
        String query = "INSERT INTO user (username, password)\n" +
                "values(?,?)";

        String username = newUser.getUsername();
        String password = newUser.getPassword();

        getJdbcTemplate().update(query, new Object[]{username, password});


    }

    @Override
    public boolean userLogin(String username, String password)
    {
        String query = "select username from user where username =? and password =?";

        try{
            Blogger user = null;
            Object[] inputs = new Object[] {username.toUpperCase(), password};
            user = (Blogger) getJdbcTemplate().queryForObject(query, inputs, new BeanPropertyRowMapper(Blogger.class));
            return true;

        } catch (EmptyResultDataAccessException e) {
            return false;
        }


    }

}
