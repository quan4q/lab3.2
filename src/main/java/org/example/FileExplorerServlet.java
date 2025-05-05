package org.example;

import accounts.UserProfile;
import accounts.UsersDB;

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
        HttpSession session = request.getSession(false);
        UsersDB usersDB = (UsersDB) getServletContext().getAttribute("DB");
        Long uid = null;
        String requestedPath = request.getParameter("path");
        String currentPath;
        String currentTime = LocalDateTime.now().toString();

        if(session != null){
            uid = (Long) session.getAttribute("uid");
        }
        else{
            response.sendRedirect("login");
        }

        if (uid != null) {
            UserProfile user = usersDB.getUser(uid);
            String userHome = "C:/Users/user/Desktop/javaTest/" + user.getLogin();
            currentPath = userHome;
        } else {
            response.sendRedirect("login");
        }

        UserProfile user = usersDB.getUser(uid);
        String userPathRoot = "C:/Users/user/Desktop/javaTest/" + user.getLogin();

        if(!requestedPath.startsWith("C:/Users/user/Desktop/javaTest/" + user.getLogin())){
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