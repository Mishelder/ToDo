package com.miaskor.servlets;

import com.miaskor.dto.RegistrationClientDto;
import com.miaskor.exception.ValidationException;
import com.miaskor.service.RegistrationClientService;
import com.miaskor.util.ControllersURIKeys;
import com.miaskor.util.WebFilePath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(ControllersURIKeys.REGISTRATION)
public class RegistrationServlet extends HttpServlet {

    private final RegistrationClientService registrationClientService =
            RegistrationClientService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(WebFilePath.getPath("registration","jsp","jsp"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var login = req.getParameter("login");
        var email = req.getParameter("email");
        var password = req.getParameter("password");
        RegistrationClientDto registrationClientDto = RegistrationClientDto.builder()
                .login(login)
                .email(email)
                .password(password)
                .build();
        try {
            var client = registrationClientService.registerClient(registrationClientDto);
            req.getSession().setAttribute("client",client);
            Cookie cookie = new Cookie("loggedIn","true");
            resp.addCookie(cookie);
            resp.sendRedirect(ControllersURIKeys.LOGIN);
        }catch (ValidationException e){
            req.setAttribute("error",e.getErrorMessages());
            doGet(req,resp);
        }
    }
}
