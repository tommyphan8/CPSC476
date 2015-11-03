<!DOCTYPE html>
<html>
    <head>
        <title>5Chan</title>
    </head>
    <body>
        <a href="<c:url value="/login?logout" />">Logout</a>
        <h2>Create a Post</h2>
        <form method="POST" action="blogs" enctype="multipart/form-data">
            <input type="hidden" name="action" value="create"/>
            Subject<br/>
            <input type="text" name="subject"><br/><br/>
            Body<br/>
            <textarea name="body" rows="5" cols="30"></textarea><br/><br/>
            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>
