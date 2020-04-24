<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>OAuth 2.0 Login</title>
</head>
<body>
    <h1>index</h1>
    <a href="<c:url value="/login/auth/google" />">Login with Google</a><br>
    <a href="<c:url value="/login/auth/github" />">Login with Github</a><br>
    <a href="<c:url value="/login/auth/facebook" />">Login with Facebook</a><br>
    <a href="/logout">logout</a>
</body>
</html>

