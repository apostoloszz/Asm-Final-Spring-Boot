<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>Checkout</h1>
    <div>${message}</div>
    <form action="${pageContext.request.contextPath}/checkout/confirm" method="post">
        <div class="mb-3">
            <label for="customerUserName">Username</label>
            <input type="text" class="form-control" id="customerUserName" name="user_name" required>
        </div>
        <div class="mb-3">
            <label for="customerPassword">Password</label>
            <input type="password" class="form-control" id="customerPassword" name="password" required>
        </div>
        <div class="mb-3">
            <label for="customerName">Customer Name</label>
            <input type="text" class="form-control" id="customerName" name="name" required>
        </div>
        <div class="mb-3">
            <label for="customerEmail">Email</label>
            <input type="email" class="form-control" id="customerEmail" name="email" required>
        </div>
        <div class="mb-3">
            <label for="customerAddress">Address</label>
            <input type="text" class="form-control" id="customerAddress" name="address" required>
        </div>
        <div class="mb-3">
            <label for="customerPhone">Phone</label>
            <input type="tel" class="form-control" id="customerPhone" name="phone" required>
        </div>

        <h3>Order Summary</h3>
        <table class="table">
            <thead>
            <tr>
                <th>Product</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cartItems}" var="item">
                <tr>
                    <td>${item.product.name}</td>
                    <td>${item.quantity}</td>
                    <td>${item.product.price}</td>
                    <td>${item.quantity * item.product.price}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <p><strong>Total: </strong>${total}</p>
        <button type="submit" class="btn btn-success">Confirm Order</button>
    </form>
</div>
</body>
</html>
