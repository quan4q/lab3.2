package org.example;

import accounts.UsersDB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        UsersDB usersDB = new UsersDB();
        sce.getServletContext().setAttribute("DB", usersDB);
    }
}

