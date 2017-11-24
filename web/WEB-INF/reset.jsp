<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <p>Please enter your email address to reset your password.
        <p>
        <form action="reset?action=resetpass" method="POST">
            Email Address: <input type="text" name="toResetEmail">
            <input type="submit" value="Submit">
        </form>
        ${message}
        </p>
    </body>
</html>
