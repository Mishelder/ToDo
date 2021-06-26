package com.miaskor.servlets;

import com.miaskor.dto.RegistrationClientDto;
import com.miaskor.entity.Client;
import com.miaskor.exception.ValidationException;
import com.miaskor.mapper.json.JsonMapper;
import com.miaskor.service.RegistrationClientService;
import com.miaskor.util.ControllersURIKeys;
import com.miaskor.util.WebFilePath;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

@WebServlet(ControllersURIKeys.REGISTRATION)
public class RegistrationServlet extends HttpServlet {

    private final RegistrationClientService registrationClientService =
            RegistrationClientService.getInstance();
    private final JsonMapper jsonMapper = new JsonMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(WebFilePath.getPath("registration","jsp","jsp"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientJson = getBody(req);
       // var registrationClientDto = jsonMapper.readValue(clientJson);
       /* System.out.println(registrationClientDto);
        try {
            var client = registrationClientService.
                    registerClient(registrationClientDto);
        }catch (ValidationException e){
            req.setAttribute("error",e.getErrorMessages());
            resp.setStatus(403);
        }*/
    }

    private String getBody(HttpServletRequest req) {
        StringBuffer stringBuffer = new StringBuffer();
        try(InputStream inputStream = req.getInputStream()) {

            if (inputStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[256];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuffer.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }
}
