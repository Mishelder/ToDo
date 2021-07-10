<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>TODO</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fileLoader?fileName=todo&extension=css&folder=css"
          type="text/css">
</head>
<body>
<%@include file="header.jsp" %>
<div class="above_footer">
    <div class="change_list">
        <input type="button" id="move_left" value="<-">
        <input type="button" id="move_right" value="->">
    </div>
</div>
<div id="to_do_list" class="to_do_list">

</div>

<%@include file="footer.jsp" %>
<script src="${pageContext.request.contextPath}/fileLoader?fileName=input&extension=js&folder=js"></script>
<script src="${pageContext.request.contextPath}/fileLoader?fileName=div&extension=js&folder=js"></script>
<script src="${pageContext.request.contextPath}/fileLoader?fileName=todo&extension=js&folder=js"></script>
</body>
</html>
