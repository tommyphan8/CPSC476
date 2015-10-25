package com.wrox;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by natallysantillan on 10/3/15.
 */
@WebServlet(name = "HomepageServlet",
            urlPatterns = "/home")
public class HomepageServlet extends HttpServlet {
    BlogPostServlet BlogPostServlet = new BlogPostServlet();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if(action == null) {

            //Retrieve action blogID
            String idString = request.getParameter("blogID");
            //Retrieve post
            BlogPost post = this.getBlogPost(idString, response);
            if (post == null)
                return;
        }
    }

    private BlogPost getBlogPost(String idString, HttpServletResponse response)
            throws ServletException, IOException
    {
        if(idString == null || idString.length() == 0)
        {
            response.sendRedirect("blogs");
            return null;
        }

        try
        {
            BlogPost BlogPost = BlogPostServlet.blogDatabase.get(Integer.parseInt(idString));
            if(BlogPost == null)
            {
                response.sendRedirect("blogs");
                return null;
            }
            return BlogPost;
        }
        catch(Exception e)
        {
            response.sendRedirect("blogs");
            return null;
        }
    }

}
