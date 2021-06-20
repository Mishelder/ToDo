<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link href="${pageContext.request.contextPath}/css?fileName=registration&extension=css&folder=css" rel="stylesheet"
          type="text/css"/>

</head>
<body>
<%@ include file="header.jsp" %>
<div class="wrapper fadeInDown">
    <div id="formContent">

        <a href="${pageContext.request.contextPath}/login">
            <h2 class="inactive underlineHover"> Sign In </h2>
        </a>
        <a href="${pageContext.request.contextPath}/registration">
            <h2 class="active ">Sign Up </h2>
        </a>

        <form action="${pageContext.request.contextPath}/registration" method="post">
            <label for="login">
                <input type="text" id="login" class="fadeIn second" name="login" placeholder="login">
            </label><p style="color: red">${requestScope.error.get("login")}</p>
            <label for="email">
                <input type="email" id="email" class="fadeIn third" name="email" placeholder="email">
            </label><p style="color: red">${requestScope.error.get("email")}</p>
            <label for="password">
                <input type="password" id="password" class="fadeIn fourth" name="password" placeholder="password">
            </label><p style="color: red">${requestScope.error.get("password")}</p>
            <input type="submit" class="fadeIn fifth" value="Registration">
        </form>

    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
