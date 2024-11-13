<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Secure Page</title>
</head>
<body>
<h1>Welcome, <span th:text="${user.name}"></span>!</h1>
<p>This is a secure page only accessible to logged-in users.</p>
<a href="/logout">Logout</a>
</body>
</html>
