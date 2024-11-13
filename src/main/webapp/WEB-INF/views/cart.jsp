<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Giỏ hàng</title>
</head>
<body>
<div class="container">
    <h1 class="mt-5">Giỏ hàng của bạn</h1>
    <c:if test="${not empty message}">
        <div class="alert alert-info">${message}</div>
    </c:if>
    <table class="table">
        <thead>
        <tr>
            <th>Sản phẩm</th>
            <th>Số lượng</th>
            <th>Giá</th>
            <th>Tổng</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty cartItems}">
            <c:forEach items="${cartItems}" var="item">
                <tr>
                    <td>${item.product.name}</td>
                    <td>
                        <form action="/cart/update" method="post">
                            <input type="hidden" name="productId" value="${item.product.id}">
                            <input type="number" name="quantity" value="${item.quantity}" min="1" style="width: 50px;">
                            <button type="submit" class="btn btn-warning btn-sm">Cập nhật</button>
                        </form>
                    </td>
                    <td>${item.product.price}</td>
                    <td>${item.quantity * item.product.price}</td>
                    <td>
                        <form action="/cart/remove" method="post">
                            <input type="hidden" name="productId" value="${item.product.id}">
                            <button type="submit" class="btn btn-danger btn-sm">Xóa</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty cartItems}">
            <tr>
                <td colspan="5" class="text-center">Giỏ hàng của bạn đang trống.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <p>Tổng giá trị giỏ hàng: ${total != null ? total.setScale(2).toString() : 0.00}</p>
    <a href="/products" class="btn btn-primary">Tiếp tục mua sắm</a>
    <a href="/checkout" class="btn btn-success">Thanh toán</a>
</div>
</body>
</html>
