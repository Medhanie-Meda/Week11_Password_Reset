<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="<c:url value='styles/notes.css' />" />        
    </head>
    <body>
        <h1>NotesKeepr Login</h1>
        <form action="login" method="post">
            username: <input type="text" name="username"><br>
            password: <input type="password" name="password"><br>
            <input type="submit" value="Login">
        </form>
        <p> <a href="reset">Reset password</a> </p>        
        ${errormessage}
    </body>
</html>
