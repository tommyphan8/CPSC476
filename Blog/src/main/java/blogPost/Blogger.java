package blogPost;

/**
 * Created by Tommy on 10/25/2015.
 */
public class Blogger {
    private String username;

    private String password;

//    public Blogger(String username, String password)
//    {
//        this.username = username.toUpperCase();
//        this.password = password;
//    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setUsername(String username)
    {
        this.username = username.toUpperCase();
    }

    public String getUsername()
    {
        return this.username;
    }
}
