package com.miaskor.servlets;

import com.miaskor.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(Constants.ControllersURI.LOGOUT)
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        Cookie cookie = new Cookie("loggedIn","false");
        resp.addCookie(cookie);
        resp.sendRedirect(Constants.ControllersURI.LOGIN);
    }
}
