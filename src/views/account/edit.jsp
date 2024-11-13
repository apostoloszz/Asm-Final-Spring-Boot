<form action="${pageContext.request.contextPath}/account/update" method="post">
    <input type="hidden" name="id" value="${customer.id}" />

    <label for="userName">Username:</label>
    <input type="text" id="userName" name="user_name" value="${customer.user_name}" required />

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="${customer.email}" required />

    <label for="name">Full Name:</label>
    <input type="text" id="name" name="name" value="${customer.name}" />

    <label for="address">Address:</label>
    <input type="text" id="address" name="address" value="${customer.address}" />

    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" value="${customer.phone}" />

    <input type="submit" value="Update" />
</form>
