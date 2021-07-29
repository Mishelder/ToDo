<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/fileLoader?fileName=header&extension=css&folder=css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div class="header_container">
        <div class="header-bar">
            <h1 class="logo">ToDo</h1>
            <ul class="slider-menu">
                <c:if test="${cookie.loggedIn.value.equals('true') && sessionScope.client !=null}">
                    <li><a href = "${pageContext.request.contextPath}/todo" id="home_btn" class="links">Home</a></li>
                </c:if>
                <li>About</li>
                <c:if test="${cookie.loggedIn.value.equals('true') && sessionScope.client !=null}">
                <li><a href="${pageContext.request.contextPath}/logout" id="logout_btn" class="links">Logout</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</body>
</html>
