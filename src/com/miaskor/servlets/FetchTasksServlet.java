package com.miaskor.servlets;
import com.miaskor.util.ControllersURIKeys;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(ControllersURIKeys.FETCH)
public class FetchTasksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var tasks = (String)req.getSession().getAttribute("tasks");
        System.out.println(tasks);
        resp.setContentType("application/json");
        resp.getWriter().write(tasks);
    }
}
