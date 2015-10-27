package blogPost;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlogPost
{
    private String userName;

    private String subject;

    private String body;

    private String timeStamp;

    private int ID;


    public int getID(){ return this.ID;}

    public void setID(int ID){this.ID = ID;}


    public void setTimeStamp(String timeStamp) { this.timeStamp = timeStamp;}

    public String getTimeStamp() {return timeStamp;}

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String customerName)
    {
        this.userName = customerName;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }


}
