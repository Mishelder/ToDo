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
            <h2 id ="signIn" class="active">Sign In</h2>
            <h2 id ="signUp" class="inactive underlineHover">Sign Up</h2>
        <form id ="form" action="${pageContext.request.contextPath}/login" method="post">
        </form>
        <div id="formFooter">
            <a class="underlineHover" href="#">Forgot Password?</a>
        </div>
    </div>
</div>
<%@ include file="footer.jsp"%>
<script src="${pageContext.request.contextPath}/css?fileName=registration_login&extension=js&folder=js"></script>
</body>
</html>
