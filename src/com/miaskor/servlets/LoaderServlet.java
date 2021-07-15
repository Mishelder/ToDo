package com.miaskor.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.miaskor.util.WebFilePath.*;
import static com.miaskor.util.Constants.ControllersURI.*;
import static com.miaskor.util.Constants.Parameters.*;

@WebServlet(LOADER)
public class LoaderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var fileName = (String)req.getParameter(FILENAME);
        var folder = (String)req.getParameter(FOLDER);
        var extension = (String)req.getParameter(EXTENSION);
        if(extension.equals("css"))
            resp.setContentType("text/css");
        else if(extension.equals("js"))
            resp.setContentType("application/javascript");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        req.getRequestDispatcher(getPath(fileName,extension,folder))
                .forward(req,resp);
    }
}
