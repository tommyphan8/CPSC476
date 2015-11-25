package Services;

import DAO.UserDAO;
import blogPost.Blogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Tommy on 11/24/2015.
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDAO ud;

    @Override
    public boolean userExists(String userName)
    {
        return ud.userExists(userName);

    }

    //create user account
    @Override
    @Transactional
    public void insert(Blogger newUser)
    {
        ud.insert(newUser);
    }
    //check if userlogin is correct
    @Override
    public boolean userLogin(String username, String password)
    {
        return ud.userLogin(username, password);
    }

}
