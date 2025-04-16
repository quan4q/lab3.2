package org.example;

import accounts.AccountService;
import accounts.UserProfile;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
        AccountService service = (AccountService) getServletContext().getAttribute("AS");
        HttpSession session = req.getSession(true);

        if (login == null || password == null || action == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        if (action.equals("register")) {
            UserProfile newUser = new UserProfile(login, password, email);
            session.setAttribute("user", newUser);
            service.addNewUser(newUser);
            String userHome = "C:\\Users\\user\\Desktop\\javaTest\\".replace("\\", "/") + login;

            resp.sendRedirect("files?path=" + userHome);
            return;
        }

        if(action.equals("login")) {
            if(service.getUserByLogin(login) != null){
                UserProfile user = service.getUserByLogin(login);
                if(password.equals(user.getPassword())){
                    String userHome = "C:\\Users\\user\\Desktop\\javaTest\\".replace("\\", "/") + login;

                    session.setAttribute("user", user);
                    resp.sendRedirect("files?path=" + userHome);
                }
                else{
                    resp.sendRedirect("login.jsp");
                }
            }
            else{
                resp.sendRedirect("login.jsp");
            }
        }
    }
}
