<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login/Register</title>
</head>
<body>
<h2>Login or Register</h2>

<form method="post" action="">
    <input type="text" name="login" placeholder="Login" required><br>
    <input type="password" name="password" placeholder="Password" required><br>
    <input type="email" name="email" placeholder="Email (for register)"><br>
    <button type="submit" name="action" value="login">Login</button>
    <button type="submit" name="action" value="register">Register</button>
</form>
</body>
</html>
