package com.miaskor.servlets;

import com.miaskor.util.Constants;
import com.miaskor.util.WebFilePath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(Constants.ControllersURI.LOADER)
public class LoaderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var fileName = (String)req.getParameter(Constants.Parameters.FILENAME);
        var folder = (String)req.getParameter(Constants.Parameters.FOLDER);
        var extension = (String)req.getParameter(Constants.Parameters.EXTENSION);
        if(extension.equals("css"))
            resp.setContentType("text/css");
        else if(extension.equals("js"))
            resp.setContentType("application/javascript");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        req.getRequestDispatcher(WebFilePath.getPath(fileName,extension,folder))
                .forward(req,resp);
    }
}
