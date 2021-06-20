package com.miaskor.servlets;

import com.miaskor.dto.FetchTaskDto;
import com.miaskor.entity.Client;
import com.miaskor.service.GetTaskService;
import com.miaskor.util.ControllersURIKeys;
import com.miaskor.util.WebFilePath;

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

    private final GetTaskService getTaskService = GetTaskService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var pointer_time = (ZonedDateTime) req.getSession().getAttribute("pointer_time");
        var client =(Client) req.getSession().getAttribute("client");
        var tasks = (Map<String, List<FetchTaskDto>>) req.getSession().getAttribute("tasks");
        if(req.getRequestURI().contains("right")) {
            pointer_time = pointer_time.plusDays(1);
        }
        else {
            pointer_time = pointer_time.minusDays(1);
        }
        var task = getTaskService
                .getTask(pointer_time.toLocalDate(), client.getId());
        tasks.put(pointer_time.toLocalDate().toString(),task);
        req.getSession().setAttribute("tasks",tasks);
        req.getSession().setAttribute("pointer_time",pointer_time);
        req.getRequestDispatcher(ControllersURIKeys.TODO).forward(req,resp);
    }
}
