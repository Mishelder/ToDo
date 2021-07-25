package com.miaskor.servlets;

import com.miaskor.exception.ValidationException;
import com.miaskor.mapper.json.ErrorMessagesToJsonMapper;
import com.miaskor.mapper.json.JsonToLoginClientDtoMapper;
import com.miaskor.service.LoginClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.miaskor.util.WebFilePath.*;
import static com.miaskor.util.JsonUtil.*;
import static com.miaskor.util.Constants.ControllersURI.*;
import static com.miaskor.util.Constants.Attributes.*;

@WebServlet(LOGIN)
public class LoginServlet extends HttpServlet {

    private final LoginClientService clientService = LoginClientService.getInstance();
    private final ErrorMessagesToJsonMapper errorMessagesToJsonMapper =
            ErrorMessagesToJsonMapper.getInstance();
    private final JsonToLoginClientDtoMapper jsonToLoginClientDtoMapper =
            JsonToLoginClientDtoMapper.getInstance();
    private final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        req.getRequestDispatcher(getPath("login", "jsp", "jsp"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body =parseBody(req);
        var loginClientDto = jsonToLoginClientDtoMapper.map(body);
        try {
            var client = clientService.loginClient(loginClientDto);
            Cookie cookie = new Cookie("loggedIn", "true");
            resp.addCookie(cookie);
            req.getSession().setAttribute(CLIENT, client);
            resp.sendRedirect(TODO);
            logger.info("Client has logged okay {}",client);
        } catch (ValidationException e) {
            String errors = errorMessagesToJsonMapper.map(e.getErrorMessages());
            logger.info("Client has errors while login {}",errors);
            resp.getWriter().write(errors);
        }
    }
}
