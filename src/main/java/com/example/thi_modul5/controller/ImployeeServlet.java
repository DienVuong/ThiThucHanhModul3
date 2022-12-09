package com.example.thi_modul5.controller;

import com.example.thi_modul5.DAO.ImployeeDAO;
import com.example.thi_modul5.model.Department;
import com.example.thi_modul5.model.Imployee;
import com.example.thi_modul5.service.ImployeeServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ImployeeServlet", value = "/imployee")
public class ImployeeServlet extends HttpServlet {
    private ImployeeServiceImpl imployeeService;
    private ImployeeDAO imployeeDAO;

    @Override
    public void init() {
        imployeeService = new ImployeeServiceImpl();
        imployeeDAO = new ImployeeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "detail":
                displayDetailProduct(request, response);
                break;
            case "create":
                createForm(request, response);
                break;
            case "update":
                updateForm(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            default:
                displayListImployee(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                create(request, response);
                break;
            case "update":
                update(request, response);
                break;
            case "search":
                displaySearchProductList(request, response);
                break;
        }


    }

    private void displayListImployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
        List<Imployee> imployees = imployeeService.findAll(request);
        request.setAttribute("imployees", imployees);
        request.setAttribute("departments", imployeeService.findDepartments());


        requestDispatcher.forward(request, response);
    }


    private void displayDetailProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/detail.jsp");
        request.setAttribute("imployees", imployeeService.findById(request));
        requestDispatcher.forward(request, response);
    }

    private void displaySearchProductList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("search");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
        request.setAttribute("imployees", imployeeService.findByNameContaining(request));
        requestDispatcher.forward(request, response);
    }

    private void createForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> departments = imployeeService.findDepartments();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/create.jsp");
        request.setAttribute("departments", departments);
        requestDispatcher.forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (imployeeService.save(request)) {
            HttpSession session = request.getSession();
            session.setAttribute("message", "Create successfully!");
        }
        response.sendRedirect("http://localhost:8080/imployee");
    }

    private void updateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/update.jsp");
        request.setAttribute("imployees", imployeeService.findById(request));
        request.setAttribute("departments", imployeeService.findDepartments());
        requestDispatcher.forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        Integer phonenumber = Integer.parseInt(request.getParameter("phonenumber"));
        Integer quantity = Integer.parseInt(request.getParameter("salary"));
        Integer departmentId = Integer.parseInt(request.getParameter("departmentid"));
        imployeeService.save(request);
        response.sendRedirect("http://localhost:8080/imployee");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        imployeeService.deleteById(request);
        response.sendRedirect("http://localhost:8080/imployee");
    }
}
