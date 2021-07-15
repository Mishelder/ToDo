package com.miaskor.servlets;

import com.miaskor.mapper.json.JsonToDeleteTaskDtoMapper;
import com.miaskor.service.DeleteTaskService;
import com.miaskor.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.miaskor.util.Constants.ControllersURI.*;
@WebServlet(DELETE)
public class DeleteServlet extends HttpServlet {

    private final DeleteTaskService deleteTaskService = DeleteTaskService.getInstance();
    private final JsonToDeleteTaskDtoMapper jsonToDeleteTaskDtoMapper = JsonToDeleteTaskDtoMapper.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var body = JsonUtil.parseBody(req);
        var deleteTaskDto = jsonToDeleteTaskDtoMapper.map(body);
        deleteTaskService.deleteTask(deleteTaskDto);
    }
}
