package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private final Users users = Users.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            req.getRequestDispatcher("/user/hello.jsp").forward(req,resp);
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ((users.getUsers().contains(req.getParameter("login")))
                && req.getAttribute("password") != ("")) {
            HttpSession session = req.getSession();
            session.setAttribute("user", req.getAttribute("login"));
            resp.sendRedirect("/user/hello.jsp");
        } else {
            resp.sendRedirect("/login.jsp");
        }
    }
}
