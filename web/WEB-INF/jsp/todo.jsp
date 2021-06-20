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
    <c:forEach varStatus="index_day" begin="0" step="1" end="4">
        <div class="to_do_day" id="${index_day.index}">
            <label>${requestScope.time_full_month.get(index_day.index)}</label>
            <form class="task_form" action="${pageContext.request.contextPath}/saveTask" method="post">
                <ul class="taskUl">
                    <c:forEach varStatus="index_task" begin="0" step="1" end="14">
                        <li class="taskLi">
                            <label for="task">
                                <input type="text" name="task_${index_task.index}"
                                       value="${sessionScope.tasks.get(requestScope.time_cut_off.get(index_day.index))
                                       .get(index_task.index).taskName}" id="task" autocomplete="off"/>
                                <input type="checkbox" id="scales" name="task_box_${index_task.index}"
                                       ${sessionScope.tasks.get(requestScope.time_cut_off.get(index_day.index))
                                       .get(index_task.index).done}/>
                            </label>
                        </li>
                    </c:forEach>
                    <c:if test="${param.day+1 == index_day.index}">
                        <p style="color: red">${requestScope.error.get("task")}</p>
                    </c:if>
                </ul>
                <input type="hidden" value="${index_day.index}" name="day"/>
                <input type="submit" value="Save" class="save_button" />
            </form>
        </div>
    </c:forEach>
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
</body>
</html>
