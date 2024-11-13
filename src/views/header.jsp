<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <title>E-Commerce Site</title>
</head>
<body>
<header class="bg-light py-3">
    <div class="container">
        <h1 class="text-center">Welcome to Our E-Commerce Site</h1>
        <nav class="navbar navbar-expand-lg navbar-light">
            <a class="navbar-brand" href="/products">Products</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav ml-auto">
                    <%--Thanh tìm kiếm--%>
                        <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/products/search" method="GET">
                            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="keyword">
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                        </form>
                        <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/products/new" class="nav-link">Add New Product</a>
                    </li>


                    <li class="nav-item">
                        <a class="nav-link" href="/cart">Cart</a>
                    </li>

                    <c:choose>
                        <c:when test="${empty sessionScope.loggedInUser}">
                            <li class="nav-item">
                                <a class="nav-link" href="/login">Login</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/register">Register</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item">
                                <a class="nav-link" href="/logout">Logout</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </nav>
    </div>
</header>
<!-- Additional content can go here -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
