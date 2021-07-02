<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>TODO</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css?fileName=todo&extension=css&folder=css"
          type="text/css">
</head>
<body>
<%@include file="header.jsp" %>
<div class="to_do_list">

</div>
<div class="above_footer">
    <div class="change_list">
        <form class="change_left" action="${pageContext.request.contextPath}/flip/left" method="get">
            <input type="submit" value="<-">
        </form>

        <form class="change_right" action="${pageContext.request.contextPath}/flip/right" method="get">
            <input type="submit" value="->">
        </form>
    </div>
</div>
<%@include file="footer.jsp" %>
<script src="${pageContext.request.contextPath}/css?fileName=todo&extension=js&folder=js"></script>
</body>
</html>
