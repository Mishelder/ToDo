package com.miaskor.servlets;
import com.miaskor.entity.Client;
import com.miaskor.mapper.json.JsonRangeDateToMapMapper;
import com.miaskor.mapper.json.MapTasksToJsonMapper;
import com.miaskor.service.FetchTaskService;
import com.miaskor.util.Constants;
import com.miaskor.util.DateUtil;
import com.miaskor.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(Constants.ControllersURI.FETCH)
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
        LocalDate from = LocalDate.parse(map.get("from"), DateUtil.FORMATTER_FROM_JS_DATE_FORMAT);
        LocalDate to = LocalDate.parse(map.get("to"),DateUtil.FORMATTER_FROM_JS_DATE_FORMAT);
        var client = (Client)req.getSession().getAttribute(Constants.Attributes.CLIENT);
        var tasks = mapTasksToJsonMapper
                .map(fetchTaskService
                        .getTasks(from, to, client.getId()));
        resp.setContentType("application/json");
        resp.getWriter().write(tasks);
    }
}
