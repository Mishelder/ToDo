package com.miaskor.servlets;


import com.miaskor.mapper.json.JsonToUpdateTaskDtoMapper;
import com.miaskor.service.UpdateService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.miaskor.util.JsonUtil.*;
import static com.miaskor.util.Constants.ControllersURI.*;

@WebServlet(UPDATE)
public class UpdateServlet extends HttpServlet {

    private final UpdateService updateService = UpdateService.getInstance();
    private final JsonToUpdateTaskDtoMapper jsonToUpdateTaskDtoMapper =
            JsonToUpdateTaskDtoMapper.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var body = parseBody(req);
        updateService.updateTask(jsonToUpdateTaskDtoMapper.map(body));
    }
}
