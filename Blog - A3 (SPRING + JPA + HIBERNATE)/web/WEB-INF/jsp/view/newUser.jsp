<!DOCTYPE html>
<html>
    <head>
        <title>5Chan</title>
    </head>
    <br>
        <a href="<c:url value="/blogs" />">Home</a>
        <a href="<c:url value="/login" />">
            <%if(session.getAttribute("username")== null){
            %><b> Login </b>
            <% } else {%>
            <b> Logout </b>
            <% } %>
        </a>
        <h2>New User</h2>
        <%

            if(((Boolean)request.getAttribute("userExist")))
            {
                %><b>User Exists</b></br>  <%
            }

        %>

        <form method="POST" action="login">
            <input type="hidden" name="action" value ="newUser">
            New User<br/>
            <input type="text" name="newUser"><br/><br/>
            New Password<br/>
            <input type="password" name="userPassword"><br/><br/>
            <input type="submit" value="Submit" >
        </form><br><br>

    </body>
</html>