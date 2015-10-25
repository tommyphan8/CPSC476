package com.wrox;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(
        name = "BlogPostServlet",
        urlPatterns = {"/blogs"},
        loadOnStartup = 1
)
@MultipartConfig(
        fileSizeThreshold = 5_242_880, //5MB
        maxFileSize = 20_971_520L, //20MB
        maxRequestSize = 41_943_040L //40MB
)
public class BlogPostServlet extends HttpServlet
{
    private volatile int BLOG_ID_SEQUENCE = 1;

    protected Map<Integer, BlogPost> blogDatabase = new LinkedHashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String action = request.getParameter("action");
        if(action == null)
            action = "list";
        switch(action)
        {
            case "create":
                if(request.getSession().getAttribute("username") == null)
                    {
                       response.sendRedirect("login");
                        return;
                    }
                this.showBlogForm(request, response);
                break;
            case "view":
                this.viewBlogs(request, response);
                break;
            case "download":
                this.downloadAttachment(request, response);
                break;
            case "list":
            default:
                this.listBlogs(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        if(request.getSession().getAttribute("username") == null)
        {
            response.sendRedirect("login");
            return;
        }

        String action = request.getParameter("action");
        if(action == null)
            action = "list";
        switch(action)
        {
            case "create":
                this.createBlog(request, response);
                break;
            case "list":
            default:
                response.sendRedirect("blogs");
                break;
        }
    }

    private void showBlogForm(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException
    {
        request.getRequestDispatcher("/WEB-INF/jsp/view/blogForm.jsp")
               .forward(request, response);
    }

    private void viewBlogs(HttpServletRequest request,
                            HttpServletResponse response)
            throws ServletException, IOException
    {
        String idString = request.getParameter("blogID");
        BlogPost post = this.getBlogPost(idString, response);
        if(post == null)
            return;

        request.setAttribute("blogID", idString);
        request.setAttribute("BlogPost", post);

        request.getRequestDispatcher("/WEB-INF/jsp/view/viewBlog.jsp")
               .forward(request, response);
    }

    private void downloadAttachment(HttpServletRequest request,
                                    HttpServletResponse response)
            throws ServletException, IOException
    {
        String idString = request.getParameter("ticketId");
        BlogPost post = this.getBlogPost(idString, response);
        if(post == null)
            return;

        String name = request.getParameter("attachment");
        if(name == null)
        {
            response.sendRedirect("tickets?action=view&ticketId=" + idString);
            return;
        }

        Attachment attachment = post.getAttachment(name);
        if(attachment == null)
        {
            response.sendRedirect("tickets?action=view&ticketId=" + idString);
            return;
        }

        response.setHeader("Content-Disposition",
                "attachment; filename=" + attachment.getName());
        response.setContentType("application/octet-stream");

        ServletOutputStream stream = response.getOutputStream();
        stream.write(attachment.getContents());
    }

    private void listBlogs(HttpServletRequest request,
                             HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setAttribute("BlogDatabase", this.blogDatabase);

        request.getRequestDispatcher("/WEB-INF/jsp/view/listBlogs.jsp")
                .forward(request, response);
    }

    private void createBlog(HttpServletRequest request,
                              HttpServletResponse response)
            throws ServletException, IOException
    {
        BlogPost post = new BlogPost();
        post.setCustomerName(
                (String) request.getSession().getAttribute("username")
        );
        post.setSubject(request.getParameter("subject"));
        post.setBody(request.getParameter("body"));

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String formattedDate = sdf.format(date);
        post.setTimeStamp(formattedDate);

        Part filePart = request.getPart("file1");
        if(filePart != null && filePart.getSize() > 0)
        {
            Attachment attachment = this.processAttachment(filePart);
            if(attachment != null)
                post.addAttachment(attachment);
        }

        int id;
        synchronized(this)
        {
            id = this.BLOG_ID_SEQUENCE++;
            this.blogDatabase.put(id, post);
        }

        response.sendRedirect("blogs?action=view&blogID=" + id);
    }

    private Attachment processAttachment(Part filePart)
            throws IOException
    {
        InputStream inputStream = filePart.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        int read;
        final byte[] bytes = new byte[1024];

        while((read = inputStream.read(bytes)) != -1)
        {
            outputStream.write(bytes, 0, read);
        }

        Attachment attachment = new Attachment();
        attachment.setName(filePart.getSubmittedFileName());
        attachment.setContents(outputStream.toByteArray());

        return attachment;
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
            BlogPost blogPost = this.blogDatabase.get(Integer.parseInt(idString));
            if(blogPost == null)
            {
                response.sendRedirect("blogs");
                return null;
            }
            return blogPost;
        }
        catch(Exception e)
        {
            response.sendRedirect("blogs");
            return null;
        }
    }
}