<%@ page import="java.util.List" %>
<%
    @SuppressWarnings("unchecked")
    List<BlogPost> blogDatabase =
            (List<BlogPost>)request.getAttribute("BlogDatabase");


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
                String temp = (String)session.getAttribute("username");
                %><a href="<c:url value="/login?logout" />">Logout</a>
                  <a href="<c:url value="/blogs">
                  <c:param name="action" value="profileHome" />
                  <c:param name="username" value="<%=temp%>" />
                  </c:url>">Profile Home</a>
        <%
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

                for(int i = blogDatabase.size()-1; i >= 0; i--)
                {
                    BlogPost post = blogDatabase.get(i);
                    %>meowPost #<%= post.getID() %>: <a href="<c:url value="/blogs">
                         <c:param name="action" value="view" />
                         <c:param name="blogID" value="<%= Integer.toString(post.getID()) %>" />
                         </c:url>"><%= post.getSubject() %></a> (Blogger:
                     <%= post.getUserName() %>): <%= post.getTimeStamp() %><br /><%

                }

            }
        %>
    </body>
</html>
