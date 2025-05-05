package org.example;

import accounts.UserProfile;
import accounts.UsersDB;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        UsersDB usersDB = (UsersDB) getServletContext().getAttribute("DB");
        Long uid = null;

        if(session != null){
            uid = (Long) session.getAttribute("uid");
        }
        else{
            response.sendRedirect("login");
        }

        if (uid != null) {
            UserProfile user = usersDB.getUser(uid);
            String userHome = "C:/Users/user/Desktop/javaTest/" + user.getLogin();
            if(!request.getParameter("file").startsWith(userHome)){
                response.sendRedirect("login");
            }
        } else {
            response.sendRedirect("login");
        }

        String filePath = request.getParameter("file");
        File file = new File(filePath);

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

        try (ServletOutputStream out = response.getOutputStream()) {
            Files.copy(file.toPath(), out);
        }
    }
}