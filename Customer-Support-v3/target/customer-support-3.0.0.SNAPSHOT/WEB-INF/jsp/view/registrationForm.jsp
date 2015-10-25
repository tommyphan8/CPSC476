<%--
  Created by IntelliJ IDEA.
  User: Tommy
  Date: 10/6/2015
  Time: 11:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <title>5Chan</title>
  </head>
  <body>
    <h2>User Registration</h2>

    <form method="POST" action="<c:url value="/login" />">
      Username<br />
      <input type="hidden" name="action" value="new"/>
      <input type="text" name="username" /><br /><br />
      Password<br />
      <input type="password" name="password" /><br /><br />
      <input type="submit" value="Create" />
    </form>

  </body>
</html>
