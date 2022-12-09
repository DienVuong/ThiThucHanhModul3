package com.example.thi_modul5.DAO;

import com.example.thi_modul5.model.Department;
import com.example.thi_modul5.model.Imployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    private final Connection connection;
    private final String SELECT_ALL_DEPARTMENT = "select * from department;";
    private final String SELECT_CATEGORY_BY_ID = "select * from department where id = ?;";

    private final String FIND_EMPLOYEE_BY_NAME = "select * from imployee where name like ? ;";

    public DepartmentDAO() {
        connection = MyConnection.getConnection();
    }

    public List<Department> findAll(){
        List< Department> departments = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_ALL_DEPARTMENT)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                departments.add(new Department(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }
    public Department findDepartmentById(Long id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_CATEGORY_BY_ID)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long idD = resultSet.getLong("id");
                String name = resultSet.getString("name");
                return new Department(idD, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Imployee> findEmployeeByName(String searchKeyWord) {
        List<Imployee> imployees = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_EMPLOYEE_BY_NAME)) {
            preparedStatement.setString(1,"%" + searchKeyWord +"%");
            getlistSearch(imployees, preparedStatement);
        } catch (SQLException  e) {
            e.printStackTrace();
        }
        return imployees;
    }
    private void  getlistSearch(List<Imployee> imployees, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");
            Integer phone = resultSet.getInt("phonenumber");
            Integer salary = resultSet.getInt("salary");
            Long departmentID = resultSet.getLong("departmentid");
            imployees.add(new Imployee(id, name, email,address,phone,salary,findDepartmentById(departmentID)));
        }
    }

}
