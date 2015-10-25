<%@ page import="java.util.Map" %>
<%
    @SuppressWarnings("unchecked")
    Map<Integer, BlogPost> blogDatabase =
            (Map<Integer, BlogPost>)request.getAttribute("BlogDatabase");


%>
<!DOCTYPE html>
<html>
    <head>
        <title>5Chan</title>
    </head>
    <body>
        <%
            if(session.getAttribute("username") != null)
            {
                %><a href="<c:url value="/login?logout" />">Logout</a><%
            }
            else
            {
                %><a href="<c:url value="/blogs?action=newUser" />">Create User</a>
                <a href="<c:url value="login" />">Login</a><%
            }

            %>
        <%--<a href="<c:url value="/login?logout" />">Logout</a>--%>
        <h2>5Chan: All About Cats</h2>
        <a href="<c:url value="/blogs">
            <c:param name="action" value="create" />
        </c:url>">Post your meowPost!</a><br /><br />
        <%
            if(blogDatabase.size() == 0)
            {
                %><i>There are no cat posts!.</i><%
            }
            else
            {
                System.out.print(blogDatabase.keySet().size());
                for(int i = blogDatabase.keySet().size(); i >=1; i--)
                {
                    String idString = Integer.toString(i);
                    BlogPost post = blogDatabase.get(i);
                    %>meowPost #<%= idString %>: <a href="<c:url value="/blogs">
                        <c:param name="action" value="view" />
                        <c:param name="blogID" value="<%= idString %>" />
                    </c:url>"><%= post.getSubject() %></a> (Blogger:
        <%= post.getCustomerName() %>): <%= post.getTimeStamp() %><br /><%
                }
            }
        %>
    </body>
</html>
