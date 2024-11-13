<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Product List</title>
</head>
<body>

<div class="container">
    <c:if test="${not empty message}">
        <div class="alert alert-success">${message}</div>
    </c:if>

    <h1 class="mt-5">Product List</h1>
    <div class="row">
        <c:forEach items="${products}" var="product">
            <div class="col-md-4">
                <div class="card mb-4">
                    <img src="${product.image_url}" class="card-img-top" alt="${product.name}">
                    <div class="card-body text-center">
                        <h5 class="card-title font-weight-bold">${product.name}</h5>
                        <p class="card-text text-muted">${product.description}</p>
                        <p class="card-text"><strong>Price: </strong>${product.price} VND</p>

                        <!-- Add to Cart Button -->
                        <form action="${pageContext.request.contextPath}/cart/add" method="post" class="d-inline-block">
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="hidden" name="quantity" value="1">
                            <button type="submit" class="btn btn-success btn-sm">
                                <i class="fas fa-cart-plus"></i> Add to Cart
                            </button>
                        </form>

                        <!-- Edit Button (nếu đã có id tồn tại) -->
                        <c:if test="${product.id != null}">
                            <a href="/products/${product.id}/edit" class="btn btn-warning btn-sm ml-2">
                                <i class="fas fa-edit"></i> Edit
                            </a>
                        </c:if>

                        <!-- Delete Button -->
                        <a href="/products/${product.id}/delete" class="btn btn-danger btn-sm ml-2"
                           onclick="return confirm('Are you sure you want to delete this product?')">
                            <i class="fas fa-trash"></i> Delete
                        </a>
                    </div>

                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
