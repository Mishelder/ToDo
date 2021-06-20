package com.miaskor.servlets;

import com.miaskor.util.ControllersURIKeys;
import com.miaskor.util.DateUtil;
import com.miaskor.util.WebFilePath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZonedDateTime;

@WebServlet(urlPatterns = {ControllersURIKeys.TODO, ControllersURIKeys.TODO + "/start"})
public class ToDoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var pointer_time = (ZonedDateTime) req.getSession().getAttribute("pointer_time");
        var contains = req.getRequestURI().contains("/start");
        if(contains)
            pointer_time = (ZonedDateTime) req.getSession().getAttribute("start_time");
        req.setAttribute("time_cut_off", DateUtil.createTimeCutOffRange(pointer_time));
        req.setAttribute("time_full_month", DateUtil.createTimeFullMonth(pointer_time));
        req.getRequestDispatcher(WebFilePath.getPath("todo", "jsp", "jsp"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
