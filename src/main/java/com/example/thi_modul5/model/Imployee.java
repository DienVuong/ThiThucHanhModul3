package com.example.thi_modul5.model;

public class Imployee {
    private Long id;
    private String name;
    private String email;
    private String address;
    private Integer phonenumber;
    private Integer salary;
    private Department department;

    public Imployee(Long id, String name, String email, String address, Integer phonenumber, Integer salary, Department department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phonenumber = phonenumber;
        this.salary = salary;
        this.department = department;
    }

    public Imployee(Long id, String name, String email, String address, Integer phonenumber, Integer salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phonenumber = phonenumber;
        this.salary = salary;
    }

    public Imployee(String name, String email, String address, Integer phonenumber, Integer salary, Department department) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phonenumber = phonenumber;
        this.salary = salary;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Integer phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
