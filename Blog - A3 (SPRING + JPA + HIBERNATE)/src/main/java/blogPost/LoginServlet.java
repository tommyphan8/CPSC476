package blogPost;

import Services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(
        name = "loginServlet",
        urlPatterns = "/login"
)
public class LoginServlet extends HttpServlet
{

    ApplicationContext context = new ClassPathXmlApplicationContext("servletContext.xml");
    UserService service = (UserService) context.getBean("userServiceImp");



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
            System.out.print("ID: " + temp.getId());
            service.insert(temp);

            request.getSession().setAttribute("username", temp.getUsername());
            request.changeSessionId();
            response.sendRedirect("blogs");

        }


    }

}
