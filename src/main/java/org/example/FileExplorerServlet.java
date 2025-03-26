package org.example;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebServlet("/files")
public class FileExplorerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestedPath = request.getParameter("path");
        String currentPath;

        if(requestedPath == null || requestedPath.isEmpty()){
            currentPath = System.getProperty("user.home");
        }
        else{
            currentPath = requestedPath;
        }

        File directory = new File(currentPath);

        List<File> files = Arrays.asList(directory.listFiles());
        request.setAttribute("files", files);
        request.setAttribute("currentPath", currentPath);
        request.setAttribute("parentPath", directory.getParent());
        request.setAttribute("timestamp", new Date());

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
