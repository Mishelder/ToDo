package com.miaskor.servlets;

import com.miaskor.entity.Client;
import com.miaskor.entity.Task;
import com.miaskor.exception.ValidationException;
import com.miaskor.mapper.json.ErrorMessagesToJsonMapper;
import com.miaskor.mapper.json.JsonToSaveTaskDtoMapper;
import com.miaskor.mapper.json.TaskIdToJsonMapper;
import com.miaskor.service.SaveTaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.miaskor.util.JsonUtil.*;
import static com.miaskor.util.Constants.ControllersURI.*;
import static com.miaskor.util.Constants.Attributes.*;

@WebServlet(SAVE)
public class SaveTaskServlet extends HttpServlet {

    private final SaveTaskService saveTaskService = SaveTaskService.getInstance();
    private final JsonToSaveTaskDtoMapper jsonToSaveTaskDtoMapper = JsonToSaveTaskDtoMapper.getInstance();
    private final TaskIdToJsonMapper taskIdToJsonMapper = TaskIdToJsonMapper.getInstance();
    private final ErrorMessagesToJsonMapper errorMessagesToJsonMapper =
            ErrorMessagesToJsonMapper.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var body = parseBody(req);
        var saveTaskDto = jsonToSaveTaskDtoMapper.map(body);
        var client = (Client) req.getSession().getAttribute(CLIENT);
        saveTaskDto.setClientId(client.getId());
        try {
            Task task = saveTaskService.saveTask(saveTaskDto);
            resp.setContentType("application/json");
            resp.getWriter().write(taskIdToJsonMapper.map(task.getId()));
        } catch (ValidationException e) {
            String errors = errorMessagesToJsonMapper.map(e.getErrorMessages());
            resp.getWriter().write(errors);
        }
    }
}
