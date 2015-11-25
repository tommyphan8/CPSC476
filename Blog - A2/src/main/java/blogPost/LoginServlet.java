package blogPost;

import DAO.JdbcUserDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;



@WebServlet(
        name = "loginServlet",
        urlPatterns = "/login"
)
public class LoginServlet extends HttpServlet
{

    ApplicationContext context = new ClassPathXmlApplicationContext("servletContext.xml");
    JdbcUserDAO service = context.getBean("UserDao", JdbcUserDAO.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        HttpSession session = request.getSession();

        if(request.getParameter("logout") != null)
        {
            session.invalidate();
            response.sendRedirect("blogs");
            return;
        }
        else if(session.getAttribute("username") != null) {
            response.sendRedirect("blogs");
            return;
        }

        request.setAttribute("loginFailed", false);
        request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        if(session.getAttribute("username") != null)
        {
            response.sendRedirect("blogs");
            return;
        }

        if(request.getParameter("action") != null && request.getParameter("action").equals("newUser"))
        {
            this.newUser(request,response);
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username == null || password == null ||
                !service.userLogin(username, password))
        {
            request.setAttribute("loginFailed", true);
            request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp")
                   .forward(request, response);
        }
        else
        {
            session.setAttribute("username", username.toUpperCase());
            request.changeSessionId();
            response.sendRedirect("blogs");
        }


    }

    private void newUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String username = request.getParameter("newUser");
        String password = request.getParameter("userPassword");
        System.out.print(username + password);
        System.out.print(service.userExists("Tommy"));
        if(service.userExists(username))
        {

            request.setAttribute("userExist", true);
            request.getRequestDispatcher("/WEB-INF/jsp/view/newUser.jsp")
                    .forward(request,response);
        }
        else
        {
            Blogger temp = new Blogger();
            temp.setPassword(password);
            temp.setUsername(username);
            service.insert(temp);

            request.getSession().setAttribute("username", temp.getUsername());
            request.changeSessionId();
            response.sendRedirect("blogs");

        }


    }

}
