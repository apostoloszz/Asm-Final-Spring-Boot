<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title><c:out value="${title}"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div>
    <jsp:include page="${bodyPage}"/>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
