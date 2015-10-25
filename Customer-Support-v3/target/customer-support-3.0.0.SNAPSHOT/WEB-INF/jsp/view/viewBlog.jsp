<%@ page import="com.wrox.BlogPost" %>
<%
    String blogID = (String)request.getAttribute("blogID");
    BlogPost post = (BlogPost)request.getAttribute("BlogPost");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>5Chan</title>
    </head>
    <body>
        <a href="<c:url value="/login?logout" />">Logout</a>
        <h2>meowPost #<%= blogID %>: <%= post.getSubject() %></h2>
        <i>Blogger Name - <%= post.getCustomerName() %></i>
        :<%= post.getTimeStamp()%><br /><br />
        <%= post.getBody() %><br /><br />
        <%
            if(post.getNumberOfAttachments() > 0)
            {
                %>Attachments: <%
                int i = 0;
                for(Attachment a : post.getAttachments())
                {
                    if(i++ > 0)
                        out.print(", ");
                    %><a href="<c:url value="/blogs">
                        <c:param name="action" value="download" />
                        <c:param name="postID" value="<%= blogID %>" />
                        <c:param name="attachment" value="<%= a.getName() %>" />
                    </c:url>"><%= a.getName() %></a><%
                }
                %><br /><br /><%
            }
        %>
        <a href="<c:url value="/blogs" />">Return to list of meowPics</a>
    </body>
</html>
