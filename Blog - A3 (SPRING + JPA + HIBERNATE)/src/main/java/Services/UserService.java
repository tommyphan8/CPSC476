package Services;

import blogPost.Blogger;

/**
 * Created by Tommy on 11/24/2015.
 */
public interface UserService {

    public boolean userExists(String userName);

    //create user account
    public void insert(Blogger newUser);

    //check if userlogin is correct
    public boolean userLogin(String username, String password);

}
