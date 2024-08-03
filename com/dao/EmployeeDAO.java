package com.dao;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Employee;

public class EmployeeDAO {

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/vsbdata";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        // Define the SQL INSERT query
        String sql = "INSERT INTO employee_data (id, ename, city, dept, designation, doj, dob, salary, address, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Create the employee data to insert
        int id = 05;
        String ename = "Jas";
        String city = "Karur";
        String dept = "IT";
        String designation = " Full Stack Developer";
        java.sql.Date doj = java.sql.Date.valueOf("2022-06-06");
        java.sql.Date dob = java.sql.Date.valueOf("2003-05-30");
        double salary = 50000;
        String address = "456 karur north";

        // Read the image file
        try (InputStream is = new FileInputStream("C://Users//ADMIN//Downloads//travel.png/")) {
            byte[] arr = is.readAllBytes();

            // JDBC connection and prepared statement
            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement pst = con.prepareStatement(sql)) {

                // Set the parameters for the prepared statement
                pst.setInt(1, id);
                pst.setString(2, ename);
                pst.setString(3, city);
                pst.setString(4, dept);
                pst.setString(5, designation);
                pst.setDate(6, doj);
                pst.setDate(7, dob);
                pst.setDouble(8, salary);
                pst.setString(9, address);
                pst.setBytes(10, arr);

                // Execute the insert command
                int rowsAffected = pst.executeUpdate();

                // Print the result
                if (rowsAffected > 0) {
                    System.out.println("A new employee has been inserted successfully.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Retrieve and print a specific employee
        Employee employee = getEmployee(05);
        if (employee != null) {
            printEmployee(employee);
        } else {
            System.out.println("Employee not found.");
        }

        // Retrieve and print employees with more than 2 years of experience
        List<Employee> employeesWithExperience = getEmployeesWithMoreThanTwoYearsExperience();
        for (Employee emp : employeesWithExperience) {
            printEmployee(emp);
        }
    }

    public static Employee getEmployee(int id) {
        Employee employee = null;
        String sql = "SELECT id, ename, city, dept, designation, doj, dob, salary, address, image FROM employee_data WHERE id = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    employee = new Employee();
                    employee.setId(rs.getInt("id"));
                    employee.setEname(rs.getString("ename"));
                    employee.setCity(rs.getString("city"));
                    employee.setDept(rs.getString("dept"));
                    employee.setDesignation(rs.getString("designation"));
                    employee.setDoj(rs.getDate("doj"));
                    employee.setDob(rs.getDate("dob"));
                    employee.setSalary(rs.getDouble("salary"));
                    employee.setAddress(rs.getString("address"));
                    employee.setImage(rs.getBytes("image"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }

    public static List<Employee> getEmployeesWithMoreThanTwoYearsExperience() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT id, ename, city, dept, designation, doj, dob, salary, address, image FROM employee_data WHERE DATEDIFF(CURDATE(), doj) > 730";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setEname(rs.getString("ename"));
                employee.setCity(rs.getString("city"));
                employee.setDept(rs.getString("dept"));
                employee.setDesignation(rs.getString("designation"));
                employee.setDoj(rs.getDate("doj"));
                employee.setDob(rs.getDate("dob"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setAddress(rs.getString("address"));
                employee.setImage(rs.getBytes("image"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public static void printEmployee(Employee employee) {
        System.out.println("ID: " + employee.getId());
        System.out.println("Name: " + employee.getEname());
        System.out.println("City: " + employee.getCity());
        System.out.println("Department: " + employee.getDept());
        System.out.println("Designation: " + employee.getDesignation());
        System.out.println("Date of Joining: " + employee.getDoj());
        System.out.println("Date of Birth: " + employee.getDob());
        System.out.println("Salary: " + employee.getSalary());
        System.out.println("Address: " + employee.getAddress());
        System.out.println("Image: " + (employee.getImage() != null ? "[Image Data]" : "No Image"));
        System.out.println("-------------------------------");
    }
}