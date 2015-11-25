package blogPost;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tommy on 10/25/2015.
 */

@Entity
//@Table(uniqueConstraints = {
//        @UniqueConstraint(name = "username", columnNames = {"username"})
//})
public class Blogger implements Serializable {


    private int id;
//    @Column(name="USERNAME")
    private String username;
//    @Column(name="PASSWORD")
    private String password;

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Basic
    public String getPassword()
    {
        return this.password;
    }


    public void setUsername(String username)
    {
        this.username = username.toUpperCase();
    }

    @Basic
    public String getUsername()
    {
        return this.username;
    }

    @Id // @id indicates that this is a unique primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //@GeneratedValue indicates that value is automatically generated by the server
    @Column(name = "USERID", nullable = false)
    public int getId()
    {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
