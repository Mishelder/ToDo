<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="${pageContext.request.contextPath}/fileLoader?fileName=login&extension=css&folder=css" rel="stylesheet"
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
            <a class="underlineHover fadeIn fourth" href="#">Forgot Password?</a>
        </div>
    </div>
</div>
<%@ include file="footer.jsp"%>
<noscript>You need to enable JavaScript to run this app.</noscript>
<script src="${pageContext.request.contextPath}/fileLoader?fileName=input&extension=js&folder=js"></script>
<script src="${pageContext.request.contextPath}/fileLoader?fileName=paragraph&extension=js&folder=js"></script>
<script src="${pageContext.request.contextPath}/fileLoader?fileName=registration_login&extension=js&folder=js"></script>

</body>
</html>
