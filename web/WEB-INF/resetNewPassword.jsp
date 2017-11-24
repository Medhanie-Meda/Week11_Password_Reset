<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Password</title>
    </head>
    <body>
        <h1>Enter New Password</h1>
         <form action="reset?action=getnewpassword" method="POST">
            <input type="text" name="newPassord">
            <input type="hidden" name="uuid" value="${uuid}">
            <input type="submit" value="Submit">
        </form>
        ${message}
    </body>
</html>
