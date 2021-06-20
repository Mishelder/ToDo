<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="${pageContext.request.contextPath}/css?fileName=login&extension=css&folder=css" rel="stylesheet"
          type="text/css"/>

</head>
<body>
<%@ include file="header.jsp" %>
<div class="wrapper fadeInDown">
    <div id="formContent">
        <a href="${pageContext.request.contextPath}/login">
            <h2 class="active"> Sign In </h2>
        </a>
        <a href="${pageContext.request.contextPath}/registration">
            <h2 class="inactive underlineHover">Sign Up </h2>
        </a>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <label for="login">
                <input type="text" id="login" class="fadeIn second" name="login" value="${param.get("login")}" placeholder="login">
            </label>
            <label for="password">
                <input type="password" id="password" class="fadeIn third" name="password" placeholder="password">
            </label>
            <p style="color: red">${requestScope.error.get("login")}</p>
            <p style="color: red">${requestScope.error.get("password")}</p>
            <input type="submit" class="fadeIn fourth" value="Log In">
        </form>

        <div id="formFooter">
            <a class="underlineHover" href="#">Forgot Password?</a>
        </div>

    </div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>
