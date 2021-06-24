package com.miaskor.servlets;

import com.miaskor.dto.LoginClientDto;
import com.miaskor.exception.ValidationException;
import com.miaskor.service.FetchTaskService;
import com.miaskor.service.LoginClientService;
import com.miaskor.util.ControllersURIKeys;
import com.miaskor.util.WebFilePath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

@WebServlet(ControllersURIKeys.LOGIN)
public class LoginServlet extends HttpServlet {

    private final LoginClientService clientService = LoginClientService.getInstance();
    private final FetchTaskService fetchTaskService = FetchTaskService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        req.getRequestDispatcher(WebFilePath.getPath("login","jsp","jsp"))
        .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var login = req.getParameter("login");
        var password = req.getParameter("password");
        var loginClientDto = LoginClientDto.builder()
                .login(login)
                .password(password)
                .build();
        try{
            var client = clientService.loginClient(loginClientDto);
            Cookie cookie = new Cookie("loggedIn","true");
            resp.addCookie(cookie);
            req.getSession().setAttribute("client",client);
            var now = ZonedDateTime.now();
            var end_time = now.plusDays(4);
            var tasks =
                    fetchTaskService.getTasks(now.toLocalDate(), end_time.toLocalDate(),client.getId());
            req.getSession().setAttribute("tasks",tasks);
            req.getSession().setAttribute("start_time", now);
            req.getSession().setAttribute("end_time", end_time);
            req.getSession().setAttribute("pointer_time", now);
            resp.sendRedirect(ControllersURIKeys.TODO);
        }catch (ValidationException e){
            req.setAttribute("error",e.getErrorMessages());
            doGet(req,resp);
        }
    }
}
