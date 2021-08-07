package com.miaskor.servlets;

import com.miaskor.service.SendEmailWithCodeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.miaskor.util.Constants.ControllersURI.SEND_CODE;
@WebServlet(SEND_CODE)
public class SendCheckedCodeWhenPasswordForgottenServlet extends HttpServlet {

    private final SendEmailWithCodeService sendEmailWithCodeService = SendEmailWithCodeService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*var email = JsonUtil.parseBody(req);
        var code = sendEmailWithCodeService.sendEmail(email);
        resp.getWriter().write(code);*/
    }
}
