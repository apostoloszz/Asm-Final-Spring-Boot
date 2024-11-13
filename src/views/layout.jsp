<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>

<%--<jsp:include page="layout.jsp">--%>
<%--    <jsp:param name="title" value="Product List" />--%>
<%--    <jsp:param name="bodyPage" value="product-list.jsp" />--%>
<%--</jsp:include>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<jsp:include page="header.jsp"/>
<jsp:include page="${bodyPage}" />
<jsp:include page="footer.jsp"/>
</div>
</body>
</html>
