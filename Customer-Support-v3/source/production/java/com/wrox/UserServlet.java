package com.wrox;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by natallysantillan on 10/3/15.
 */
@WebServlet(name = "UserServlet",
            urlPatterns = "/newUser")
public class UserServlet extends HttpServlet {

    LoginServlet loginServlet = new LoginServlet();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/view/newUser.jsp")
                .forward(request, response);
        if(request.getParameter("action") != null)
        {
            LoginServlet.userDatabase.put(request.getParameter("newUser"), request.getParameter("userPassword"));
        }


    }
}
