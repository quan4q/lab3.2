package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if(session == null){
            response.sendRedirect("login");
            return;
        }

        if(session.getAttribute("user") == null){
            response.sendRedirect("login");
            return;
        }

        session.removeAttribute("user");

        response.sendRedirect(request.getContextPath() + "/login");
    }
}


