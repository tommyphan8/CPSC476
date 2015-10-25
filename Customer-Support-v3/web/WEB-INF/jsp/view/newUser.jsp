<!DOCTYPE html>
<html>
    <head>
        <title>Blog</title>
    </head>
    <body>
        <a href="<c:url value="/home" />">Home</a>
        <a href="<c:url value="/login?logout" />">
            <%if(session.getAttribute("username")== null){
            %><b> Login </b>
            <% } else {%>
            <b> Logout </b>
            <% } %>
        </a>
        <h2>New SUser</h2>

        <form method="POST" action="/login" enctype="multipart/form-data">
            <input type="hidden" name="action" value ="new"/>
            New User<br/>
            <input type="text" name="newUser"/><br/><br/>
            New Password<br/>
            <input type="password" name="userPassword"/><br/><br/>
            <input type="submit" value="Submit" />
        </form><br><br>

    </body>
</html>