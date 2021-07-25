package com.miaskor.servlets;

import com.miaskor.entity.Client;
import com.miaskor.exception.ValidationException;
import com.miaskor.mapper.json.ErrorMessagesToJsonMapper;
import com.miaskor.mapper.json.JsonToRegistrationClientDtoMapper;
import com.miaskor.service.RegistrationClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.miaskor.util.JsonUtil.*;
import static com.miaskor.util.Constants.ControllersURI.*;

@WebServlet(REGISTRATION)
public class RegistrationServlet extends HttpServlet {

    private final RegistrationClientService registrationClientService =
            RegistrationClientService.getInstance();
    private final JsonToRegistrationClientDtoMapper jsonToRegistrationClientDtoMapper =
            JsonToRegistrationClientDtoMapper.getInstance();
    private final ErrorMessagesToJsonMapper errorMessagesToJsonMapper =
            ErrorMessagesToJsonMapper.getInstance();
    private final Logger logger = LoggerFactory.getLogger(RegistrationServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = parseBody(req);
        var registrationClientDto = jsonToRegistrationClientDtoMapper.map(body);
        try {
            Client client = registrationClientService.
                    registerClient(registrationClientDto);
            logger.info("Client has registered okay {}",client);
        } catch (ValidationException e) {
            String errors = errorMessagesToJsonMapper.map(e.getErrorMessages());
            logger.info("Client has errors while registration {}",errors);
            resp.getWriter().write(errors);
        }
    }
}
