package com.example.jakartaeecrud;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/Controller", "/main", "/insert", "/select", "/update","/delete"})
public class Controller extends HttpServlet {

    DAO dao = new DAO();
    User user = new User();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dao.connectionTest();
        String action = request.getServletPath();
        if (action.equals("/main")){
            listUsers(request, response);
        } else if (action.equals("/insert")) {
            createUser(request, response);
        }else if (action.equals("/select")) {
            editUser(request,response);
        } else if (action.equals("/update")) {
            updateUser(request,response);
        }else if (action.equals("/delete")){
            deleteUser(request,response);
        }

    }

    protected void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> list = dao.retrieveAllUsers();
        request.setAttribute("users", list);
        RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
        rd.forward(request, response);
    }
    protected void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = request.getIntHeader("id");
        id++;
        user.setId(id);
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        dao.insertUserIntoDB(user);

        response.sendRedirect("main");
    }
    protected void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        user.setId(id);
        dao.editUser(user);
        request.setAttribute("id", user.getId());
        request.setAttribute("name", user.getName());
        request.setAttribute("email", user.getEmail());
        RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
        rd.forward(request,response);
    }

    protected void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        dao.updateUser(user);
        response.sendRedirect("main");
    }

    protected void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        user.setId(id);
        dao.deleteUser(user);
        response.sendRedirect("main");
    }

}
