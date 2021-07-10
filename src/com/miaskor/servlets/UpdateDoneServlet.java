package com.miaskor.servlets;

import com.miaskor.mapper.json.JsonToUpdateTaskDoneDtoMapper;
import com.miaskor.service.UpdateService;
import com.miaskor.util.Constants;
import com.miaskor.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.ControllersURI.UPDATE_DONE)
public class UpdateDoneServlet extends HttpServlet {

    private final UpdateService updateService = UpdateService.getInstance();
    private final JsonToUpdateTaskDoneDtoMapper jsonToUpdateTaskDoneDtoMapper = JsonToUpdateTaskDoneDtoMapper.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var body = JsonUtil.parseBody(req);
        updateService.updateDoneTask(jsonToUpdateTaskDoneDtoMapper.map(body));
    }
}
