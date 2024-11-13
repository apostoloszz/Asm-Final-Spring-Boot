    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Order Confirmation</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
    <div class="container">
        <h1>Order Confirmation</h1>
        <p>Thank you for your purchase, <strong>${order.customer.name}</strong>!</p>
        <p>Your order ID is: <strong>${order.id}</strong></p>

        <h3>Order Details</h3>
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
            <c:forEach items="${order.orderDetails}" var="detail">
                <tr>
                    <td>${detail.product.name}</td>
                    <td>${detail.quantity}</td>
                    <td>${detail.price}</td>
                    <td>${detail.price.multiply(new java.math.BigDecimal(detail.quantity))}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <p><strong>Total: </strong>${order.total}</p>
        <a href="${pageContext.request.contextPath}/products" class="btn btn-primary">Continue Shopping</a>
    </div>
    </body>
    </html>
