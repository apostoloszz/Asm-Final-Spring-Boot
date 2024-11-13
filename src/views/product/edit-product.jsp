<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Edit Product</h1>
    <!-- Form for Editing an Existing Product -->
    <c:if test="${not empty product.id}">
        <form method="post" action="${pageContext.request.contextPath}/products/${product.id}">
            <div class="form-group">
                <label for="name">Product Name:</label>
                <input type="text" name="name" id="name" class="form-control" required="true" value="${product.name}"/>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea name="description" id="description" class="form-control" rows="3" required="true">${product.description}</textarea>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" name="price" id="price" class="form-control" required="true" step="0.01" value="${product.price}"/>
            </div>
            <div class="form-group">
                <label for="image_url">Image URL:</label>
                <input type="text" name="image_url" id="image_url" class="form-control" required="true" value="${product.image_url}"/>
            </div>
            <div class="form-group">
                <label for="category">Category:</label>
                <select name="category.id" id="category" class="form-control">
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.id}" <c:if test="${category.id == product.category.id}">selected</c:if>>${category.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <button type="submit" class="btn btn-primary">Update</button>
                <a href="<c:url value='/products'/>" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </c:if>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
