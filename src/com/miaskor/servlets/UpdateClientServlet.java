package com.miaskor.servlets;

import com.miaskor.mapper.json.JsonToUpdateClientDtoMapper;
import com.miaskor.service.UpdateClientService;
import com.miaskor.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.miaskor.util.Constants.ControllersURI.UPDATE_CLIENT;

@WebServlet(UPDATE_CLIENT)
public class UpdateClientServlet extends HttpServlet {

    private final UpdateClientService updateClientService = UpdateClientService.getInstance();
    private final JsonToUpdateClientDtoMapper jsonMapper = JsonToUpdateClientDtoMapper.getInstance();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var json = JsonUtil.parseBody(req);
        updateClientService.updateClient(jsonMapper.map(json));
    }
}
