package org.example;

import accounts.AccountService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        AccountService service = new AccountService();
        sce.getServletContext().setAttribute("AS", service);
    }
}

