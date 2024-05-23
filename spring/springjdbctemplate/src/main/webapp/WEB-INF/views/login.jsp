<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   <form action="logs" method="POST">
        <label for="username">Username:</label>
        <input type="text" id="username" name="userName"><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="passWord"><br><br> <!-- Changed name to passWord -->
        <input type="submit" value="Login">
   </form>
</body>
</html>