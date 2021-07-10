package com.miaskor.servlets;


import com.miaskor.mapper.json.JsonToUpdateTaskDtoMapper;
import com.miaskor.service.UpdateService;
import com.miaskor.util.Constants;
import com.miaskor.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.ControllersURI.UPDATE)
public class UpdateServlet extends HttpServlet {

    private final UpdateService updateService = UpdateService.getInstance();
    private final JsonToUpdateTaskDtoMapper jsonToUpdateTaskDtoMapper =
            JsonToUpdateTaskDtoMapper.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var body = JsonUtil.parseBody(req);
        updateService.updateTask(jsonToUpdateTaskDtoMapper.map(body));
    }
}
