package com.miaskor.servlets;

import com.miaskor.util.ControllersURIKeys;
import com.miaskor.util.PropertyUtil;
import com.miaskor.util.WebFilePath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(ControllersURIKeys.CSS_LOADER)
public class CssServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/css");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        var fileName = (String)req.getParameter("fileName");
        var folder = (String)req.getParameter("folder");
        var extension = (String)req.getParameter("extension");
        req.getRequestDispatcher(WebFilePath.getPath(fileName,extension,folder))
                .forward(req,resp);
    }
}
