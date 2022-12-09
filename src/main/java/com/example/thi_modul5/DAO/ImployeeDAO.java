package com.example.thi_modul5.DAO;

import com.example.thi_modul5.model.Department;
import com.example.thi_modul5.model.Imployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImployeeDAO {
    private Connection connection;
    private final String SELECT_ALL_IMPLOYEE = "select * from imployee;";
    private final String INSERT_IMPLOYEE = "insert into imployee(name, email, address, phonenumber, salary,departmentid) value (?,?,?,?,?,?);";
    private final String UPDATE_IMPLOYEE = "update imployee set name = ?, email = ?, address = ? phonenumber = ? salary = ? where id = ?;";
    private final String DELETE_IMPLOYEE = "delete from imployee where id = ?;";
    private final String SELECT_IMPLOYEE_BY_NAME = "select * from imployee where name like ?;";

    private final String SELECT_IMPLOYEE_BY_ID = "select * from imployee where id = ?;";
    private final String FIND_EMPLOYEE_BY_NAME = "select * from imployee where name like ? ;";
    private DepartmentDAO departmentDAO;
    private final String FIND_DEPARTMENT_BY_ID = "select * from department where id = ?;";

    public ImployeeDAO() {
        connection = MyConnection.getConnection();
        departmentDAO = new DepartmentDAO();
    }

    public List<Imployee> findAll() {
        List<Imployee> imployees = new ArrayList<>();
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement(SELECT_ALL_IMPLOYEE)) {
            getListImployee(imployees, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imployees;
    }


    public Imployee findImployeeById(Long id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_IMPLOYEE_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                Integer phonenumber = resultSet.getInt("phonenumber");
                Integer salary = resultSet.getInt("salary");
                return new Imployee(id, name, email, address, phonenumber, salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createImployee(Imployee imployee) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_IMPLOYEE)) {
            preparedStatement.setString(1, imployee.getName());
            preparedStatement.setString(2, imployee.getEmail());
            preparedStatement.setString(3, imployee.getAddress());
            preparedStatement.setInt(4, imployee.getPhonenumber());
            preparedStatement.setInt(5, imployee.getSalary());
            preparedStatement.setLong(6,imployee.getDepartment().getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateProduct(Imployee imployee) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(UPDATE_IMPLOYEE)) {
            preparedStatement.setString(1, imployee.getName());
            preparedStatement.setString(2, imployee.getEmail());
            preparedStatement.setString(3, imployee.getAddress());
            preparedStatement.setInt(4, imployee.getPhonenumber());
            preparedStatement.setInt(5, imployee.getSalary());
            preparedStatement.setLong(6, imployee.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteImployee(Long id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(DELETE_IMPLOYEE)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Imployee> findAllByNameContaining(String nameSearch) {
        List<Imployee> imployees = new ArrayList<>();
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement(SELECT_IMPLOYEE_BY_NAME)) {
            preparedStatement.setString(1, "%" + nameSearch + "%");
            getListImployee(imployees, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imployees;
    }

    private void getListImployee(List<Imployee> imployees, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Long id = Long.parseLong(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");
            Integer phonenumber = Integer.parseInt(resultSet.getString("phonenumber"));
            Integer salary = Integer.parseInt(resultSet.getString("salary"));
            imployees.add(new Imployee(id, name, email, address, phonenumber, salary));
        }
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
    public Department findDepartmentById(Long id) {
        Department departments = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_DEPARTMENT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                departments = new Department(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

}
