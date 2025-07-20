package com.servlet;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.*;

@WebServlet("/AddEmployee")
public class Employee extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String department = request.getParameter("department");
        String designation = request.getParameter("designation");
        String joiningDate = request.getParameter("joining_date");
        String salary = request.getParameter("salary");
        String maritalStatus = request.getParameter("marital_status");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String pincode = request.getParameter("pincode");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/employee", "root", "Root");

            String query = "INSERT INTO employee (name, gender, dob, email, phone, address, department, designation, joining_date, salary, marital_status, city, state, pincode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, gender);
            ps.setString(3, dob);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, address);
            ps.setString(7, department);
            ps.setString(8, designation);
            ps.setString(9, joiningDate);
            ps.setDouble(10, Double.parseDouble(salary));
            ps.setString(11, maritalStatus);
            ps.setString(12, city);
            ps.setString(13, state);
            ps.setString(14, pincode);

            int result = ps.executeUpdate();
            if (result > 0) {
                out.println("<h3>Employee added successfully!</h3>");
            } else {
                out.println("<h3>Failed to add employee.</h3>");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
        System.out.println("Servlet called");

        out.close();
    }
}
