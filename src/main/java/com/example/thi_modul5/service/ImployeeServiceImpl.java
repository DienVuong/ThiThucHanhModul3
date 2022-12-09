package com.example.thi_modul5.service;

import com.example.thi_modul5.DAO.DepartmentDAO;
import com.example.thi_modul5.DAO.ImployeeDAO;
import com.example.thi_modul5.model.Department;
import com.example.thi_modul5.model.Imployee;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ImployeeServiceImpl implements IImployeeService {
    private static ArrayList<Imployee> imployeeList;
    private static ArrayList<Department> departments;
    private static Long INDEX;

    private ImployeeDAO imployeeDAO;
    private DepartmentDAO departmentDAO;

    public ImployeeServiceImpl() {
        imployeeDAO = new ImployeeDAO();
        departmentDAO = new DepartmentDAO();
    }


    @Override
    public List<Imployee> findAll(HttpServletRequest request) {
        return imployeeDAO.findAll();
    }

    public List<Department> findDepartments() {
        return departmentDAO.findAll();
    }

    public Department findDerpatmentById(HttpServletRequest request) {

        return null;
    }

    @Override
    public Imployee findById(HttpServletRequest request) {
        return null;
    }

    @Override
    public boolean save(HttpServletRequest request) {
        String imployeeId = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        Integer phonenumber = Integer.parseInt(request.getParameter("phonenumber"));
        Integer salary = Integer.parseInt(request.getParameter("salary"));
        Long departmentId = Long.parseLong(request.getParameter("departmentid"));
        if (imployeeId == null) {
            return imployeeDAO.createImployee(new Imployee(name, email, address, phonenumber,salary, departmentDAO.findDepartmentById(departmentId)));
        }else {
            return imployeeDAO.updateProduct(new Imployee(Long.parseLong(imployeeId),name, email, address, phonenumber, salary, departmentDAO.findDepartmentById(departmentId)));
        }

    }

    @Override
    public boolean deleteById(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id")) ;
        return imployeeDAO.deleteImployee(id);
    }

    @Override
    public List<Imployee> findByNameContaining(HttpServletRequest request) {
        String name = request.getParameter("search");
        return imployeeDAO.findEmployeeByName(name);
    }
}