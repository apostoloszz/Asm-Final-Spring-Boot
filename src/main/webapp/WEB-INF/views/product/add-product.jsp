<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Product></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Add New Product</h1>
    <!-- Form for Creating a New Product -->
    <c:if test="${empty product.id}">
        <form:form method="post" action="${pageContext.request.contextPath}/products" modelAttribute="product">
            <div class="form-group">
                <label for="name">Product Name:</label>
                <form:input path="name" id="name" class="form-control" required="true"/>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <form:textarea path="description" id="description" class="form-control" rows="3" required="true"/>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <form:input path="price" id="price" class="form-control" required="true" type="number" step="0.01"/>
            </div>
            <div class="form-group">
                <label for="image_url">Image URL:</label>
                <form:input path="image_url" id="image_url" class="form-control" required="true"/>
            </div>
            <div class="form-group">
                <label for="category">Category:</label>
                <form:select path="category.id" id="category" class="form-control">
                    <form:options items="${categories}" itemValue="id" itemLabel="name"/>
                </form:select>
            </div>
            <div>
                <button type="submit" class="btn btn-primary">Create</button>
                <a href="<c:url value='/products'/>" class="btn btn-secondary">Cancel</a>
            </div>
        </form:form>
    </c:if>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>