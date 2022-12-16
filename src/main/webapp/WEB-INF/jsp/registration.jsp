<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/registration" method="post">

    <label for="firstName">First name:
        <input type="text" name="firstName" id="firstName">
    </label><br>

    <label for="lastName">Last name:
        <input type="text" name="lastName" id="lastName">
    </label><br>

    <label for="dateOfBirth">Birthday:
        <input type="date" name="dateOfBirth" id="dateOfBirth">
    </label><br>

    <label for="address">Address:
        <input type="text" name="address" id="address">
    </label><br>

    <label for="email">Email:
        <input type="text" name="email" id="email">
    </label><br>

    <label for="mobilePhone">Mobile phone:
        <input type="text" name="mobilePhone" id="mobilePhone">
    </label><br>

    <label for="password">Password:
        <input type="password" name="password" id="password">
    </label><br>

    <label for="role">Role:
        <select name="role" id="role">
            <c:forEach var="role" items="${requestScope.roles}">
                <option value="${role}">${role}</option>
            </c:forEach>
        </select>
    </label><br>

    <button type="submit">Send</button>

<%--    <c:if test="${not empty requestScope.errors}">--%>
<%--        <div style="color: red">--%>
<%--            <c:forEach var="error" items="${requestScope.errors}">--%>
<%--                <span>${error.message}</span>--%>
<%--                <br>--%>
<%--            </c:forEach>--%>
<%--        </div>--%>
<%--    </c:if>--%>

</form>
</body>
</html>
