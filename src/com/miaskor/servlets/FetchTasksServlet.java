package com.miaskor.servlets;
import com.miaskor.entity.Client;
import com.miaskor.mapper.json.JsonRangeDateToMapMapper;
import com.miaskor.mapper.json.MapTasksToJsonMapper;
import com.miaskor.service.FetchTaskService;
import com.miaskor.util.ControllersURIKeys;
import com.miaskor.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(ControllersURIKeys.FETCH)
public class FetchTasksServlet extends HttpServlet {

    private final FetchTaskService fetchTaskService = FetchTaskService.getInstance();
    private final MapTasksToJsonMapper mapTasksToJsonMapper =
            MapTasksToJsonMapper.getInstance();
    private final JsonRangeDateToMapMapper jsonRangeDateToMapMapper =
            JsonRangeDateToMapMapper.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var body = JsonUtil.parseBody(req);
        var map = jsonRangeDateToMapMapper.map(body);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy");
        LocalDate from = LocalDate.parse(map.get("from"),dateTimeFormatter);
        LocalDate to = LocalDate.parse(map.get("to"),dateTimeFormatter);
        var client = (Client)req.getSession().getAttribute("client");
        var tasks = mapTasksToJsonMapper
                .map(fetchTaskService
                        .getTasks(from, to, client.getId()));
        resp.setContentType("application/json");
        resp.getWriter().write(tasks);
    }
}
