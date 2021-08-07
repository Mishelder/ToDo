<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="${pageContext.request.contextPath}/fileLoader?fileName=login&extension=css&folder=css" rel="stylesheet"
          type="text/css"/>

</head>
<body>

    <div class="hidden modal__window">
        <button class="close__modal">&times;</button>
        <div class="security__number hidden">
            <div><input type="text" class="checked_security" autocomplete="off" name="number1" maxlength="1"/></div>
            <div><input type="text" class="checked_security" autocomplete="off" name="number2" maxlength="1"/></div>
            <div><input type="text" class="checked_security" autocomplete="off" name="number3" maxlength="1"/></div>
            <div><input type="text" class="checked_security" autocomplete="off" name="number4" maxlength="1"/></div>
        </div>
        <input type="submit" name="back_to_email" class="back_to_email hidden" value="Change Email"/>
        <input type="email" name="email" class="forgotten_email" placeholder="Please input email" autocomplete="off" required/>
        <p id="check_email_to_change_password" class="red hidden"></p>
        <input type="submit" name="accept_email" class="accept_email" value="Send code">
    </div>
    <div class="hidden modal__background"></div>
<%@ include file="header.jsp" %>
<div class="wrapper fadeInDown">
    <div id="formContent">
            <h2 id ="signIn" class="active">Sign In</h2>
            <h2 id ="signUp" class="inactive underlineHover">Sign Up</h2>
        <form id ="form" action="${pageContext.request.contextPath}/login" method="post">
        </form>
        <div id="formFooter">
            <a class="underlineHover fadeIn fourth" id="forgot_password" href="#">Forgot Password?</a>
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
