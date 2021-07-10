package com.miaskor.servlets;

import com.miaskor.entity.Client;
import com.miaskor.exception.ValidationException;
import com.miaskor.mapper.json.ErrorMessagesToJsonMapper;
import com.miaskor.mapper.json.JsonToRegistrationClientDtoMapper;
import com.miaskor.service.RegistrationClientService;
import com.miaskor.util.ControllersURIKeys;
import com.miaskor.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(ControllersURIKeys.REGISTRATION)
public class RegistrationServlet extends HttpServlet {

    private final RegistrationClientService registrationClientService =
            RegistrationClientService.getInstance();
    private final JsonToRegistrationClientDtoMapper jsonToRegistrationClientDtoMapper =
            JsonToRegistrationClientDtoMapper.getInstance();
    private final ErrorMessagesToJsonMapper errorMessagesToJsonMapper =
            ErrorMessagesToJsonMapper.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = JsonUtil.parseBody(req);
        var registrationClientDto = jsonToRegistrationClientDtoMapper.map(body);
        try {
            Client client = registrationClientService.
                    registerClient(registrationClientDto);
        }catch (ValidationException e){
            String errors = errorMessagesToJsonMapper.map(e.getErrorMessages());
            resp.getWriter().write(errors);
        }
    }
}
