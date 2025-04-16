package org.example;

import accounts.UserProfile;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebServlet("/files")
public class FileExplorerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestedPath = request.getParameter("path");
        String currentPath;
        String currentTime = LocalDateTime.now().toString();
        HttpSession session = request.getSession(false);
        UserProfile user = (UserProfile) session.getAttribute("user");
        String userPathRoot = "C:/Users/user/Desktop/javaTest/" + user.getLogin();

        if(session.getAttribute("user") == null){
            response.sendRedirect("/login.jsp");
            return;
        }
        if(!requestedPath.startsWith("C:/Users/user/Desktop/javaTest/")){
            currentPath = "C:/Users/user/Desktop/javaTest/" + user.getLogin();
        }

        String pathToCheck = requestedPath.replace("C:/Users/user/Desktop/javaTest", "");

        if(!pathToCheck.startsWith("/" + user.getLogin())){
            currentPath = "C:/Users/user/Desktop/javaTest/" + user.getLogin();
        }
        else{
            currentPath = requestedPath;
        }

        File directory = new File(currentPath);

        List<File> files = Arrays.asList(directory.listFiles());
        request.setAttribute("files", files);
        request.setAttribute("currentPath", currentPath);
        request.setAttribute("parentPath", directory.getParent());
        request.setAttribute("timestamp", currentTime);
        request.setAttribute("userPathRoot", userPathRoot);

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}