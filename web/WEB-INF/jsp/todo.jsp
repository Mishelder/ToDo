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
                    <c:choose>
                        <c:when test="${sessionScope.tasks
                                    .get(requestScope.time_cut_off.get(index_day.index)) != null}">
                            <c:forEach varStatus="index_task" begin="0" step="1" end="14">
                                <c:set var="tasksIsExist" value="false" scope="page"/>
                                <c:forEach var="item" items="${sessionScope.tasks
                                    .get(requestScope.time_cut_off.get(index_day.index))}">
                                    <c:if test="${item.indexInForm == index_task.index}">
                                        <li class="taskLi">
                                            <label>
                                                <input type="text" name="task_${index_task.index}"
                                                       value="${item.taskName}" id="task" autocomplete="off"/>
                                                <input type="checkbox" name="task_box_${index_task.index}"
                                                    ${item.done}/>
                                            </label>
                                        </li>
                                        <c:set var="tasksIsExist" value="true" scope="page"/>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${tasksIsExist.equals('false')}">
                                    <li class="taskLi">
                                        <label>
                                            <input type="text" name="task_${index_task.index}"
                                                   autocomplete="off"/>
                                            <input type="checkbox" name="task_box_${index_task.index}"/>
                                        </label>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach varStatus="index_task" begin="0" step="1" end="14">
                                <li class="taskLi">
                                    <label>
                                        <input type="text" name="task_${index_task.index}"
                                               autocomplete="off"/>
                                        <input type="checkbox" name="task_box_${index_task.index}"/>
                                    </label>
                                </li>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${param.day+1 == index_day.index}">
                        <p style="color: red">${requestScope.error.get("task")}</p>
                    </c:if>
                </ul>
                <input type="hidden" value="${index_day.index}" name="day"/>
                <input type="submit" value="Save" class="save_button"/>
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
