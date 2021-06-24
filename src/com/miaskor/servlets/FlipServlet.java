package com.miaskor.servlets;

import com.miaskor.dto.FetchTaskDto;
import com.miaskor.entity.Client;
import com.miaskor.service.FetchTaskService;
import com.miaskor.util.ControllersURIKeys;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {ControllersURIKeys.FLIP_RIGHT,ControllersURIKeys.FLIP_LEFT})
public class FlipServlet extends HttpServlet {

    private final FetchTaskService fetchTaskService = FetchTaskService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var pointer_time = (ZonedDateTime) req.getSession().getAttribute("pointer_time");
        var end_time = (ZonedDateTime) req.getSession().getAttribute("end_time");

        if(req.getRequestURI().contains("right")) {
            end_time = end_time.plusDays(1);
            pointer_time = pointer_time.plusDays(1);
            addTasks(end_time,req);
        }
        else {
            pointer_time = pointer_time.minusDays(1);
            end_time = end_time.minusDays(1);
            addTasks(pointer_time,req);
        }
        req.getSession().setAttribute("pointer_time",pointer_time);
        req.getSession().setAttribute("end_time",end_time);
        resp.sendRedirect(ControllersURIKeys.TODO);
    }

    private void addTasks(ZonedDateTime getTime, HttpServletRequest req){
        var client =(Client) req.getSession().getAttribute("client");
        List<FetchTaskDto> task = fetchTaskService
                .getTask(getTime.toLocalDate(), client.getId());
        if(!task.isEmpty()) {
            var tasks = (Map<String, List<FetchTaskDto>>) req.getSession().getAttribute("tasks");
            tasks.put(getTime.toLocalDate().toString(), task);
            req.getSession().setAttribute("tasks", tasks);
        }
    }
}
