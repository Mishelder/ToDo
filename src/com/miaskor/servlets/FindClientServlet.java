package com.miaskor.servlets;

import com.miaskor.mapper.json.ErrorMessagesToJsonMapper;
import com.miaskor.service.CheckClientByEmail;
import com.miaskor.util.JsonUtil;
import com.miaskor.validator.ErrorMessage;
import com.miaskor.validator.ValidationResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.miaskor.util.Constants.ControllersURI.FIND_CLIENT_BY_EMAIL;

@WebServlet(FIND_CLIENT_BY_EMAIL)
public class FindClientServlet extends HttpServlet {

    private final CheckClientByEmail checkClientByEmail = CheckClientByEmail.getInstance();
    private final ErrorMessagesToJsonMapper errorMessagesToJsonMapper =
            ErrorMessagesToJsonMapper.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var json = JsonUtil.parseBody(req);
        System.out.println(json);
        if(!checkClientByEmail.checkByEmail(json)){
            ValidationResult validationResult = new ValidationResult();
            validationResult.add(new ErrorMessage("email","email is not exist"));
            resp.getWriter().write(errorMessagesToJsonMapper.map(validationResult.getErrorMessages()));
        }
    }
}
