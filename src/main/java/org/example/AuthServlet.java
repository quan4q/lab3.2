package org.example;


import accounts.UserProfile;
import accounts.UsersDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

@WebServlet("/login")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        UserProfile user = (session != null) ? (UserProfile) session.getAttribute("user") : null;

        if (user != null) {
            String userHome = "C:/Users/user/Desktop/javaTest/" + user.getLogin();
            resp.sendRedirect("files?path=" + userHome);
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String action = req.getParameter("action");
        UsersDB usersDB = (UsersDB) getServletContext().getAttribute("DB");
        HttpSession session = req.getSession(true);

        if (login == null || password == null || action == null) {
            resp.sendRedirect("login");
            return;
        }

        if (action.equals("register")) {
            UserProfile newUser = new UserProfile(login, password, email);
            session.setAttribute("user", newUser);
            int rows = usersDB.setNewUser(newUser);

            if(rows == -2){
                resp.sendRedirect("login");
                return;
            }

            String userHome = "C:\\Users\\user\\Desktop\\javaTest\\".replace("\\", "/") + login;

            File folder = new File(userHome);

            if(!folder.exists()){
                folder.mkdirs();
            }

            resp.sendRedirect("files?path=" + userHome);
            return;
        }

        if(action.equals("login")) {
            if(usersDB.getPassword(login) != null && usersDB.getPassword(login).equals(password)){
                String userHome = "C:\\Users\\user\\Desktop\\javaTest\\".replace("\\", "/") + login;
                UserProfile user = new UserProfile(login, password, email);
                session.setAttribute("user", user);
                resp.sendRedirect("files?path=" + userHome);
            }
            else{
                resp.sendRedirect("login");
            }
        }
    }
}
