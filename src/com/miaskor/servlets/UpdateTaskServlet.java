package com.miaskor.servlets;


import com.miaskor.mapper.json.JsonToUpdateTaskDtoMapper;
import com.miaskor.service.UpdateTaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.miaskor.util.Constants.ControllersURI.UPDATE_TASK;
import static com.miaskor.util.JsonUtil.parseBody;

@WebServlet(UPDATE_TASK)
public class UpdateTaskServlet extends HttpServlet {

    private final UpdateTaskService updateTaskService = UpdateTaskService.getInstance();
    private final JsonToUpdateTaskDtoMapper jsonToUpdateTaskDtoMapper =
            JsonToUpdateTaskDtoMapper.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var body = parseBody(req);
        updateTaskService.updateTask(jsonToUpdateTaskDtoMapper.map(body));
    }
}
