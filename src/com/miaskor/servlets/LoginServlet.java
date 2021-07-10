package com.miaskor.servlets;

import com.miaskor.exception.ValidationException;
import com.miaskor.mapper.json.ErrorMessagesToJsonMapper;
import com.miaskor.mapper.json.JsonToLoginClientDtoMapper;
import com.miaskor.service.LoginClientService;
import com.miaskor.util.Constants;
import com.miaskor.util.JsonUtil;
import com.miaskor.util.WebFilePath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(Constants.ControllersURI.LOGIN)
public class LoginServlet extends HttpServlet {

    private final LoginClientService clientService = LoginClientService.getInstance();
    private final ErrorMessagesToJsonMapper errorMessagesToJsonMapper =
            ErrorMessagesToJsonMapper.getInstance();
    private final JsonToLoginClientDtoMapper jsonToLoginClientDtoMapper =
            JsonToLoginClientDtoMapper.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        req.getRequestDispatcher(WebFilePath.getPath("login", "jsp", "jsp"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = JsonUtil.parseBody(req);
        var loginClientDto = jsonToLoginClientDtoMapper.map(body);
        try {
            var client = clientService.loginClient(loginClientDto);
            Cookie cookie = new Cookie("loggedIn", "true");
            resp.addCookie(cookie);
            req.getSession().setAttribute(Constants.Attributes.CLIENT, client);
            resp.sendRedirect(Constants.ControllersURI.TODO);
        } catch (ValidationException e) {
            String errors = errorMessagesToJsonMapper.map(e.getErrorMessages());
            resp.getWriter().write(errors);
        }
    }
}
