package DAO;

import blogPost.Blogger;

import java.util.List;
/**
 * Created by Tommy on 10/25/2015.
 */
public interface UserDAO {

    //check if username exists
    public boolean userExists(String userName);

    //create user account
    public void insert(Blogger newUser);

    //check if userlogin is correct
    public boolean userLogin(String username, String password);



}
